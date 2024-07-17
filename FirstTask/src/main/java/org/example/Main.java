package org.example;

import org.example.users.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void ManageUsers(){
        Scanner input = new Scanner(System.in);
        String response = "";

        while(!response.equals("B")){
            System.out.println("Manage Users =>  Add new User: N \t Update User: U \t Remove User: R \t Back: B");
            response = input.nextLine();
            response = response.toUpperCase();

            System.out.println("Type of user => Employee: M \t FreeLancer: F");
            String typeOfUser = input.nextLine();

            String name,email;
            System.out.println("Enter user name: ");
            name = input.nextLine();
            System.out.println("Enter user email: ");
            email = input.nextLine();

            User newUser;

            try {
                if(typeOfUser.equals("M")) // could use a factory class here (out of scope for this project)
                    newUser = new Employee(name, email);
                else
                    newUser = new FreeLancer(name, email);
            }
            catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                continue;
            }


            switch (response){
                case "N":
                        User.createUser(newUser);
                    break;
                case "R":
                        User.removeUser(newUser);
                    break;
                case "U":
                        System.out.println("Enter new name for this user: ");
                        String newName = input.nextLine();
                        User.updateUser(newUser, newName);
                    break;
                case "B":
                    break;
                default:
                    System.out.println("Incorrect option! Please chose a valid option. \n");

            }
        }
    }

    public static void ManageTasks(){
        Scanner input = new Scanner(System.in);
        String response = "";

        while(!response.equals("B")){
            System.out.println("Manage Tasks =>  Add new Task: N \t Update Task: U \t Remove Task: R \t List tasks: T \t Back: B");
            response = input.nextLine();
            response = response.toUpperCase();

            String title, description, dueDate;
            Task.Status status;
            Task.Priority priority;
            System.out.println("Enter task title: ");
            title = input.nextLine();


            switch (response){
                case "N":
                        System.out.println("Enter task description: ");
                        description= input.nextLine();
                        System.out.println("Enter task status (PENDING, COMPLETED): ");
                        String statusInput = input.nextLine().toUpperCase();
                        status = Task.Status.valueOf(statusInput);
                        System.out.println("Enter task priority (HIGH, MEDIUM, LOW): ");
                        String priorityInput = input.nextLine().toUpperCase();
                        priority = Task.Priority.valueOf(priorityInput);
                        dueDate = input.nextLine();
                        try {
                            Task.addTask(new Task(title, description, status, priority, dueDate));
                        }
                        catch (IllegalArgumentException e){
                            System.out.println(e.getMessage());
                            continue;
                        }

                    break;
                case "R":
                        Task.removeTask(title);
                    break;
                case "U":

                    break;
                case "T":
                        Task.listTasks();
                    break;
                case "B":
                    break;
                default:
                    System.out.println("Incorrect option! Please chose a valid option. \n");

            }
        }
    }

    public static void ManageProjects(){ // no time to implement this, but you get the idea

    }

    public static void Demo(){
        System.out.println("First scenario: running all tasks in the main task array: ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for(int i = 0; i < 100; i++)
            Task.addTask(new Task("task"+i, "description"+i,Task.Status.PENDING,Task.Priority.HIGH, "01/02/2024"));

        execute(Task.getList());



        System.out.println("Second scenario: running all tasks for a specific project: ");
        Project project = new Project("Creating a website");
        project.addTask(new Task("FrontEnd", "Design Interface", Task.Status.PENDING, Task.Priority.HIGH, "10/07/2024"));
        project.addTask(new Task("BackEnd", "Implement system login and manage APIs", Task.Status.PENDING, Task.Priority.HIGH, "14/07/2024"));

        execute(project.getList());

        project.addTask(new Task("Integration", "Integrate with current system", Task.Status.PENDING, Task.Priority.HIGH, "16/07/2024"));
        project.addTask(new Task("Deployment", "deploy and use", Task.Status.PENDING, Task.Priority.HIGH, "20/07/2024"));
        project.removeTask("FrontEnd");
        project.removeTask("BackEnd");

        execute(project.getList());
    }

    public static void execute(List<Task> tasks){
        System.out.println("Tasks: ");
        Task.listTasks(tasks);
        System.out.println("\n ============== Start executing using TaskProcessor (with 10 threads) ======== \n");
        TaskProcessor taskProcessor = new TaskProcessor(10);
        taskProcessor.executeTasks(tasks);
    }

    public static void main(String[] args) {

        System.out.println("Welcome to Task Management System");
        Scanner input = new Scanner(System.in);
        String response = "";

        List<Task> all_tasks = new ArrayList<>();
        List<Project> all_projects = new ArrayList<>();

        while(!response.equals("E")){
            System.out.println("What operation would you like to do:\nDemo Scenario: S \t Manage Users: U \t Manage Tasks: T \t Manage Projects: P \t Exit: E");
            response = input.nextLine();
            response = response.toUpperCase();

            switch (response){
                case "S":
                    Demo();
                    break;
                case "U":
                    ManageUsers();
                    break;
                case "T":
                    ManageTasks();
                    break;
                case "P":
                    ManageProjects();
                    break;
                case "E":
                    break;
                default:
                    System.out.println("Incorrect option! Please chose a valid option. \n");
            }
        }
    }
}