package net.ftlines.blog.cdidemo.model;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class HomesRepository
{

    @Inject
    private EntityManager em;

    public void saveHome(Sport home)
    {
        em.persist(home);
    }

}
