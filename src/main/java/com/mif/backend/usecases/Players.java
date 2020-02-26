package com.mif.backend.usecases;

import com.mif.backend.entities.Player;
import com.mif.backend.persistence.PlayersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Players {
    @Inject
    private PlayersDAO playersDAO;

    private Player playerToCreate = new Player();

    private List<Player> players;

    @PostConstruct
    public void init() {
        loadPlayers();
    }

    private void loadPlayers() {
        this.players = playersDAO.loadAll();
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    @Transactional
    public String createPlayer() {
        this.playersDAO.persist(playerToCreate);
        return "success";
    }

    public Player getPlayerToCreate() {
        return playerToCreate;
    }

    public void setPlayerToCreate(Player playerToCreate) {
        this.playerToCreate = playerToCreate;
    }
}
