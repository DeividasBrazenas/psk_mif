package com.mif.backend.persistence;

import com.mif.backend.entities.Player;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class PlayersDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Player> loadAll() {
        return em.createNamedQuery("Player.findAll", Player.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Player player){
        this.em.persist(player);
    }
}