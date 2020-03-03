package debr.persistence;

import debr.entities.Player;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class PlayersDAO {
    @Inject
    private EntityManager em;

    public void persist(Player player){
        this.em.persist(player);
    }
}