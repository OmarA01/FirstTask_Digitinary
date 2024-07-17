package org.example.users;

import java.util.ArrayList;
import java.util.List;

abstract public class User {

    private static List<User> users = new ArrayList<>();
    private static int numberOfUser = 0;
    private int id;
    private String name;
    private String email;


    public User(String name, String email){
        if(name == null || name.length() < 3 || name.length() > 40){
            throw new IllegalArgumentException("Name must be between 3 - 40 characters");
        }
        if(email == null || !isValidEmail(email)){
            throw new IllegalArgumentException("Invalid email format. (correct example: Jon@gmail.com)");
        }
        this.id = 0;
        this.name = name;
        this.email = email;
    }

    // email validation
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }


    // ============== managing users ========================================================

    public static void createUser(User user){
        if(!find(user)) {
            user.setId(++numberOfUser);
            users.add(user);
            System.out.println("User created successfully");
        }
        else
            System.out.println("User already exists");
    }

    public static void removeUser(User user){
        if(find(user)){
            users.remove(user);
            System.out.println("User removed successfully");
        }
        else
            System.out.println("User doesn't exist");
    }

    public static boolean find(User user){
        return users.contains(user);
    }

    public static void updateUser(User target, String newName){
        users.stream().filter(user -> user.getEmail().equals(target.getEmail()))
                .findFirst()
                .ifPresentOrElse(
                        user -> {
                            user.setName(newName);
                            System.out.println("User updated successfully");
                            },
                        () -> System.out.println("User doesn't exit")
                        );
    }


    public static void listAllUsers(){ // printing all users in the system
        users.forEach(System.out::println);
    }


    //    ============= setters and getters ===================================================

    public int getId() {
        return id;
    }

    private void setId(int id){
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.length() < 3 || name.length() > 40){
            throw new IllegalArgumentException("Name must be between 3 - 40 characters");
        }
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email == null || !isValidEmail(email)){
            throw new IllegalArgumentException("Invalid email format. (correct example: Jon@gmail.com)");
        }
        this.email = email;
    }


    // override toString to print users
    @Override
    public String toString(){
        return getName()+"  //  "+getEmail();
    }

}
