package org.example.restaurant.service;

import java.util.List;

public interface ArrayFiller {
    /**
     * Добавляет в List элеметы от 0 до 99.
     * Задача должна быть выполнена в двух потоках
     * @param list пустой список для заполенния
     */
    void arrayFillSortedFrom0to100(List<Integer> list);
}
