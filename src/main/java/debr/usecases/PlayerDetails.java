package debr.usecases;

import debr.entities.Player;
import debr.entities.Sponsor;
import debr.entities.Tournament;
import debr.entities.TournamentWin;
import debr.persistence.PlayersDAO;
import debr.persistence.SponsorsDAO;
import debr.persistence.TournamentWinsDAO;
import debr.persistence.TournamentsDAO;
import debr.services.Earnings.IEarningsGenerator;
import debr.services.Earnings.Production;
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
public class PlayerDetails implements Serializable {
    @Inject
    private PlayersDAO playersDAO;

    @Inject
    private SponsorsDAO sponsorsDAO;

    @Inject
    private TournamentsDAO tournamentsDAO;

    @Inject
    private TournamentWinsDAO tournamentWinsDAO;

    @Inject
    @Production
    IEarningsGenerator earningsGenerator;

    @Getter
    private Player player;

    @Getter
    @Setter
    private String sponsorId;

    @Getter
    @Setter
    private String tournamentId;

    @Getter
    @Setter
    private String yearWon;

    @PostConstruct
    public void init() {
        loadPlayer();
    }

    private void loadPlayer() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer playerId = Integer.parseInt(requestParameters.get("playerId"));

        this.player = playersDAO.findOne(playerId);
    }

    @Transactional
    public String addSponsorForPlayer() {
        Sponsor sponsor = sponsorsDAO.findOne(Integer.parseInt(sponsorId));

        this.player.setSponsor(sponsor);
        playersDAO.persist(player);

        return "playerDetails?playerId=" + player.getId() + "&faces-redirect=true";
    }

    @Transactional
    public String addTournamentWinForPlayer() {
        Tournament tournament = tournamentsDAO.findOne(Integer.parseInt(tournamentId));

        TournamentWin tournamentWin = new TournamentWin();
        tournamentWin.setTournament(tournament);
        tournamentWin.setPlayer(player);
        tournamentWin.setYear(yearWon);

        tournamentWinsDAO.persist(tournamentWin);

        return "playerDetails?playerId=" + player.getId() + "&faces-redirect=true";
    }

    public String getEarnings() {
        return earningsGenerator.generateEarnings();
    }
}
