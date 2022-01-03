package my.vk.multi.threading;

import java.util.ArrayList;
import java.util.List;

public class SynchronizationProducerConsumerExample {
    public static void main(String... args) {
        Processor p = new Processor();

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

class Processor {
    private Object lock = new Object();

    private List<Integer> list = new ArrayList<>();

    private final Integer UPPER_LIMIT = 5;
    private final Integer LOWER_LIMIT = 0;
    private Integer value = 0;

    public void produce() throws InterruptedException {

        synchronized (lock) {
            while (true) {
                if (list.size() == UPPER_LIMIT) {
                    System.out.println("List full...");
                    lock.wait();
                } else {
                    list.add(value++);
                    System.out.println("Added..." + value);
                    lock.notify();
                }
                Thread.sleep(500L);
            }
        }
    }

    public void consume() throws InterruptedException {

        synchronized (lock) {
            while (true) {
                if (list.size() == LOWER_LIMIT) {
                    System.out.println("List empty...");
                    value = 0;
                    lock.wait();
                } else {
                    System.out.println("Removed..." + list.remove(list.size() - 1));
                    lock.notify();
                }
                Thread.sleep(500L);
            }
        }
    }
}
