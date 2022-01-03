package my.vk.multi.threading;

import java.util.stream.IntStream;

public class RunnableExample {
    public static void main(String... args) {
        Thread runnable1 = new Thread(new RunnableClass(1, 100));
        Thread runnable2 = new Thread(new RunnableClass(2, 100));

        runnable1.start();
        runnable2.start();
    }
}

class RunnableClass implements Runnable {

    final Integer id;
    final Integer noOfIterations;

    RunnableClass(Integer id, Integer noOfIterations) {
        this.id = id;
        this.noOfIterations = noOfIterations;
    }

    @Override
    public void run() {
        IntStream.range(0, noOfIterations).forEach(t -> {
            System.out.printf("ID: %d \t Current Iteration : %d \n", id, t);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}