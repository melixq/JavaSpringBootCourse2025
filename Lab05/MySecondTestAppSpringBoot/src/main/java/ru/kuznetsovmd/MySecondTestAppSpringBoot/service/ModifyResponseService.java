package ru.kuznetsovmd.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.kuznetsovmd.MySecondTestAppSpringBoot.model.Response;

@Service
public interface ModifyResponseService {
    Response modify(Response response);
}
