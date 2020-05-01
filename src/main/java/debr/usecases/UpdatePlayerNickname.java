package debr.usecases;

import debr.entities.Player;
import debr.interceptors.LoggedInvocation;
import debr.persistence.PlayersDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
@SessionScoped
public class UpdatePlayerNickname implements Serializable {
    @Inject
    private PlayersDAO playersDAO;

    @Getter
    private Player player;

    @Getter
    @Setter
    private String nickName;

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
    @LoggedInvocation
    public String updatePlayerNickname() {
        player.setNickName(nickName);

        try{
            playersDAO.update(this.player);
        } catch (OptimisticLockException e) {
            this.player = playersDAO.findOne(this.player.getId());
            return "/playerDetails.xhtml?faces-redirect=true&playerId=" + player.getId() + "&error=optimistic-lock-exception";
        } catch (Exception e){
            System.out.println("blogai");
        }

        return "playerDetails?playerId=" + player.getId() + "&faces-redirect=true";
    }
}
