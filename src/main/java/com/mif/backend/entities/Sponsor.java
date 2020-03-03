package com.mif.backend.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Sponsor.findAll", query = "select s from Sponsor as s")
})
@Table(name = "SPONSOR")
@Getter @Setter
@EqualsAndHashCode(of={"id"})
public class Sponsor implements Serializable {
    public Sponsor(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private String name;

    @OneToMany(mappedBy = "sponsor")
    @Getter @Setter
    private List<Player> players;
}
