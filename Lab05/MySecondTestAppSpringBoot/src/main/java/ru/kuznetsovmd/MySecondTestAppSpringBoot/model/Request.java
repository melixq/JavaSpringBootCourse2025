package ru.kuznetsovmd.MySecondTestAppSpringBoot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    /**
     * Уникальный идентификатор сообщения
     */
    @NotBlank(message = "UID is required")
    @Size(max = 32, message = "Max length of UID is 32 characters")
    private String uid;

    /**
     * Уникальный идентификатор операции
     */
    @NotBlank(message = "Operation UID is required")
    @Size(max = 32, message = "Max length of Operation UID is 32 characters")
    private String operationUid;

    /**
     * Название системы, передающей запрос
     */
    private Systems systemName;

    /**
     * Отметка системного времени
     */
    @NotBlank(message = "System Time is required")
    private String systemTime;

    /**
     * Источник запроса
     */
    private String source;

    /**
     * Идентификатор передачи
     */
    @Min(value = 1, message = "Communication Id must be at least 1")
    @Max(value = 100000, message = "Communication Id must be at most 100000")
    private int communicationId;

    /**
     * Занимаемая должность
     */
    private Positions position;

    /**
     * Размер зарплаты
     */
    private Double salary;

    /**
     * Бонусный коэффициент
     */
    private Double bonus;

    /**
     * Количество отработанных дней
     */
    private Integer workDays;

    /**
     * Год, для которого выполняется расчет
     */
    private Integer year;

    /**
     * Уникальный идентификатор шаблона сообщения
     */
    private int templateId;

    /**
     * Уникальный идентификатор продукта
     */
    private int productCode;

    /**
     * Уникальный идентификатор SMS-сообщения
     */
    private int smsCode;

    /**
     * Временная отметка получения запроса
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private String requestReceiveTime;

    @Override
    public String toString() {
        return "{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName='" + systemName + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", communicationId='" + communicationId + '\'' +
                ", templateId='" + templateId + '\'' +
                ", productCode='" + productCode + '\'' +
                ", smsCode='" + smsCode + '\'' +
                ", requestReceiveTime='" + requestReceiveTime + '\'' +
                ", salary=" + salary + '\'' +
                ", bonus=" + bonus + '\'' +
                ", workDays=" + workDays + '\'' +
                ", position=" + position +
                ", year=" + year + '\'' +
                '}';
    }
}
