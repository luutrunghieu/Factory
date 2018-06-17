import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static int NUMBERS_OF_WORKERS = 5;
    public static int NUMBERS_OF_MANAGERS = 1;
    public static BlockingQueue<Task> taskQueue = new LinkedBlockingQueue<Task>();
    public static void main(String[] args) {
        AtomicInteger taskName = new AtomicInteger();

        ScheduledExecutorService managerExecutor = Executors.newScheduledThreadPool(NUMBERS_OF_MANAGERS);
        for(int i = 0;i<NUMBERS_OF_MANAGERS;i++){
            Manager manager = new Manager(taskName,taskQueue);
            managerExecutor.scheduleAtFixedRate(manager,0,1,TimeUnit.SECONDS);
        }

        ScheduledExecutorService workerExecutor = Executors.newScheduledThreadPool(NUMBERS_OF_WORKERS);
        for (int i = 0; i < NUMBERS_OF_WORKERS; i++) {
            Runnable worker = new Worker("" + i,taskQueue);
            workerExecutor.scheduleAtFixedRate(worker,0,1,TimeUnit.SECONDS);
        }

        try {
            managerExecutor.awaitTermination(1,TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        managerExecutor.shutdown();

        try {
            workerExecutor.awaitTermination(1,TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        workerExecutor.shutdown();
        System.out.println("Finished");
    }
}
