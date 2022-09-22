package org.example.restaurant.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class JavaCore {
    @Test
    void test1() {
        String a = "aaa";
        String b = "aaa" ;
        assertSame(a, b);
    }

    @Test
    void test2() {
        String a = "aaa";
        String b = new String ("aaa") ;
        assertSame(a, b);
    }

    @Test
    void test3() {
        String a = "AAA";
        String b = a.toLowerCase() ;
        assertEquals("aaa", a);
    }

    ////
    @Test
    void test4() {
        Integer a = 5;
        Integer b = 5;
        assertSame(a, b);
    }

    @Test
    void test5() {
        Integer a = 5;
        Integer b = a;
        b += 5;
        assertEquals(10, a);
    }

    @Test
    void test6() {
        double a = 10.1;
        double b = a;
        a *= 10;
        assertEquals(101.0, b);
    }

    class A {
        int a;
    }

    private void f(A a) {
        a.a *= 10;
    }

    @Test
    public void test7() {
        A a = new A();
        a.a = 10;
        f(a);
        assertEquals(100, a.a);
    }

    private void f2(A a) {
        a = new A();
        a.a = 100;
    }

    @Test
    public void test8() {
        A a = new A();
        a.a = 10;
        f2(a);
        assertEquals(100, a.a);
    }

    ///
    @Test
    public void test9() {
        double a1 = 0;
        for (int i = 1; i <= 10; i++) {
            a1 += 0.1;
        }
        double a2 = 0.1 * 10;
        assertEquals(a1, a2);
    }

    @Test
    public void test10() {
        double a1 = 1.0;
        double a2 = 0.9999999;
        assertEquals(a1, a2, 0.000001d);
    }

    ////
    private final int test11 = 10;
    class B {
        private int b;
        B() {
            b = test11 * 10;
        }
    }
    @Test
    public void test11() {
        B b = new B();
        assertEquals(100, b.b);
    }
}
