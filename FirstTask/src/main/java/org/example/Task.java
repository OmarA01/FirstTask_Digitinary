package org.example;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Task {

    public enum Status {
        PENDING,
        COMPLETED
    }

    public enum Priority {
        HIGH,
        MEDIUM,
        LOW
    }

    private static int numberOfTasks = 0;
    private static List<Task> tasks = new ArrayList<>();
    private int id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private LocalDate dueDate;

    public Task(String title, String description, Status status, Priority priority, String  dueDate){
        this.id = 0;
        if(title == null || description == null || status == null || priority == null || dueDate == null)
            throw new IllegalArgumentException("some attributes are null. Please provide valid attributes");

        if (!dueDate.matches("^(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/\\d{4}$"))
            throw new IllegalArgumentException("Due Date should match this format dd/mm/yyyy");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dueDate, formatter);

        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dueDate = date;
    }

    // ========== managing tasks ===========================================================


    public static void addTask(Task task) {
        if(!find(task.getTitle())) {
            task.setId(++numberOfTasks);
            tasks.add(task);
            System.out.println("Task added successfully");
        }
        else
            System.out.println("Task already exits");
    }


    public static void removeTask(String title) {
        tasks.stream().filter(task -> task.getTitle().equals(title))
                .findFirst().ifPresentOrElse(task -> {
                    tasks.remove(task);
                    System.out.println("Task removed successfully");
                }, () -> System.out.println("Task doesn't exist"));
    }


    public static void updateTask(String title) {

    }


    public static void listTasks() {
        tasks.forEach(task -> System.out.print(task.getTitle()+" - "));
    }

    public static void listTasks(List<Task> tasks) {
        tasks.forEach(task -> System.out.print(task.getTitle()+" - "));
    }

    public static boolean find(String title){
        return tasks.stream().anyMatch(task -> task.getTitle().equals(title));
    }

    public static List<Task> filter(Task.Status status){
        return tasks.stream().filter(task -> task.getStatus() == status).collect(Collectors.toList());
    }

    public static List<Task> filter(Task.Priority priority){
        return tasks.stream().filter(task -> task.getPriority() == priority).collect(Collectors.toList());
    }

    public static List<Task> filter(LocalDate dueDate){
        return tasks.stream().filter(task -> task.getDueDate()  == dueDate).collect(Collectors.toList());
    }

    // ========== setters and getters =======================================================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public static List<Task> getList(){
        return tasks;
    }

    // override toString to print tasks
    @Override
    public String toString(){
        return "----- Task"+getId()+" -> title: "+getTitle()+"\nDescription: "+
                getDescription()+"\nStatus: "+getStatus()+", priority: "+
                getPriority()+"\ndue Date: "+getDueDate();
    }
}
