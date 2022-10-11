package org.example.restaurant;

import org.junit.jupiter.api.Test;

public class JavaCoreTest {
    @Test
    void test1() {
        String a = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String b = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        /*
         * Объекты a и b одинаковые? Добавьте соответвующий assert
         */

        //assertXXXXXXX(a ==  b)

        /*
         * Опишите причину:
         *
         */
    }

    @Test
    void test2() {
        String a = "aaa";
        String b = new String ("aaa") ;
        /*
         * Объекты a и b одинаковые? Добавьте соответвующий assert
         */

        //assertXXXXXXX(a ==  b)

        /*
         * Опишите причину:
         *
         */
    }

    @Test
    void test3() {
        String a = "AAA";
        String b = a.toLowerCase() ;
        /*
         * Изменится ли объект a? Добавьте соответвующий assert
         */

        //assertXXXXXXXXX("aaa", a);

        /*
         * Опишите причину:
         *
         */

    }

    ////
    @Test
    void test4() {
        Integer a = 5000;
        Integer b = 5000;
        /*
         * Объекты a и b одинаковые? Добавьте соответвующий assert
         */

        //assertXXXXXXX(a ==  b)

        /*
         * Опишите причину:
         *
         */
    }

    @Test
    void test5() {
        double a = 10.1;
        double b = a;
        a *= 10;
        /*
         * Изменится ли значение переменной b? Добавьте соответвующий assert
         */
        //assertXXXXXXX(101.0, b);
    }

    class A {
        int a;
    }

    private void f(A a) {
        a.a *= 10;
    }

    @Test
    public void test6() {
        A a = new A();
        a.a = 10;
        f(a);
        /*
         * Изменится ли значение a.a? Добавьте соответвующий assert
         */
        //assertXXXXXXX(100, a.a);
    }

    private void f2(A a) {
        a = new A();
        a.a = 100;
    }

    @Test
    public void test7() {
        A a = new A();
        a.a = 10;
        f2(a);
        /*
         * Изменится ли значение a.a? Добавьте соответвующий assert
         */
        //assertXXXXXXX(100, a.a);

    }

    ///
    @Test
    public void test8() {
        double a1 = 0;
        for (int i = 1; i <= 10; i++) {
            a1 += 0.1;
        }
        double a2 = 0.1 * 10;
        /*
         * Одинаковое ли значение переменных a1 и a2? Добавьте соответвующий assert
         */
        //assertXXXXXXX(a1, a2);

        /*
         * Опишите причину:
         *
         */
    }
}
