package org.example.employee.service.util.impl;

import org.example.employee.service.util.WorkHourService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class WorkHourServiceImpl implements WorkHourService {
    private final Random random;

    public WorkHourServiceImpl() {
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public Integer getWorkHours() {
        return random.nextInt(0, 160);
    }
}
