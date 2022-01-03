package my.vk.multi.threading;

public class DaemonThreadsExample {
    public static void main(String... args) {
        Thread t1 = new Thread(new DaemonThread(false, DaemonThread.THREAD_TYPE.NORMAL, 5000L));
        Thread t2 = new Thread(new DaemonThread(true, DaemonThread.THREAD_TYPE.DAEMON, 1000L));

        t2.setDaemon(true);

        t1.start();
        t2.start();
    }
}

class DaemonThread implements Runnable {
    DaemonThread(boolean isIterativeExecution, THREAD_TYPE threadType, Long sleepDurationInMillis) {
        this.isIterativeExecution = isIterativeExecution;
        this.threadType = threadType;
        this.sleepDurationInMillis = sleepDurationInMillis;
    }

    enum THREAD_TYPE {
        NORMAL, DAEMON;
    }

    final THREAD_TYPE threadType;
    final Long sleepDurationInMillis;
    final boolean isIterativeExecution;

    @Override
    public void run() {
        if (isIterativeExecution) {
            iterativeExecution();
        } else {
            nonIterativeExecution();
        }
    }

    private void iterativeExecution() {
        while (true) {
            try {
                Thread.sleep(sleepDurationInMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadType.toString() + " thread is running");
        }
    }

    private void nonIterativeExecution() {
        try {
            Thread.sleep(sleepDurationInMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(threadType.toString() + " thread is completed");
    }
}