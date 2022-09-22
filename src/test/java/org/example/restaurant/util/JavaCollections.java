package org.example.restaurant.util;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class JavaCollections {
    @Test
    public void test1() {
        Set<Object> set = new TreeSet<>();
        set.add(5);
        set.add("aaaa");
    }

    @Test
    public void test1_1() {
        Set<Object> set = new HashSet<>();
        set.add(5);
        set.add("aaaa");
    }

    @Test
    public void test1_2() {
        Set<Object> set = new TreeSet<>();
        set.add(5.5);
        set.add(6.7f);
    }
    @Test
    public void test2() {
        Set<Object> set = new TreeSet<>();
        set.add(new ArrayList<>());
    }

    @Test
    public void test3() {
        Map<Object, Object> map = new TreeMap<>();
        map.put(new ArrayList<Integer>(), "aaa");
    }

    @Test
    public void test4() {
        Map<String, String> map = new HashMap<>();
        map.put("c", "c");
        map.put("a", "a");
        map.put("b", "b");
        for (Map.Entry<String, String> e : map.entrySet()) {
            assertEquals("a", e.getKey());
        }
    }

    @Test
    public void test5() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("c", "c");
        map.put("a", "a");
        map.put("b", "b");
        for (Map.Entry<String, String> e : map.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
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

    @Test
    public void test6() {
        HashMap<Key, String> map = new HashMap<>();
        Key key = new Key();
        map.put(key, "c");
        for (int i = 0; i < 100; i ++) {
            map.put(new Key(), String.valueOf(i));
        }
        // Какая сложность операции (в терминолигии O или о)
        String s = map.get(key);
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
}
