package ru.kuznetsovmd.MySecondTestAppSpringBoot.service;

import org.junit.jupiter.api.Test;
import ru.kuznetsovmd.MySecondTestAppSpringBoot.model.Positions;

import static org.junit.jupiter.api.Assertions.*;

class QuarterlyBonusServiceImplTest {

    @Test
    void calculateQuarterlyBonusWhenManager() {
        Positions position = Positions.HR;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;

        // when
        double result = new QuarterlyBonusServiceImpl().calculateQuarterlyBonus(position, salary, bonus, workDays);

        // then
        double excepted = 648000;
        assertEquals(excepted, result);
    }

    @Test
    void calculateQuarterlyBonusWhenNonManager() {
        Positions position = Positions.DEV;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;

        // when
        double result = new QuarterlyBonusServiceImpl().calculateQuarterlyBonus(position, salary, bonus, workDays);

        // then
        double excepted = 0;
        assertEquals(excepted, result);
    }
}