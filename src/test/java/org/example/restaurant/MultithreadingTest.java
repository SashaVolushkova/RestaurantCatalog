package org.example.restaurant;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class MultithreadingTest {
    AtomicInteger i = new AtomicInteger(0);
    class A implements Runnable {
        @Override
        public void run() {
            while (i.get() < 100) {
                System.out.println(i);
                i.incrementAndGet();
            }
        }
    }

    @Test
    public void test1() {
        A a = new A();
        Thread thread1 = new Thread(a);
        Thread thread2 = new Thread(a);
        thread1.start();
        thread2.start();
    }

    @Test
    public void test2() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i=0; i<100; i++) {
                if(!Thread.interrupted()) {
                    System.out.println(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println("InterruptedException!");
                        System.err.println("Exception msg: " + e.getMessage());
                        return;
                    }
                } else {
                    System.out.println("Interrupted!");
                    return;
                }
            }
        });
        thread1.start();
        Thread.sleep(1000);
        thread1.interrupt();
    }

}
