package com.mif.backend.usecases;

import com.mif.backend.entities.Sponsor;
import com.mif.backend.persistence.SponsorsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Map;

@Model
public class PlayersForSponsors implements Serializable {
    @Inject
    private SponsorsDAO sponsorsDAO;

    @Getter @Setter
    private Sponsor sponsor;


    @PostConstruct
    public void init() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer sponsorId = Integer.parseInt(requestParameters.get("sponsorId"));
        this.sponsor = sponsorsDAO.findOne(sponsorId);
    }

}
