package net.ftlines.blog.cdidemo.model;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class PositionsRepository {

  @Inject
  EntityManager em;

  public List<Position> list() {
    return em.createQuery("FROM Position ORDER BY name").getResultList();
  }
}
