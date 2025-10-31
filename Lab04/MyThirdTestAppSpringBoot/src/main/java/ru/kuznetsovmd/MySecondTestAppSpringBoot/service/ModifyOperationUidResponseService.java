package ru.kuznetsovmd.MySecondTestAppSpringBoot.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.kuznetsovmd.MySecondTestAppSpringBoot.model.Response;

import java.util.UUID;

@Service
@Qualifier("ModifyOperationUidResponseService")
public class ModifyOperationUidResponseService implements ModifyResponseService {
    @Override
    public Response modify(Response response) {
        UUID uid = UUID.randomUUID();
        response.setUid(uid.toString());
        return response;
    }
}
