package org.example.restaurant.service.impl;

import liquibase.repackaged.org.apache.commons.lang3.NotImplementedException;
import org.example.restaurant.service.ArrayFiller;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static liquibase.repackaged.org.apache.commons.lang3.StringUtils.join;

public class ArrayFillerInTwoThreads implements ArrayFiller {
    /**
     * Добавляет в List элеметы от 0 до 99.
     * Задача должна быть выполнена в двух потоках
     * @param list пустой список для заполенния
     */
    @Override
    public void arrayFillSortedFrom0to100(List<Integer> list) {
        throw new NotImplementedException();
        /*
         * Напишите вашу реализацию
         */
    }
}
