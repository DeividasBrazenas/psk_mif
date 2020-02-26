package com.mif.backend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "Player.findAll", query = "select a from Player as a")
})
@Table(name = "PLAYER")
public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private String firstName;

    @Getter @Setter
    private String lastName;

    public Player(){
    }

    public Player(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
