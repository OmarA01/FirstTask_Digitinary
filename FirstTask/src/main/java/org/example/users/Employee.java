package org.example.users;
import org.example.Project;
import org.example.Task;

import java.util.ArrayList;
import java.util.List;

public class Employee extends User {

    List<Project> tasks = new ArrayList<>();

    public Employee(String name, String email){
        super(name,email);
    }

}
