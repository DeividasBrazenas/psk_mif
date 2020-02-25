package backend.entities;

import java.io.Serializable;

public class Player implements Serializable {
    private String firstName;
    private String lastName;

    public Player(){
    }

    public Player(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String name){
        this.firstName = firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public void setLastName(String name){
        this.lastName = lastName;
    }
}
