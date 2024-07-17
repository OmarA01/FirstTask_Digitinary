package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project{

    private List<Task> tasks;
    private int tasks_in_project;
    private String name;

    public Project(String name){
        this.tasks_in_project = 0;
        this.name = name;
        tasks = new ArrayList<>();
    }

    // ======== manage tasks in project ==================================================

    public void addTask(Task task){
        if(!find(task.getTitle())) {
            task.setId(++tasks_in_project);
            tasks.add(task);
        }
        else
            System.out.println("Task \""+task.getTitle()+"\" already exists in "+this.getName()+" project");
    }


    public void removeTask(String title) {
        tasks.stream().filter(task -> task.getTitle().equals(title))
                .findFirst().ifPresentOrElse(task -> {
                    tasks.remove(task);
                    System.out.println("Task \""+task.getTitle()+"\" removed successfully from \""+this.getName()+"\" project");
                }, () -> System.out.println("Task doesn't exist"));
    }


    public void updateTask(String name) {

    }


    public void listTasks(){
        tasks.forEach(task -> System.out.print(task.getTitle()+" - "));    }


    public boolean find(String title){
        return tasks.stream().anyMatch(task -> task.getTitle().equals(title));
    }

    public List<Task> getList(){
        return tasks;
    }

    // ============ setters and getters =============================================
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
