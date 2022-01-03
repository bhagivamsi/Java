package my.vk.multi.threading;

public class LockingWithCustomObjExample {

    static Integer counter1 = 0;
    static Integer counter2 = 0;

    static Object counter1Lock = new Object();
    static Object counter2Lock = new Object();

    public static void incrementCounter1() {
        synchronized (counter1Lock) {
            counter1++;
        }
    }

    public static void incrementCounter2() {
        synchronized (counter2Lock) {
            counter2++;
        }
    }

    public static void main(String... args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++)
                    incrementCounter1();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++)
                    incrementCounter2();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("counter1 " + counter1);
        System.out.println("counter2 " + counter2);
    }
}
