package ru.kuznetsovmd.MySecondTestAppSpringBoot.service;

import ru.kuznetsovmd.MySecondTestAppSpringBoot.model.Positions;

public interface QuarterlyBonusService {
    double calculateQuarterlyBonus(Positions position, double salary, double bonus, int workDays);
}
