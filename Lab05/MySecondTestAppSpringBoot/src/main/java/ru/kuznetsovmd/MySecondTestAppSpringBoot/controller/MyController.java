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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kuznetsovmd.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.kuznetsovmd.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.kuznetsovmd.MySecondTestAppSpringBoot.model.*;
import ru.kuznetsovmd.MySecondTestAppSpringBoot.service.*;

import java.time.Instant;


@Slf4j
@RestController
public class MyController {
    private final RequestProcessingService requestProcessingService;

    public MyController(RequestProcessingService requestProcessingService) {
        this.requestProcessingService = requestProcessingService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(
            @Valid @RequestBody Request request,
            BindingResult bindingResult
    ) {
        log.info("Processing feedback request");
        return requestProcessingService.processRequest(request, bindingResult);
    }
}
