package debr.persistence;

import debr.entities.TournamentWin;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class TournamentWinsDAO {
    @Inject
    private EntityManager em;

    public List<TournamentWin> loadAll() {
        return em.createNamedQuery("TournamentWin.findAll", TournamentWin.class).getResultList();
    }

    public void persist(TournamentWin tournamentWin) {
        this.em.persist(tournamentWin);
    }

    public TournamentWin findOne(Integer id) {
        return em.find(TournamentWin.class, id);
    }
}
