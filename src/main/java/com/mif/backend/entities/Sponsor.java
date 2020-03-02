package com.mif.backend.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SPONSOR")
public class Sponsor {
    public Sponsor(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private String id;

    @Getter @Setter
    private String name;

    @OneToMany(mappedBy = "team")
    @Getter @Setter
    private List<Player> players;
}
