package net.ftlines.blog.cdidemo.model;

import java.util.Date;

import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import net.ftlines.blog.cdidemo.jpa.EntityManagerFactoryCreatedEvent;

public class ModelInitializer {
    private final String[] firstNames = { "Jacob", "Emily", "Michael", "Sarah", "Matthew", "Brianna", "Nicholas",
            "Samantha", "Christopher", "Hailey", "Abner", "Abby", "Joshua", "Douglas", "Jack", "Keith", "Gerald",
            "Samuel", "Willie", "Larry", "Jose", "Timothy", "Sandra", "Kathleen", "Pamela", "Virginia", "Debra",
            "Maria", "Linda" };
    private final String[] lastNames = { "Smith", "Johnson", "Williams", "Jones", "Brown", "Donahue", "Bailey", "Rose",
            "Allen", "Black", "Davis", "Clark", "Hall", "Lee", "Baker", "Gonzalez", "Nelson", "Moore", "Wilson",
            "Graham", "Fisher", "Cruz", "Ortiz", "Gomez", "Murray" };

    private final String[] teamNames = { "Raging Kickers", "Rocky Tigers", "Demolition Hawks", "Lightning Bulls" };

    private static <T> T random(T[] values) {
        return values[(int) (Math.random() * values.length)];
    }

    private static int random(int min, int max) {
        return (int) (Math.random() * (double) (max - min)) + min;
    }

    public void initialize(@Observes EntityManagerFactoryCreatedEvent created) {
        EntityManagerFactory emf = created.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

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
        }

        Team[] teams = new Team[teamNames.length];
        for (int i = 0; i < teamNames.length; i++) {
            Team t = new Team();
            t.setName(teamNames[i]);
            teams[i] = t;
        }

        for (Employee e : employees) {
            Team t = random(teams);
            t.getMembers().add(e);
            e.setTeam(t);
        }
        for (Team t : teams) {
            em.persist(t);
        }

        for (Employee e : employees) {
            em.persist(e);
        }

        em.getTransaction().commit();
    }
}
