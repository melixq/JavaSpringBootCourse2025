package ru.kuznetsovmd.MySecondTestAppSpringBoot.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kuznetsovmd.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.kuznetsovmd.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.kuznetsovmd.MySecondTestAppSpringBoot.model.*;
import ru.kuznetsovmd.MySecondTestAppSpringBoot.service.ModifyResponseService;
import ru.kuznetsovmd.MySecondTestAppSpringBoot.service.ValidationService;


@Slf4j
@RestController
public class MyController {
    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier(value = "ModifySystemTimeResponseService") ModifyResponseService modifyResponseService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {
        log.info("Received request: {}", request);

        var response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        log.info("Response built before validation: {}", response);

        try {
            log.info("Validating request: {}", request);

            validationService.isValidUid(request.getUid());
            validationService.isValid(bindingResult);

            log.info("Request validation passed, final response: {}", response);
        } catch (UnsupportedCodeException e) {
            log.error("Validation failed with UnsupportedCodeException: {}", e.getMessage());
            if (bindingResult.hasErrors()) {
                log.error("Binding errors during UnsupportedCodeException: {}", bindingResult.getAllErrors());
            }
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNSUPPORTED);

            log.info("Response updated after UnsupportedCodeException: {}", response);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (ValidationFailedException e) {
            log.error("Validation failed with ValidationFailedException: {}", e.getMessage());
            if (bindingResult.hasErrors()) {
                log.error("Binding errors during ValidationFailedException: {}", bindingResult.getAllErrors());
            }

            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);

            log.info("Response updated after ValidationFailedException: {}", response);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Validation failed with UnknownException: {}", e.getMessage());
            if (bindingResult.hasErrors()) {
                log.error("Binding errors during UnknownException: {}", bindingResult.getAllErrors());
            }

            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);

            log.info("Response updated after UnknownException: {}", response);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        modifyResponseService.modify(response);

        log.info("Response updated after modify response: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
