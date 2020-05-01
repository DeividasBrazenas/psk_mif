package debr.usecases;

import debr.interceptors.LoggedInvocation;
import debr.services.INickNameGenerator;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Model
@SessionScoped
public class GeneratePlayerNickname implements Serializable {
    @Inject
    INickNameGenerator nickNameGenerator;

    private Future<String> nickNameGenerationTask = null;

    @LoggedInvocation
    public String generateNickName() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        nickNameGenerationTask = nickNameGenerator.generateNickName();

        return  "/playerDetails.xhtml?faces-redirect=true&playerId=" + requestParameters.get("playerId");
    }

    public boolean isNickNameGenerationRunning() {
        return nickNameGenerationTask != null && !nickNameGenerationTask.isDone();
    }

    public String getNickNameGenerationStatus() throws ExecutionException, InterruptedException {
        if (nickNameGenerationTask == null) {
            return null;
        } else if (isNickNameGenerationRunning()) {
            return "Nick name generation in progress";
        }
        return "Suggested nick name: " + nickNameGenerationTask.get();
    }
}
