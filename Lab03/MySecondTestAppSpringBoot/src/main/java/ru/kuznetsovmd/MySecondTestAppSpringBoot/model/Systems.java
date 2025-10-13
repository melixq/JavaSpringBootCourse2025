package ru.kuznetsovmd.MySecondTestAppSpringBoot.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Systems {
    ERP("Enterprise Resource Planning"),
    CRM("Customer Relationship Management"),
    WMS("Warehouse Management System");

    private final String systemName;

    Systems(String systemName) {
        this.systemName = systemName;
    }

    @JsonValue
    public String getSystemName() {
        return systemName;
    }

    @Override
    public String toString() {
        return getSystemName();
    }
}
