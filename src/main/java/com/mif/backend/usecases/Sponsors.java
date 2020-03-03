package com.mif.backend.usecases;

import com.mif.backend.entities.Sponsor;
import com.mif.backend.persistence.SponsorsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Sponsors {
    @Inject
    private SponsorsDAO sponsorsDAO;

    @Getter @Setter
    private Sponsor sponsorToCreate = new Sponsor();

    @Getter
    private List<Sponsor> sponsors;

    @PostConstruct
    public void init(){
        loadSponsors();
    }

    @Transactional
    public String createSponsor(){
        this.sponsorsDAO.persist(sponsorToCreate);
        return "index?faces-redirect=true";
    }

    private void loadSponsors(){
        this.sponsors = sponsorsDAO.loadAll();
    }
}
