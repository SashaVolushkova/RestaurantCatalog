package org.example.restaurant;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

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
        Map<String, String> map = new HashMap<>();
        map.put("c", "c");
        map.put("a", "a");
        map.put("b", "b");

    }

    @Test
    public void test5() {
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

    class Key {
        int a;
        Key() {
            a = ThreadLocalRandom.current().nextInt();
        }

        @Override
        public int hashCode() {
            return 55;
        }
    }

    class B {
        int b;
        B() {b = 10;}
    }
    @Test
    public void test7() {
        B b = new B();
        HashMap<B, String> map = new HashMap<>();
        map.put(b, "a");
        b.b = 100;
        String s = map.get(b);
        assertSame("a", s);
    }

    class C {
        int c;
        C() { c = 10; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            C c1 = (C) o;

            return c == c1.c;
        }

        @Override
        public int hashCode() {
            return c;
        }
    }

    @Test
    public void test8() {
        C c = new C();
        HashMap<C, String> map = new HashMap<>();
        map.put(c, "a");
        c.c = 100;
        String s = map.get(c);
        assertSame("a", s);
    }

    @Test
    public void test9() {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);integers.add(2);integers.add(3);
        for (Integer integer : integers) {
            integers.remove(1);
        }
    }

    // TODO добавить задачу,
    // чтоб определить правильную коллекцию выберет для решения.
}
