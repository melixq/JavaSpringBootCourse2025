package ru.kuznetsovmd.MySecondTestAppSpringBoot.model;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class Response {
    /**
     * Уникальный идентификатор сообщения
     */
    private String uid;

    /**
     * Уникальный идентификатор операции
     */
    private String operationUid;

    /**
     * Отметка системного времени
     */
    private String systemTime;

    /**
     * Код-статус запроса
     */
    private Codes code;

    /**
     * Код ошибки
     */
    private ErrorCodes errorCode;

    /**
     * Тело ошибки
     */
    private ErrorMessages errorMessage;

    /**
     * Рассчитанная годовая премия
     */
    private Double annualBonus;

    /**
     * Рассчитанная квартальная премия
     */
    private Double quarterBonus;
}
