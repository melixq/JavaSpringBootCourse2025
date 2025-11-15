package ru.kuznetsovmd.MySecondTestAppSpringBoot.service;

import ru.kuznetsovmd.MySecondTestAppSpringBoot.model.Positions;

public interface AnnualBonusService {
    double calculateAnnualBonus(Positions positions, double salary, double bonus, int workDays, int year);
}
