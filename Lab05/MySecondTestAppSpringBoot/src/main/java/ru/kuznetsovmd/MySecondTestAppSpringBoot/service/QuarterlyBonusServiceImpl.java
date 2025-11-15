package ru.kuznetsovmd.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.kuznetsovmd.MySecondTestAppSpringBoot.model.Positions;

@Service
public class QuarterlyBonusServiceImpl implements QuarterlyBonusService {
    @Override
    public double calculateQuarterlyBonus(Positions position, double salary, double bonus, int workDays) {
        if (!position.isManager())
            return 0.0;

        return position.getPositionCoefficient() * salary * bonus * workDays / 90;
    }
}
