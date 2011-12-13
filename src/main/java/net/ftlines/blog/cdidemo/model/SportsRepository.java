package net.ftlines.blog.cdidemo.model;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class SportsRepository
{

    @Inject
    private EntityManager em;

    public void saveSport(Sport sport)
    {
        System.out.println("saveSport: " + sport);

        em.persist(sport);
    }

}
