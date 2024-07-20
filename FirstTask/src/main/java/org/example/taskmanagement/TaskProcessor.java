package org.example.taskmanagement;

import java.util.*;
import java.util.concurrent.*;

public class TaskProcessor{

    private ExecutorService executerService;
    private final Object lock = new Object();


    public TaskProcessor(int ThreadPoolSize){
        this.executerService = Executors.newFixedThreadPool(ThreadPoolSize);
    }

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
