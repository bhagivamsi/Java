package my.vk.multi.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public static void main(String... args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int t = 0; t < 12; t++) {
            executorService.execute(CostlyOperation.getInstance()::download);
        }
    }
}

class CostlyOperation {
    private static CostlyOperation costlyOperation = null;
    Semaphore semaphore = new Semaphore(2, true);

    public static CostlyOperation getInstance() {
        if (costlyOperation == null) {
            costlyOperation = new CostlyOperation();
        }
        return costlyOperation;
    }

    private CostlyOperation() {

    }

    public void download() {
        try {
            semaphore.acquire();

            System.out.println("Start executing Costly Operation " + Thread.currentThread().getName());
            Thread.sleep(3000);
            System.out.println("Completed executing Costly Operation " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}