package org.example.users;
import org.example.Task;

import java.util.ArrayList;
import java.util.List;

public class FreeLancer extends User {

    List<Task> tasks = new ArrayList<>();

    public FreeLancer(String name, String email){
        super(name,email);
    }
}
