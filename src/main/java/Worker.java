import java.security.SecureRandom;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Worker implements Runnable {
    private BlockingQueue<Task> taskQueue;
    private String name;
    public BlockingQueue<Task> getTaskQueue() {
        return taskQueue;
    }

    public void setTaskQueue(BlockingQueue<Task> taskQueue) {
        this.taskQueue = taskQueue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Worker(String name, BlockingQueue<Task> taskQueue){
        this.name = name;
        this.taskQueue = taskQueue;
    }
    public void run() {
        while(!taskQueue.isEmpty()){
            try {
                doTask(taskQueue.poll(5, TimeUnit.SECONDS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void doTask(Task task){
        try {
            Thread.sleep(new SecureRandom().nextInt(5000));
            System.out.println("Worker's name: "+name+", Task name: "+task.getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
