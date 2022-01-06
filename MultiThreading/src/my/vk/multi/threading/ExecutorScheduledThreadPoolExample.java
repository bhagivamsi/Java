package my.vk.multi.threading;

import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorScheduledThreadPoolExample {
    public static void main(String... args) {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

        System.out.printf("Starting execution at %s\n", Instant.now().toString());
//        executorService.scheduleAtFixedRate(() -> {
//            System.out.printf("Executed task at %s with Thread %s\n", Instant.now().toString(), Thread.currentThread().getName());
//        }, 1000, 5000, TimeUnit.MILLISECONDS);

        executorService.scheduleWithFixedDelay(() -> {
            System.out.printf("Executed task at %s with Thread %s\n", Instant.now().toString(), Thread.currentThread().getName());
        }, 1000, 5000, TimeUnit.MILLISECONDS);
    }
}