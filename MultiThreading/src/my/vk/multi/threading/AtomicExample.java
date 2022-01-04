package my.vk.multi.threading;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {
    static AtomicInteger value = new AtomicInteger(0);

    public static void main(String... args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) value.incrementAndGet();
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) value.incrementAndGet();
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(value);
    }
}
