package org.example.restaurant.service.impl;

import liquibase.repackaged.org.apache.commons.lang3.NotImplementedException;
import org.example.restaurant.service.RoadMap;

public class RoadMapImpl implements RoadMap {
    /**
     * Добавление дороги в существующую сеть дорог. Дороги двустрониие.
     * Если есть дорога Москва Тверь, то можно попасть из Москвы в Тверь и из Твери в Москву.
     * Неальзя добавить дорогу если уже есть дорога из путка А в пункт Б или из Б в А.
     * Пример:
     * дорога Санкт-Петербург Москва 700,
     * дорога Санкт-Петербург Петрозавоздск 800,
     * дорога Москва Тула 650,
     * дорога Москва Тверь 160,
     * дорога Тверь Псков 550
     *
     * При добавлении дороги Москва Тверь 220 или Москва Тверь 220 не должны быть добавлены (уже есть дорога москва Тверь 160)
     * @param length - длина дороги в километрах
     * @param townA - пункт А
     * @param townB - пункт Б
     */

    @Override
    public void add(String townA, String townB, int length) {
        //добавьте вашу реализацию
        throw new NotImplementedException();
    }

    /**
     * Найти кротчаюшую длину пути из пунта А в пункт Б. Длина пути измеряется в километрах. Дороги двустрониие.
     * Если есть дорога Москва Тверь, то можно попасть из Москвы в Тверь и из Твери в Москву.
     * Если пути не существует вернуть отрицательное число
     * Из предыдущего примера: Путь Мсоква Псков = 710 км (Москва-Тверь, Тверь-Псков)
     * Пути Псков Перразаводск нет - вернется отрицательное число.
     * @param townA - пункт А
     * @param townB - пункт Б
     * @return >0 если есть путь, <0 если нет пути из пункта А в пункт Б
     */

    @Override
    public int roadLength(String townA, String townB) {
        //добавьте вашу реализацию
        throw new NotImplementedException();
    }

    /**
     * возвращает количесво дорог
     * @return количесво дорог
     */
    @Override
    public int roadCount() {
        //добавьте вашу реализацию
        throw new NotImplementedException();
    }
}
