package net.ftlines.blog.cdidemo.model;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class TeamRepository {

	@Inject
	private EntityManager em;

	public List<Team> list() {
		return em.createQuery("FROM Team ORDER BY name").getResultList();
	}

}
