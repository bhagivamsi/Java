package my.vk.multi.threading;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableExample {

    public static void main(String... args) throws ExecutionException, InterruptedException, TimeoutException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        List<Future<String>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<String> futureResult = executorService.submit(() -> {
                Instant before = Instant.now();
                TimeUnit.SECONDS.sleep((long) (Math.random()));
                return String.format("Completed thread : %s in %s",
                        Thread.currentThread().getName(),
                        Duration.between(before, Instant.now()).getNano());
            });
            results.add(futureResult);
        }

        printResults(results);
    }

    private static void printResults(List<Future<String>> results) throws InterruptedException, ExecutionException, TimeoutException {
        for (Future<String> result : results) {
            if (result.isDone()) {
                System.out.println(result.get());
            } else {
                System.out.println("WAITED " + result.get(2, TimeUnit.SECONDS));
            }
        }
    }
}
