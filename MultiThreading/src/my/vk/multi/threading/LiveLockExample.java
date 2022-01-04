package my.vk.multi.threading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LiveLockExample {

    Lock lock1 = new ReentrantLock(true);
    Lock lock2 = new ReentrantLock(true);

    public static void main(String... args) {
        LiveLockExample deadLock = new LiveLockExample();

        new Thread(() -> {
            while (true) {
                deadLock.lock1.tryLock();
                System.out.println("Thread1 locks lock1");

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (deadLock.lock2.tryLock()) {
                    System.out.println("Thread1 locks lock2");
                    deadLock.lock2.unlock();
                    break;
                } else {
                    System.out.println("Thread1 unable to lock lock2");
                }

            }
            deadLock.lock1.unlock();
            deadLock.lock2.unlock();
        }).start();

        new Thread(() -> {
            while (true) {
                deadLock.lock2.tryLock();
                System.out.println("Thread2 locks lock2");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (deadLock.lock1.tryLock()) {
                    System.out.println("Thread2 locks lock1");
                    deadLock.lock1.unlock();
                    break;
                } else {
                    System.out.println("Thread2 unable to lock lock1");
                }

            }
            deadLock.lock1.unlock();
            deadLock.lock2.unlock();
        }).start();
    }
}
