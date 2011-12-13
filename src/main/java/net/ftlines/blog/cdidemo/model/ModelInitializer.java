package net.ftlines.blog.cdidemo.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import net.ftlines.blog.cdidemo.jpa.EntityManagerFactoryCreatedEvent;

import org.hibernate.ejb.EntityManagerFactoryImpl;
import org.hibernate.impl.SessionFactoryImpl;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class ModelInitializer {
  private final String[] firstNames = { "Jacob", "Emily", "Michael", "Sarah", "Matthew", "Brianna", "Nicholas",
      "Samantha", "Christopher", "Hailey", "Abner", "Abby", "Joshua", "Douglas", "Jack", "Keith", "Gerald", "Samuel",
      "Willie", "Larry", "Jose", "Timothy", "Sandra", "Kathleen", "Pamela", "Virginia", "Debra", "Maria", "Linda" };
  private final String[] lastNames = { "Smith", "Johnson", "Williams", "Jones", "Brown", "Donahue", "Bailey", "Rose",
      "Allen", "Black", "Davis", "Clark", "Hall", "Lee", "Baker", "Gonzalez", "Nelson", "Moore", "Wilson", "Graham",
      "Fisher", "Cruz", "Ortiz", "Gomez", "Murray" };

  private final String[] teamNames = { "Raging Kickers", "Rocky Tigers", "Demolition Hawks", "Lightning Bulls" };
  private final String[] positionNames = { "Designer", "Developer", "Tester" };

  private static <T> T random(T[] values) {
    return values[(int) (Math.random() * values.length)];
  }

  private static int random(int min, int max) {
    return (int) (Math.random() * (double) (max - min)) + min;
  }

  public void initialize(@Observes EntityManagerFactoryCreatedEvent created) {

    EntityManagerFactory emf = created.getEntityManagerFactory();
    EntityManager em = emf.createEntityManager();

    dumpSchema(em);

    em.getTransaction().begin();

    // prove that we can persist sports outside of wicket
    Sport sport = new Sport();
    sport.setName("baseball");
    System.out.println("saveSport: " + sport);
    em.persist(sport);

    sport = new Sport();
    sport.setName("hockey");
    System.out.println("saveSport: " + sport);
    em.persist(sport);

    Position positions[] = new Position[positionNames.length];
    for (int i = 0; i < positions.length; i++) {
      Position p = new Position();
      p.setName(positionNames[i]);
      positions[i] = p;
      em.persist(p);
    }

    Employee employees[] = new Employee[30];
    for (int i = 0; i < employees.length; i++) {
      Employee e = new Employee();
      e.setFirstName(random(firstNames));
      e.setLastName(random(lastNames));
      String email = e.getFirstName() + "." + e.getLastName() + "@firm.com";
      email = email.toLowerCase();
      e.setEmail(email);
      e.setHireDate(new Date(random(100, 111), random(0, 11), random(1, 28), 0, 0, 0));
      employees[i] = e;
      em.persist(e);
    }

    Team[] teams = new Team[teamNames.length];
    for (int i = 0; i < teamNames.length; i++) {
      Team t = new Team();
      t.setName(teamNames[i]);
      teams[i] = t;
      em.persist(t);
    }

    List<Employee> outsiders = new ArrayList<Employee>(Arrays.asList(employees));
    for (int i = 0; i < employees.length * 2 / 3; i++) {
      Employee e = outsiders.get(random(0, outsiders.size() - 1));
      outsiders.remove(e);

      Team t = random(teams);
      Position p = random(positions);

      Member member = new Member();

      member.setEmployee(e);
      // member.setTeam(t);
      member.setPosition(p);
      member.setEffectiveDate(new Date(random(100, 111), random(0, 11), random(1, 28), 0, 0, 0));
      t.getMembers().add(member);
    }

    em.getTransaction().commit();
  }

  private void dumpSchema(EntityManager em) {

    try {
      System.out.println("\n=========== SCHEMA =============\n");
      Field exp = SessionFactoryImpl.class.getDeclaredField("schemaExport");
      exp.setAccessible(true);
      SchemaExport export = (SchemaExport) exp.get(((EntityManagerFactoryImpl) em.getEntityManagerFactory())
          .getSessionFactory());
      export.execute(true, false, false, true);
      System.out.println("\n========== END SCHEMA ===========\n");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
