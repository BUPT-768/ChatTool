package Server;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Jayvee on 2014/12/3.
 */
public class TaskManager {
    private Queue<String> taskQueue;

    /**
     * 服务端的任务队列管理器
     */
    public TaskManager() {
        this.taskQueue = new LinkedList<String>();
    }

    public void addTask(String taskText) {
        taskQueue.add(taskText);
    }

    public String getTask() {
        return taskQueue.peek();
    }

    /**
     * 如果队列为空则返回false，否则true
     *
     * @return {@code taskQueue.isEmpty()}
     */
    public boolean isAvailable() {
        return !taskQueue.isEmpty();
    }

}
