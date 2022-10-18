package org.example.restaurant;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SuppressWarnings("ALL")
public class EqualsHashCodeTest {
    static class A {
        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            return 33;
        }
    }

    static class B {
        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            Random random = new Random();
            return random.nextInt();
        }
    }

    static class D {
        int a;

        @Override
        public boolean equals(Object o) {
            return a == ((D) o).a;
        }

        @Override
        public int hashCode() {
            return 77;
        }
    }

    static class E {
        int a;

        @Override
        public boolean equals(Object obj) {
            if(obj == null) return false;
            return obj instanceof E;
        }

        @Override
        public int hashCode() {
            return a;
        }
    }

    static class F {
        int a;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            F f = (F) o;

            return a == f.a;
        }

        @Override
        public int hashCode() {
            return a;
        }
    }

    @Test
    public void validate() {
        boolean a = EqualsVerifier.simple().forClass(A.class).report().isSuccessful();
        boolean b = EqualsVerifier.simple().forClass(B.class).report().isSuccessful();
        boolean d = EqualsVerifier.simple().forClass(D.class).report().isSuccessful();
        boolean e = EqualsVerifier.simple().forClass(E.class).report().isSuccessful();
        boolean f = EqualsVerifier.simple().forClass(F.class).report().isSuccessful();
        /*
         * Какие классы содержат верную пару equals hashCode. Невалидным может быть только один из методов.
         *
         */
        //assertTrue....
        //assertFalse....
        assertEquals(new Object(), a);
        assertEquals(new Object(), b);
        assertEquals(new Object(), d);
        assertEquals(new Object(), e);
        assertEquals(new Object(), f);
    }
}
