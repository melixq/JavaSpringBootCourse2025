package ru.kuznetsovmd.MySecondTestAppSpringBoot.service;

import org.junit.jupiter.api.Test;
import ru.kuznetsovmd.MySecondTestAppSpringBoot.model.Positions;

import static org.junit.jupiter.api.Assertions.*;

class AnnualBonusServiceImplTest {

    @Test
    void calculateAnnualBonus() {
        // mock
        Positions position = Positions.DEV;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;
        int year = 2025;

        // when
        double result = new AnnualBonusServiceImpl().calculateAnnualBonus(position, salary, bonus, workDays, year);

        // then
        double excepted = 660905.3497942387;
        assertEquals(excepted, result);
    }
}