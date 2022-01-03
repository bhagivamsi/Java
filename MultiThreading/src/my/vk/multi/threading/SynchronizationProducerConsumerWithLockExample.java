package my.vk.multi.threading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizationProducerConsumerWithLockExample {
    public static void main(String... args) {
        Processor2 p = new Processor2();

        Thread t1 = new Thread(() -> {
            try {
                p.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                p.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }
}

class Processor2 {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private List<Integer> list = new ArrayList<>();

    private final Integer UPPER_LIMIT = 5;
    private final Integer LOWER_LIMIT = 0;
    private Integer value = 0;

    public void produce() throws InterruptedException {
        while (true) {
            lock.lock();
            if (list.size() == UPPER_LIMIT) {
                System.out.println("List full...");
                condition.await();
            } else {
                list.add(value++);
                System.out.println("Added..." + value);
                condition.signal();
            }
            Thread.sleep(500L);
            lock.unlock();
        }

    }


    public void consume() throws InterruptedException {

        while (true) {
            lock.lock();
            if (list.size() == LOWER_LIMIT) {
                System.out.println("List empty...");
                value = 0;
                condition.await();
            } else {
                System.out.println("Removed..." + list.remove(list.size() - 1));
                condition.signal();
            }
            Thread.sleep(500L);
            lock.unlock();
        }
    }
}
