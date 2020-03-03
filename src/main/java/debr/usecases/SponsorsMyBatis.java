package debr.usecases;

import debr.myBatis.dao.SponsorMapper;
import debr.myBatis.model.Sponsor;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class SponsorsMyBatis {
    @Inject
    private SponsorMapper sponsorMapper;

    @Getter
    private List<Sponsor> sponsors;

    @Getter @Setter
    private Sponsor sponsorToCreate = new Sponsor();

    @PostConstruct
    public void init() {
        this.loadSponsors();
    }

    private void loadSponsors() {
        this.sponsors = sponsorMapper.selectAll();
    }

    @Transactional
    public String createSponsor() {
        sponsorMapper.insert(sponsorToCreate);
        return "/myBatis/sponsors?faces-redirect=true";
    }
}