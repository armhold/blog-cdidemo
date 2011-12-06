package net.ftlines.blog.cdidemo.model;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import net.ftlines.blog.cdidemo.cdi.Transactional;

@ApplicationScoped
public class EmployeesRepository {

  @Inject
  private EntityManager em;

  public List<Employee> search(String keywords) {
    String hql = "FROM Employee WHERE 1=1 ";
    List<Object> params = new ArrayList<Object>();

    for (String keyword : keywords.split(" ")) {
      final String like = "%" + keyword + "%";
      hql += " AND ( firstName LIKE (?) OR lastName LIKE (?) OR email LIKE (?) )";
      params.add(like);
      params.add(like);
      params.add(like);
    }

    Query query = em.createQuery(hql);
    for (int i = 0; i < params.size(); i++) {
      query.setParameter(i + 1, params.get(i));
    }
    query.setMaxResults(20);
    return query.getResultList();
  }

  public List<Employee> list(EmployeeCriteria criteria, int first, int max) {
    return query("FROM Employee e", criteria, "ORDER BY lastName, firstName").setFirstResult(first).setMaxResults(max)
        .getResultList();
  }

  public int count(EmployeeCriteria criteria) {
    return ((Long) query("SELECT COUNT(*) FROM Employee e", criteria, "").getSingleResult()).intValue();
  }

  private Query query(String base, EmployeeCriteria criteria, String suffix) {
    List<Object> params = new ArrayList<Object>();
    String hql = base + " WHERE 1=1";
    if (criteria.getHireDateMin() != null) {
      hql += " AND e.hireDate>=?";
      params.add(criteria.getHireDateMin());
    }
    if (criteria.getHireDateMax() != null) {
      hql += " AND e.hireDate<=?";
      params.add(criteria.getHireDateMax());
    }
    if (criteria.getTeam() != null) {
      hql += " AND EXISTS (FROM Team t JOIN t.members AS m WHERE m.employee=e AND t=?)";
      params.add(criteria.getTeam());
    }

    Query query = em.createQuery(hql + suffix);
    for (int i = 0; i < params.size(); i++) {
      query.setParameter(i + 1, params.get(i));
    }
    return query;
  }
}
