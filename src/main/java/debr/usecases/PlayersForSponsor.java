package debr.usecases;

import debr.entities.Player;
import debr.entities.Sponsor;
import debr.persistence.PlayersDAO;
import debr.persistence.SponsorsDAO;
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
public class PlayersForSponsor implements Serializable {
    @Inject
    private SponsorsDAO sponsorsDAO;

    @Inject
    private PlayersDAO playersDAO;

    @Getter @Setter
    private Sponsor sponsor;

    @Getter @Setter
    private Player playerToCreate = new Player();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer sponsorId = Integer.parseInt(requestParameters.get("sponsorId"));
        this.sponsor = sponsorsDAO.findOne(sponsorId);
    }

    @Transactional
    public String createPlayer() {
        playerToCreate.setSponsor(this.sponsor);
        playersDAO.persist(playerToCreate);
        return "/players?faces-redirect=true";
    }
}