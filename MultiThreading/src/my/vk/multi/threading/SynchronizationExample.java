package my.vk.multi.threading;

public class SynchronizationExample {

    static Integer counter = 0;

    public static synchronized void incrementCounter() {
        counter++;
    }

    public static void main(String... args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++)
                    incrementCounter();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++)
                    incrementCounter();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("counter " + counter);
    }
}
