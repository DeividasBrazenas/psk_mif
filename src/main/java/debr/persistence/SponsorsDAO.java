package debr.persistence;

import debr.entities.Sponsor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class SponsorsDAO {
    @Inject
    private EntityManager em;

    public List<Sponsor> loadAll(){
        return em.createNamedQuery("Sponsor.findAll", Sponsor.class).getResultList();
    }

    public void persist(Sponsor sponsor){
        this.em.persist(sponsor);
    }

    public Sponsor findOne(Integer id) {
        return em.find(Sponsor.class, id);
    }
}
