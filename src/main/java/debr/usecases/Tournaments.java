package debr.usecases;

import debr.entities.Tournament;
import debr.persistence.TournamentsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Tournaments {
    @Inject
    private TournamentsDAO tournamentsDAO;

    @Getter
    @Setter
    private Tournament tournamentToCreate = new Tournament();

    @Getter
    private List<Tournament> tournaments;

    @PostConstruct
    public void init() {
        loadTournaments();
    }

    @Transactional
    public String createTournament() {
        this.tournamentsDAO.persist(tournamentToCreate);
        return "tournaments?faces-redirect=true";
    }

    private void loadTournaments() {
        this.tournaments = tournamentsDAO.loadAll();
    }
}
