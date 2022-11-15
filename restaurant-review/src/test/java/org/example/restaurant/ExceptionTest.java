package org.example.restaurant;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings({"UnusedAssignment", "CaughtExceptionImmediatelyRethrown"})
@Disabled
public class ExceptionTest {
    @Test
    public void test1() {
        int x = 0;
        try {
            throw new NullPointerException();
        } catch (NullPointerException e) {
            x = 1;
        } catch (Exception e) {
            x = 2;
        } finally {
            x = 3;
        }
        assertEquals(0, x);
    }

    @Test
    public void test2() {
        int x = 0;
        try {
            throw new NullPointerException();
        } catch (NullPointerException e) {
            x = 1;
        } catch (Exception e) {
            x = 2;
        }
        assertEquals(0, x);
    }

    @Test
    public void test3() {
        int x = 0;
        try {
            throw new FileNotFoundException();
        } catch (RuntimeException e) {
            x = 1;
        } catch (Exception e) {
            x = 2;
        }
        assertEquals(0, x);
    }

    @Test
    public void test4() {
        int x = 0;
        try {
            throw new NullPointerException();
        } catch (NullPointerException e) {
            x = 1;
        } catch (Exception e) {
            x = 2;
        }
        assertEquals(0, x);
    }

    @Test
    public void test5() {
        int x = 0;
        try {
            try {
                throw new Exception();
            } catch (Exception e) {
                x += 10;
            }
        } catch (NullPointerException e) {
            x += 200;
        } catch (Exception e) {
            x += 3000;
        } finally {
            x += 400;
        }
        assertEquals(0, x);
    }

    String s = "";
    void f() throws Exception {
        try {
            s += "a";
            throw new Exception();
        } catch (Exception e) {
            throw e;
        } finally {
            s += "b";
        }
    }

    @Test
    public void test6() {
        try {
            f();
        } catch (Exception e) {
            s += "c";
        }
        assertEquals("xxxx", s);
    }
}
