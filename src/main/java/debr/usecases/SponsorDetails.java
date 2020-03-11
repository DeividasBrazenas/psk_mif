package debr.usecases;

import debr.entities.Player;
import debr.entities.Sponsor;
import debr.persistence.PlayersDAO;
import debr.persistence.SponsorsDAO;
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
public class SponsorDetails implements Serializable {
    @Inject
    private PlayersDAO playersDAO;

    @Inject
    private SponsorsDAO sponsorsDAO;

    @Getter
    private Sponsor sponsor;

    @Getter
    @Setter
    private String playerId;

    @PostConstruct
    public void init() {
        loadSponsor();
    }

    private void loadSponsor() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer sponsorId = Integer.parseInt(requestParameters.get("sponsorId"));

        this.sponsor = sponsorsDAO.findOne(sponsorId);
    }

    @Transactional
    public String addSponsorForPlayer() {
        Player player = playersDAO.findOne(Integer.parseInt(playerId));

        player.setSponsor(sponsor);
        playersDAO.persist(player);

        return "sponsorDetails?sponsorId=" + sponsor.getId() + "&faces-redirect=true";
    }
}
