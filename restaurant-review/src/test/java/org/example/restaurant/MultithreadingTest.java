package org.example.restaurant;

import org.example.restaurant.service.ArrayFiller;
import org.example.restaurant.service.impl.ArrayFillerInTwoThreads;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class MultithreadingTest {
    @Test
    public void test1() {
        ArrayFiller f = new ArrayFillerInTwoThreads();
        ArrayList<Integer> integers = new ArrayList<>();
        /*
         * Реализуйте функцию arrayFillSortedFrom0to100
         * Добавить числа от 0 до 100 в массив integers использую 2 потока
         */
        f.arrayFillSortedFrom0to100(integers);
        for (int i = 0; i < 100; i++) {
            assertEquals(i, integers.get(i));
        }
    }

    @Test
    public void test2() {
        /*
         * Какая ошибка будет вызвана соответвующим кодом. Укажите правильный exception в assert
         * Добавьте аналогичные assert с вызовом других методов, которые вызывают ту же ошибку.
         */
        assertThrowsExactly(Exception.class, () -> wait(100));
        /*
         * Укажите причину возникновения ошибки:
         *
         */
    }

}
