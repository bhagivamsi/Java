package my.vk.multi.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorSingleThreadExample {
    public static void main(String... args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("Executed task %d with Thread %s\n", finalI+1, Thread.currentThread().getName());
            });
        }

        executorService.shutdown();
    }
}