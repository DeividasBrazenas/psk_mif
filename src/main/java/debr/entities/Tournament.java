package debr.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Tournament.findAll", query = "select a from Tournament as a")
})
@Table(name = "TOURNAMENT")
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class Tournament implements Serializable {
    public Tournament() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @OneToMany(mappedBy = "tournament")
    @Getter
    @Setter
    private List<TournamentWin> wins;

    @Getter
    @Setter
    private String name;
}
