package ru.kuznetsovmd.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.kuznetsovmd.MySecondTestAppSpringBoot.model.Request;

@Service
public interface ModifyRequestService {
    void modify(Request request);
}
