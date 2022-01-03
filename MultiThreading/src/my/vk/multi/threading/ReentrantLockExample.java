package my.vk.multi.threading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

    static int counter = 0;
    static Lock lock = new ReentrantLock();

    private static void increment() {
        lock.lock();
        try {
            for (int i = 0; i < 10000; i++) {
                counter++;
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String... args) throws InterruptedException {
        Thread t1 = new Thread(ReentrantLockExample::increment);
        Thread t2 = new Thread(ReentrantLockExample::increment);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter);
    }
}
