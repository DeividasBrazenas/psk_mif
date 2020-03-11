package debr.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "TournamentWin.findAll", query = "select a from TournamentWin as a")
})
@Table(name = "TOURNAMENT_WIN")
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class TournamentWin {
    public TournamentWin() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "PLAYER_ID")
    @Getter
    @Setter
    private Player player;

    @ManyToOne
    @JoinColumn(name = "TOURNAMENT_ID")
    @Getter
    @Setter
    private Tournament tournament;

    @Getter
    @Setter
    private String year;
}
