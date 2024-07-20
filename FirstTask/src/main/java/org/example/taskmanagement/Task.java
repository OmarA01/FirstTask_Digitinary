package org.example.taskmanagement;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Task {

    /**
     * Enum values for status attribute
     */
    public enum Status {
        PENDING,
        InProgress,
        Completed
    }

    /**
     * Enum values for priority attribute
     */
    public enum Priority {
        HIGH,
        MEDIUM,
        LOW
    }

    /**
     * numberOfTasks is static in order to make id auto generated, incremented each time
     * a task is added to a list
     */
    private static int numberOfTasks = 0;

    /**
     * An id for each task, a title, a description, a status, a priority & a due date
     */
    private int id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private LocalDate dueDate;

    /**
     * Constructor with validation to initialize a new task
     * @param title:        Title of the task
     * @param description:  Description of the task
     * @param status        Status of the task
     * @param priority      Priority of the task
     * @param dueDate       Due Date of the task
     * id is 0 until task is added to a list (used)
     */

    public Task(String title, String description, String status, String priority, String  dueDate){
        if(title == null || description == null || status == null || priority == null || dueDate == null)
            throw new IllegalArgumentException("some attributes are null. Please provide valid attributes");

        if (!dueDate.matches("^(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/\\d{4}$"))
            throw new IllegalArgumentException("Due Date should match this format dd/mm/yyyy");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dueDate, formatter);

        this.id = 0;
        this.title = title;
        this.description = description;
        setStatus(status);
        setPriority(priority);
        this.dueDate = date;
    }

    // ========== managing tasks ===========================================================

    /**
     * method to add task to a list, thus incrementing numberOfTasks and assigning it to current task id
     * @param tasks: Target list to add task to
     */
    public void addTask(List<Task> tasks) {
        if(!tasks.contains(this)) {
            this.setId(++numberOfTasks);
            tasks.add(this);
            System.out.println("Task added successfully");
        }
        else
            System.out.println("Task already exits");
    }

    /**
     * method to remove task from a list
     * @param tasks: Target list to remove task from
     */
    public void removeTask(List<Task> tasks) {
        tasks.stream().filter(task -> task.getId() == this.id)
                .findFirst().ifPresentOrElse(task -> {
                    tasks.remove(task);
                    System.out.println("Task removed successfully");
                }, () -> System.out.println("Task doesn't exist"));
    }

    /**
     * method to update user info
     * @param title:        New title for task
     * @param description:  New description for task
     * @param status:       New status for task
     * @param priority:     New priority for task
     * @param dueDate:      New due date for task
     */

    public void updateTask(String title, String description, String status, String priority, LocalDate dueDate) {
        this.setTitle(title);
        this.setDescription(description);
        this.setStatus(status);
        this.setPriority(priority);
        this.setDueDate(dueDate);
    }

    /**
     * method to print tasks inside a specific list
     * @param tasks: Target list to print tasks inside
     */

    public void listTasks(List<Task> tasks) {
        tasks.forEach(task -> System.out.print(task.getTitle()+" - "));
    }

    /*public boolean find(String title){
        return tasks.stream().anyMatch(task -> task.getTitle().equals(title));
    }

    public List<Task> filter(Task.Status status){
        return tasks.stream().filter(task -> task.getStatus() == status).collect(Collectors.toList());
    }

    public List<Task> filter(Task.Priority priority){
        return tasks.stream().filter(task -> task.getPriority() == priority).collect(Collectors.toList());
    }

    public List<Task> filter(LocalDate dueDate){
        return tasks.stream().filter(task -> task.getDueDate()  == dueDate).collect(Collectors.toList());
    }*/


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

    public String getStatus() {
        return status.toString();
    }

    public void setStatus(String status) {
        status = status.toUpperCase();
        if(status.equals("P")){
            this.status = Status.PENDING;
        }
        else if(status.equals("I")){
            this.status = Status.InProgress;
        }
        else this.status = Status.Completed;
    }

    public String getPriority() {
        return priority.toString();
    }

    public void setPriority(String priority) {
        priority = priority.toUpperCase();
        if(priority.equals("H")){
            this.priority = Priority.HIGH;
        }
        else if(priority.equals("M")){
            this.priority = Priority.MEDIUM;
        }
        else this.priority = Priority.LOW;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }


    /**
     * override toString to print tasks (as a string)
     * @return A string representation of a task
     */
    @Override
    public String toString(){
        return "----- Task"+getId()+" -> title: "+getTitle()+"\nDescription: "+
                getDescription()+"\nStatus: "+getStatus()+", priority: "+
                getPriority()+"\ndue Date: "+getDueDate();
    }
}
