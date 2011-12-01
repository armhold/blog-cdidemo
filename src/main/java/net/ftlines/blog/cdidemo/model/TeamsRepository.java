package net.ftlines.blog.cdidemo.model;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class TeamsRepository {

  @Inject
  private EntityManager em;

  public List<Team> list(int first, int max) {
    return em.createQuery("FROM Team ORDER BY name").setFirstResult(first).setMaxResults(max).getResultList();
  }

  public int count() {
    return ((Long) em.createQuery("SELECT COUNT(*) FROM Team").getSingleResult()).intValue();
  }

}
