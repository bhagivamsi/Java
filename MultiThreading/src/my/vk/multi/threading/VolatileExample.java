package my.vk.multi.threading;

public class VolatileExample {

    public static void main(String... args) throws InterruptedException {
        Worker worker = new Worker();

        Thread t1 = new Thread(worker);
        t1.start();

        Thread.sleep(2000);
        Worker.terminated = true;
        System.out.println("Worker thread is terminated");
    }
}

class Worker implements Runnable {
    public volatile static boolean terminated = false;

    @Override
    public void run() {
        while (!terminated) {
            System.out.println("Worker thread is running");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}