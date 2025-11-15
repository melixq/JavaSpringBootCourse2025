package ru.kuznetsovmd.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.kuznetsovmd.MySecondTestAppSpringBoot.model.Positions;

@Service
public class AnnualBonusServiceImpl implements AnnualBonusService {
    public double calculateAnnualBonus(Positions position, double salary, double bonus, int workDays, int year) {
        int daysInYear = isLeapYear(year) ? 366 : 365;
        return salary * bonus * daysInYear * position.getPositionCoefficient() / workDays;
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
