package debr.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Player.findAll", query = "select a from Player as a")
})
@Table(name = "PLAYER")
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class Player implements Serializable {
    public Player() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String nickName;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    @ManyToOne
    @JoinColumn(name = "SPONSOR_ID")
    @Getter
    @Setter
    private Sponsor sponsor;

    @OneToMany(mappedBy = "player")
    @Getter
    @Setter
    private List<TournamentWin> wins;
}
