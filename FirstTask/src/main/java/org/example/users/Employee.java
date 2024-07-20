package org.example.users;
import org.example.taskmanagement.Project;

import java.util.ArrayList;
import java.util.List;

public class Employee extends User {

    private List<Project> projects;

    public Employee(String name, String email){
        super(name,email);
        projects = new ArrayList<>();
    }

}
