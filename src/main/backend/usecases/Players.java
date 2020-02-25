package backend.usecases;

import backend.entities.Player;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import java.util.ArrayList;
import java.util.List;

@Model
public class Players {
    private List<Player> players;

    @PostConstruct
    public void init(){
        loadPlayers();
    }

    private void loadPlayers() {
        List<Player> players = new ArrayList<Player>();

        players.add(new Player("Roger", "Federer"));
        players.add(new Player("Rafael", "Nadal"));
        players.add(new Player("Novak", "Djokovic"));

        this.players = players;
    }

    public List<Player> getPlayers(){
        return this.players;
    }
}
