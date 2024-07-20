package org.example.taskmanagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Project{
    /**
     *  A list of tasks for each project instance
     *  tasks_in_project: number of tasks in the project
     *  name:             name of project
     */
    private List<Task> tasks;
    private int tasks_in_project;
    private String name;


    /**
     * Constructor to initialize new project
     * @param name: name of project
     */
    public Project(String name){
        this.tasks_in_project = 0;
        this.name = name;
        tasks = new ArrayList<>();
    }

    /**
     * method to add a task to project
     * @param task: task to be added to the list
     */
    public void addTask(Task task){
        if(!find(task.getTitle())) {
            task.setId(++tasks_in_project);
            tasks.add(task);
        }
        else
            System.out.println("Task \""+task.getTitle()+"\" already exists in "+this.getName()+" project");
    }

    /**
     * method to remove a task from project
     * @param title: title of the task to be removed
     */
    public void removeTask(String title) {
        tasks.stream().filter(task -> task.getTitle().equals(title))
                .findFirst().ifPresentOrElse(task -> {
                    tasks.remove(task);
                    System.out.println("Task \""+task.getTitle()+"\" removed successfully from \""+this.getName()+"\" project");
                }, () -> System.out.println("Task doesn't exist"));
    }


    /**
     * method to print tasks in project
     */
    public void listTasks(){
        tasks.forEach(task -> System.out.print(task.getTitle()+" - "));
    }

    /**
     * method to get the list of tasks in project
     * @return List object of tasks
     */
    public List<Task> getList(){
        return tasks;
    }

    public List<Task> filterByPriority() {
        return tasks.stream().filter(task -> task.getPriority().equals("HIGH")).collect(Collectors.toList());
    }

    public List<Task> filterByStatus() {
        return tasks.stream().filter(task -> task.getStatus().equals("PENDING")).collect(Collectors.toList());
    }

    /**
     * method to find a task with a specific title
     * @param title: title of the task to search for
     * @return task object with required title
     */
    public boolean find(String title){
        return tasks.stream().anyMatch(task -> task.getTitle().equals(title));
    }



    // ============ setters and getters =============================================
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
