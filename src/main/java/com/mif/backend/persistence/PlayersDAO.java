package com.mif.backend.persistence;

import com.mif.backend.entities.Player;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class PlayersDAO {
    @PersistenceContext
    private EntityManager em;

    public void persist(Player player){
        this.em.persist(player);
    }
}