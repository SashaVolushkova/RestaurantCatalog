package org.example.restaurant;

import org.example.restaurant.service.RoadMap;
import org.example.restaurant.service.impl.RoadMapImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "unused"})
@Disabled
public class JavaCollectionsTest {
    @Test
    public void test1() {
        Set<Object> set = new TreeSet<>();
        /*
         * Какая ошибка будет инициирована? Подставьте нужное значение в переменную x
         * Если ошибки нет, уберите assertThrowsExactly блок.
         */
        Class<? extends Throwable> x = Exception.class;
        assertThrowsExactly(x, () -> {
            set.add(5);
            set.add("aaaa");
        });
        /*
         * Опишите причину:
         *
         */
    }

    @Test
    public void test1_1() {
        Set<Object> set = new HashSet<>();
        /*
         * Какая ошибка будет инициирована? Подставьте нужное значение в переменную x
         * Если ошибки нет, уберите assertThrowsExactly блок.
         */
        Class<? extends Throwable> x = Exception.class;
        assertThrowsExactly(x, () -> {
            set.add(5);
            set.add("aaaa");
        });
        /*
         * Опишите причину:
         *
         */
    }

    @Test
    public void test1_2() {
        Set<Object> set = new TreeSet<>();
        /*
         * Какая ошибка будет инициирована? Подставьте нужное значение в переменную x
         * Если ошибки нет, уберите assertThrowsExactly блок.
         */
        Class<? extends Throwable> x = Exception.class;
        assertThrowsExactly(x, () -> {
            set.add(5.5);
            set.add(6.7f);
        });
        /*
         * Опишите причину:
         *
         */
    }
    @Test
    public void test2() {
        Set<Object> set = new TreeSet<>();
        /*
         * Какая ошибка будет инициирована? Подставьте нужное значение в переменную x
         * Если ошибки нет, уберите assertThrowsExactly блок.
         */
        Class<? extends Throwable> x = Exception.class;
        assertThrowsExactly(x, () -> set.add(new ArrayList<>()));
        /*
         * Опишите причину:
         *
         */
    }

    @Test
    public void test3() {
        Map<Object, Object> map = new TreeMap<>();
        /*
         * Какая ошибка будет инициирована? Подставьте нужное значение в переменную x
         * Если ошибки нет, уберите assertThrowsExactly блок.
         */
        Class<? extends Throwable> x = Exception.class;
        assertThrowsExactly(x, () -> map.put(new ArrayList<Integer>(), "aaa"));
        /*
         * Опишите причину:
         *
         */
    }

    @Test
    public void test4() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("c", "c");
        map.put("a", "a");
        map.put("b", "b");
        /*
         * Какой порядок элементов будет в map.
         * Поставьте правильное занчение в arr.
         */
        String[] arr = new String[]{/**/};
        Collection<String> values = map.values();
        assertArrayEquals(arr, values.toArray());
    }

    @Test
    public void test5() {
        Map<String, String> map = new TreeMap<>();
        map.put("c", "c");
        map.put("a", "a");
        map.put("b", "b");
        /*
         * Какой порядок элементов будет в map.
         * Поставьте правильное занчение в arr.
         */
        String[] arr = new String[]{/**/};
        Collection<String> values = map.values();
        assertArrayEquals(arr, values.toArray());
    }

    static class Key {
        int a;
        Key() {
            a = new Random().nextInt();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key = (Key) o;

            return a == key.a;
        }

        @Override
        public int hashCode() {
            return a;
        }
    }

    @Test
    public void test6() {
        Key key = new Key();
        HashMap<Key, String> map = new HashMap<>();
        map.put(key, "a");
        key.a += 100;
        String o = map.get(key);
        /*
         *  Сравните объекты o и "a".
         */
        fail();
        //assert*****("a", o);
    }

    @Test
    public void test9() {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);integers.add(2);integers.add(3);
        /*
         * Удалите элемент 2 с использованием итератора.
         */
        assertArrayEquals(new Integer[]{1,3}, integers.toArray());
    }

    private final RoadMap roadMap = new RoadMapImpl();
    @Test
    public void test10() {
        roadMap.add("Санкт-Петербург", "Москва", 700);
        roadMap.add("Санкт-Петербург", "Петрозавоздск", 800);
        roadMap.add("Санкт-Петербург", "Хельсинки", 400);
        roadMap.add("Санкт-Петербург", "Выборг", 180);
        roadMap.add("Выборг", "Хельсинки", 100);

        roadMap.add("Москва", "Тверь",  160);
        roadMap.add("Москва", "Тула",  650);
        roadMap.add("Тверь", "Санкт-Петербург",  650);
        roadMap.add("Тверь", "Псков", 550);
        roadMap.add("Волгоград", "Саратов", 830);

        assertEquals(9, roadMap.roadCount());
        assertThrows(Exception.class, () -> roadMap.add("Москва", "Санкт-Петербург", 650));
        assertThrows(Exception.class, () -> roadMap.add("Москва", "Тверь", 650));
        assertEquals(700, roadMap.roadLength("Москва", "Санкт-Петербург"));
        assertEquals(710, roadMap.roadLength("Москва", "Псков"));
        assertEquals(1100, roadMap.roadLength("Москва", "Хельсинки"));
        assertTrue(roadMap.roadLength("Москва", "Саратов") < 0);
    }
}
