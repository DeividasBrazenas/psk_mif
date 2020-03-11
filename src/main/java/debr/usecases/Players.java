package debr.usecases;

import debr.entities.Player;
import debr.entities.Sponsor;
import debr.persistence.PlayersDAO;
import debr.persistence.SponsorsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class Players implements Serializable {
    @Inject
    private SponsorsDAO sponsorsDAO;

    @Inject
    private PlayersDAO playersDAO;

    @Getter
    @Setter
    private Sponsor sponsor;

    @Getter
    private List<Player> players;

    @Getter
    @Setter
    private Player playerToCreate = new Player();

    @PostConstruct
    public void init() {
        loadPlayers();
    }

    @Transactional
    public String createPlayer() {
        this.playersDAO.persist(playerToCreate);
        return "players?faces-redirect=true";
    }

    private void loadPlayers() {
        this.players = playersDAO.loadAll();
    }
}
