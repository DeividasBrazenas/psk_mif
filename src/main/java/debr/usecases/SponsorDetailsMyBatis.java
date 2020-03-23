package debr.usecases;

import debr.myBatis.dao.PlayerMapper;
import debr.myBatis.dao.SponsorMapper;
import debr.myBatis.model.Player;
import debr.myBatis.model.Sponsor;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Model
@RequestScoped
public class SponsorDetailsMyBatis implements Serializable {
    @Inject
    private PlayerMapper playerMapper;

    @Inject
    private SponsorMapper sponsorMapper;

    @Getter
    private Sponsor sponsor;

    @Getter
    private List<Player> sponsorPlayers;

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

        this.sponsor = sponsorMapper.selectByPrimaryKey(sponsorId);
        this.sponsorPlayers = playerMapper.selectBySponsorId(sponsorId);
    }

    @Transactional
    public String addSponsorForPlayer() {
        Player player = playerMapper.selectByPrimaryKey(Integer.parseInt(playerId));

        player.setSponsorId(sponsor.getId());
        playerMapper.updateByPrimaryKey(player);

        return "sponsorDetails?sponsorId=" + sponsor.getId() + "&faces-redirect=true";
    }
}
