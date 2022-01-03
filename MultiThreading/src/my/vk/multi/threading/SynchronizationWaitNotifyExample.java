package my.vk.multi.threading;

public class SynchronizationWaitNotifyExample {

    public static void main(String... args) {
        CounterClass obj = new CounterClass();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    obj.print();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    obj.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}

class CounterClass {
    Integer counter = 0;

    public void print() throws InterruptedException {
        synchronized (this) {
            System.out.println("Start produce: counter  " + counter);
            wait();
            System.out.println("End produce: counter  " + counter);
        }
    }

    public void increment() throws InterruptedException {
        synchronized (this) {
            counter++;
            notify();
            Thread.sleep(5000);
            System.out.println("End increment: counter  " + counter);
        }
    }
}