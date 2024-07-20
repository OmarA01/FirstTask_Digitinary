package org.example.users;

import java.util.ArrayList;
import java.util.List;

/**
 *  represent a user in the system
 */

abstract public class User {

    /**
     * numberOfUser is static in order to make id auto generated, incremented each time
     * a user is added to a list
     */
    private static int numberOfUser = 0;
    /**
     * an id for each user, a name, and an email
     */
    private int id;
    private String name;
    private String email;


    /**
     * Constructor with validation to initialize a new User
     * @param name:     name of the user
     * @param email:    email of the user
     * id is 0 until user is added to a list (used)
     */
    protected User(String name, String email){
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

    /**
     * method to validate that email matches correct format
     * @param email: email of the user
     * @return true if email is valid (ex: jon@gmail.com), false if not (ex: @gmail.com)
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }


    // ============== managing users ========================================================

    /**
     * method to add this user to a list, thus incrementing numberOfUser and assigning it to current user id
     * @param users: Target list to add user to
     */
    public void addUser(List<User> users){
        if(!users.contains(this)) {
            this.setId(++numberOfUser);
            users.add(this);
            System.out.println("User created successfully");
        }
        else
            System.out.println("User already exists");
    }

    /**
     * method to remove this user from a list
     * @param users: Target list to remove user from
     */
    public void removeUser(List<User> users){
        if(users.contains(this)){
            users.remove(this);
            System.out.println("User removed successfully");
        }
        else
            System.out.println("User doesn't exist");
    }

    /**
     * method to update user info (name & email)
     * @param newName: new name for user
     * @param email new email for user
     */
    public void updateUser(String newName, String email){
        this.setName(newName);
        this.setEmail(email);
    }

    // ============== setters and getters ===================================================

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


    /**
     * @return a representational string of user
     */
    @Override
    public String toString(){
        return getName()+"  //  "+getEmail();
    }

}
