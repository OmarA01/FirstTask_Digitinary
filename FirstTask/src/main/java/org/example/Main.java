package org.example;

import org.example.taskmanagement.Project;
import org.example.taskmanagement.Task;
import org.example.taskmanagement.TaskProcessor;
import org.example.users.User;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void execute(List<Task> tasks){
        System.out.println("Tasks: ");

        System.out.println("\n ============== Start executing using TaskProcessor (with 10 threads) ======== \n");
        TaskProcessor taskProcessor = new TaskProcessor(10);
        taskProcessor.executeTasks(tasks);
        taskProcessor.shutdown();
    }

    public static void executeBasedOnPriority(Project project){

        System.out.println("\n ============== Start executing using TaskProcessor based on priority (with 10 threads) ======== \n");
        TaskProcessor taskProcessor = new TaskProcessor(10);
        taskProcessor.executeTasks(project.filterByPriority());
        taskProcessor.shutdown();
    }

    public static void executeBasedOnStatus(Project project){

        System.out.println("\n ============== Start executing using TaskProcessor based on status (with 10 threads) ======== \n");
        TaskProcessor taskProcessor = new TaskProcessor(10);
        for(Task t : project.filterByStatus()){
            System.out.print(t.getTitle()+" -- ");
        }
        taskProcessor.executeTasks(project.filterByStatus());
        taskProcessor.shutdown();
    }

    public static void main(String[] args) {


        System.out.println("Welcome to Task Management System");
        Scanner input = new Scanner(System.in);
        String response = "";

        List<Task> all_tasks = new ArrayList<>();
        Project project = new Project("MyProject");
        String status,priority;
        for(int i = 1; i<=20; i++){
            if(i%2 == 0){
                priority = "L";
                status = "P";
            }
            else if(i%3 == 0){
                priority = "M";
                status = "I";
            }
            else{
                priority = "H";
                status = "C";
            }
            try {
                Task task = new Task("Task" + i, "Description" + i, status, priority, "01/07/2024");
                project.addTask(task);
                task.addTask(all_tasks);
            }
            catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        
        execute(all_tasks);
        //executeBasedOnPriority(project);
        //executeBasedOnStatus(project);

    }
}
