import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Manager implements Runnable{
    private BlockingQueue<Task> taskQueue;
    private AtomicInteger taskName;

    public BlockingQueue<Task> getTaskQueue() {
        return taskQueue;
    }

    public void setTaskQueue(BlockingQueue<Task> taskQueue) {
        this.taskQueue = taskQueue;
    }

    public AtomicInteger getTaskName() {
        return taskName;
    }

    public void setTaskName(AtomicInteger taskName) {
        this.taskName = taskName;
    }

    Manager(AtomicInteger taskName, BlockingQueue<Task> taskQueue){
        this.taskName = taskName;
        this.taskQueue = taskQueue;
    }
    public void run() {
        addTask();
    }
    public void addTask(){
        Task task = new Task();
        task.setName(taskName.incrementAndGet()+"");
        taskQueue.add(task);
        System.out.println("Add task: "+task.getName());
    }
}
