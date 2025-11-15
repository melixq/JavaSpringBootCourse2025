package ru.kuznetsovmd.MySecondTestAppSpringBoot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.kuznetsovmd.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.kuznetsovmd.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.kuznetsovmd.MySecondTestAppSpringBoot.model.*;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class RequestProcessingService {
    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final AnnualBonusService annualBonusService;
    private final QuarterlyBonusService quarterlyBonusService;

    @Autowired
    public RequestProcessingService(
            ValidationService validationService,
            @Qualifier(value = "ModifySystemTimeResponseService") ModifyResponseService modifyResponseService,
            AnnualBonusServiceImpl annualBonusService,
            QuarterlyBonusServiceImpl quarterlyBonusService)
    {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.annualBonusService = annualBonusService;
        this.quarterlyBonusService = quarterlyBonusService;
    }

    /**
     * Обрабатывает входящий запрос и возвращает ответ
     */
    public ResponseEntity<Response> processRequest(Request request, BindingResult bindingResult) {
        String receiveTime = Instant.now().toString();
        request.setRequestReceiveTime(receiveTime);

        log.info("Received request: {}, time: {}", request, receiveTime);

        Response response = buildInitialResponse(request);
        log.info("Initial response: {}", response);

        try {
            validateRequest(request, bindingResult);
            processBonuses(request, response);
            modifyResponseService.modify(response);

            log.info("Final response: {}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (UnsupportedCodeException e) {
            return handleException(response, e, ErrorCodes.UNSUPPORTED_EXCEPTION,
                    ErrorMessages.UNSUPPORTED, HttpStatus.BAD_REQUEST);
        } catch (ValidationFailedException e) {
            return handleException(response, e, ErrorCodes.VALIDATION_EXCEPTION,
                    ErrorMessages.VALIDATION, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return handleException(response, e, ErrorCodes.UNKNOWN_EXCEPTION,
                    ErrorMessages.UNKNOWN, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Создает начальный ответ
     */
    private Response buildInitialResponse(Request request) {
        return Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
    }

    /**
     * Валидация запроса
     */
    private void validateRequest(Request request, BindingResult bindingResult)
            throws UnsupportedCodeException, ValidationFailedException {
        log.info("Validating request: {}", request);
        validationService.isValidUid(request.getUid());
        validationService.isValid(bindingResult);
        log.info("Request validation passed");
    }

    /**
     * Расчет премий
     */
    private void processBonuses(Request request, Response response) {
        // Годовая премия
        double annualBonus = annualBonusService.calculateAnnualBonus(
                request.getPosition(),
                request.getSalary(),
                request.getBonus(),
                request.getWorkDays(),
                request.getYear()
        );
        response.setAnnualBonus(annualBonus);

        // Квартальная премия
        double quarterBonus = quarterlyBonusService.calculateQuarterlyBonus(
            request.getPosition(),
            request.getSalary(),
            request.getBonus(),
            request.getWorkDays()
        );
        response.setQuarterBonus(quarterBonus);
    }

    /**
     * Обработка исключений
     */
    private ResponseEntity<Response> handleException(Response response, Exception e,
                                                     ErrorCodes errorCode, ErrorMessages errorMessage,
                                                     HttpStatus status) {
        log.error("{}: {}", e.getClass().getSimpleName(), e.getMessage());

        response.setCode(Codes.FAILED);
        response.setErrorCode(errorCode);
        response.setErrorMessage(errorMessage);

        log.info("Response after exception: {}", response);
        return new ResponseEntity<>(response, status);
    }
}