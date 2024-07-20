package org.example.users;
import org.example.taskmanagement.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FreeLancer extends User {

    private List<Task> tasks;

    public FreeLancer(String name, String email){
        super(name,email);
        tasks = new ArrayList<>();
    }

    /**
     * method to add a task to the list of tasks
     * @param task: task to be added to the list
     */
    public void addTask(Task task) {
        if(!tasks.contains(task)) {
            tasks.add(task);
            System.out.println("Task added successfully");
        }
        else
            System.out.println("Task already exits");
    }

    /**
     * method to remove task from the list of tasks
     * @param task: task to be removed from the list
     */
    public void removeTask(Task task) {
        tasks.stream().filter(t -> t.getId() == task.getId())
                .findFirst().ifPresentOrElse(t -> {
                    tasks.remove(task);
                    System.out.println("Task removed successfully");
                }, () -> System.out.println("Task doesn't exist"));
    }

    /**
     * method to update task inside the list of tasks (with specific id)
     * @param id:           Id of the task to be updated
     * @param title:        New title for task
     * @param description:  New description for task
     * @param status:       New status for task
     * @param priority:     New priority for task
     * @param dueDate:      New due date for task
     */
    public void updateTask(int id, String title, String description, String status, String priority, LocalDate dueDate) {
        tasks.stream().filter(t -> t.getId() == id)
                .findFirst().ifPresentOrElse(t -> {
                    t.setTitle(title);
                    t.setDescription(description);
                    t.setStatus(status);
                    t.setPriority(priority);
                    t.setDueDate(dueDate);
                    System.out.println("Task updated successfully");
                }, () -> System.out.println("Task doesn't exist"));
    }

    /**
     * method to print tasks inside the list of tasks
     */
    public void listTasks() {
        tasks.forEach(task -> System.out.print(task.getTitle()+" - "));
    }



}
