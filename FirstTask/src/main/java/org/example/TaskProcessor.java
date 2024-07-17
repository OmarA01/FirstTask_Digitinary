package org.example;

import java.util.*;
import java.util.concurrent.*;

public class TaskProcessor{

    private ExecutorService executerService;

    public TaskProcessor(int ThreadPoolSize){
        this.executerService = Executors.newFixedThreadPool(ThreadPoolSize);
    }

    public void executeTasks(List<Task> tasks){
        for(Task task : tasks){
            executerService.execute(() -> { // I can make Task extend Thread and pass task here, but I wanted to use lambda somewhere :)
                System.out.println("Starting "+task); // task starts
                try{
                    Thread.sleep(1000);
                }
                catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                    System.err.println("Task interrupted "+e.getMessage());
                }

                task.setStatus(Task.Status.COMPLETED); // task completed
                System.out.println("Completed "+task);
            });

    }

        executerService.shutdown();

        try {
            // Wait until all tasks are finished
            while (!executerService.awaitTermination(1, TimeUnit.SECONDS)) {

            }
            System.out.println("End of execution");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Execution interrupted " + e.getMessage());
        }
    }


}
