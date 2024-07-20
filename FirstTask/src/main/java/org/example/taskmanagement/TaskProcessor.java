package org.example.taskmanagement;

import java.util.*;
import java.util.concurrent.*;

/**
 * TaskProcessor class to execute tasks in multiple threads
 */
public class TaskProcessor{
    /**
     * ExecuterService for working directly with threads
     */
    private ExecutorService executerService;

    /**
     * lock object to provide a synchronization mechanism for the execution
     */
    private final Object lock = new Object();

    /**
     * Constructor to initialize a new TaskProcessor
     * @param ThreadPoolSize: number of threads needed in the pool
     */
    public TaskProcessor(int ThreadPoolSize){
        this.executerService = Executors.newFixedThreadPool(ThreadPoolSize);
    }

    /**
     * method to execute a list of tasks using lock as a synchronization mechanism, and using
     * thread.sleep() to simulate the processing time of a task
     * @param tasks
     */
    public void executeTasks(List<Task> tasks){
        for(Task task : tasks){
            executerService.execute(() -> { // I can make Task extend Thread and pass task here, but I wanted to use lambda somewhere :)
                synchronized (lock) {
                    System.out.println("Starting " + task.getTitle() + task.getStatus()+ "----\n"); // task starts
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.err.println("Task interrupted " + e.getMessage());
                    }

                    task.setStatus("C"); // task completed
                    System.out.println("Completed " + task.getTitle() + " ---\n");
                }
            });

    }

    }

    /**
     * method to stop accepting new tasks and gracefully shut down the service by allowing
     * currently running tasks to complete. (wait for 30 seconds)
     */
    public void shutdown() {
        executerService.shutdown();
        try {
            while (!executerService.awaitTermination(30, TimeUnit.SECONDS)) {

            }
            System.out.println("End of execution");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Execution interrupted " + e.getMessage());
        }
    }


}
