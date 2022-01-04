package my.vk.multi.threading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockExample {

    Lock lock1 = new ReentrantLock(true);
    Lock lock2 = new ReentrantLock(true);

    public static void main(String... args) {
        DeadLockExample deadLock = new DeadLockExample();

        new Thread(() -> {
            deadLock.lock1.lock();
            System.out.println("Thread1 locks lock1");

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            deadLock.lock2.lock();
            System.out.println("Thread1 locks lock2");

            deadLock.lock1.unlock();
            deadLock.lock2.unlock();
        }).start();

        new Thread(() -> {
            deadLock.lock2.lock();
            System.out.println("Thread2 locks lock2");

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            deadLock.lock1.lock();
            System.out.println("Thread2 locks lock1");

            deadLock.lock1.unlock();
            deadLock.lock2.unlock();
        }).start();
    }
}
