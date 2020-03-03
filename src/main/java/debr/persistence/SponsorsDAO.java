package debr.persistence;

import debr.entities.Sponsor;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class SponsorsDAO {
    @PersistenceContext
    @Setter
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
