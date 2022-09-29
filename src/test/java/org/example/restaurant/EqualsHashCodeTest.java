package org.example.restaurant;
import java.util.Random;
//Какие класс
// ы содержат верную пару equals hashCode. Невалидным может быть только один из методов
public class EqualsHashCodeTest {
    class A {
        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            return 33;
        }
    }

    class B {
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

    class D {
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

    class E {
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

    class F {
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
}
