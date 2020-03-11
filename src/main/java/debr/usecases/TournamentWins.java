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
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class TournamentWins implements Serializable {
    @Inject
    private TournamentWinsDAO tournamentWinsDAO;

    @Inject
    private PlayersDAO playersDAO;

    @Inject
    private TournamentsDAO tournamentsDAO;

    @Getter
    @Setter
    private TournamentWin tournamentWinToCreate = new TournamentWin();

    @Getter
    @Setter
    private Player player = new Player();

    @Getter
    @Setter
    private Tournament tournament = new Tournament();

    @Getter
    @Setter
    private String year;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        Integer playerId = Integer.parseInt(requestParameters.get("playerId"));
        Integer tournamentId = Integer.parseInt(requestParameters.get("tournamentId"));
        String year = requestParameters.get("year");

        this.player = playersDAO.findOne(playerId);
        this.tournament = tournamentsDAO.findOne(tournamentId);
        this.year = year;
    }

    @Transactional
    public String createTournamentWin() {
        tournamentWinToCreate.setPlayer(player);
        tournamentWinToCreate.setTournament(tournament);
        tournamentWinToCreate.setYear(year);

        tournamentWinsDAO.persist(tournamentWinToCreate);
        return "/players?faces-redirect=true&tournamentId=" + this.tournament.getId();
    }
}
