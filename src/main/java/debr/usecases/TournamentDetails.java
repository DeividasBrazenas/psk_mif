package debr.usecases;

import debr.entities.Player;
import debr.entities.Tournament;
import debr.entities.TournamentWin;
import debr.persistence.PlayersDAO;
import debr.persistence.TournamentWinsDAO;
import debr.persistence.TournamentsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
@RequestScoped
public class TournamentDetails implements Serializable {
    @Inject
    private TournamentsDAO tournamentsDAO;

    @Inject
    private TournamentWinsDAO tournamentWinsDAO;

    @Inject
    private PlayersDAO playersDAO;

    @Getter
    private Tournament tournament;

    @Getter
    @Setter
    private String playerId;

    @Getter
    @Setter
    private String yearWon;

    @PostConstruct
    public void init() {
        loadTournament();
    }

    private void loadTournament() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer tournamentId = Integer.parseInt(requestParameters.get("tournamentId"));

        this.tournament = tournamentsDAO.findOne(tournamentId);
    }

    @Transactional
    public String addTournamentWinForPlayer() {
        Player player = playersDAO.findOne(Integer.parseInt(playerId));

        TournamentWin tournamentWin = new TournamentWin();
        tournamentWin.setPlayer(player);
        tournamentWin.setTournament(tournament);
        tournamentWin.setYear(yearWon);

        tournamentWinsDAO.persist(tournamentWin);

        return "tournamentDetails?tournamentId=" + tournament.getId() + "&faces-redirect=true";
    }
}
