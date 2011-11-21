package net.ftlines.blog.cdidemo.model;

import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import net.ftlines.blog.cdidemo.jpa.EntityManagerFactoryCreatedEvent;

public class ModelInitializer {
	private final String[] firstNames = { "Jacob", "Emily", "Michael", "Sarah", "Matthew", "Brianna", "Nicholas",
			"Samantha", "Christopher", "Hailey", "Abner", "Abby", "Joshua", "Douglas", "Jack", "Keith", "Gerald",
			"Samuel", "Willie", "Larry", "Jose", "Timothy", "Sandra", "Kathleen", "Pamela", "Virginia", "Debra",
			"Maria", "Linda" };
	private final String[] lastNames = { "Smith", "Johnson", "Williams", "Jones", "Brown", "Donahue", "Bailey",
			"Rose", "Allen", "Black", "Davis", "Clark", "Hall", "Lee", "Baker", "Gonzalez", "Nelson", "Moore",
			"Wilson", "Graham", "Fisher", "Cruz", "Ortiz", "Gomez", "Murray" };

	private static <T> T random(T[] values) {
		return values[(int)(Math.random() * values.length)];
	}

	public void initialize(@Observes EntityManagerFactoryCreatedEvent created) {
		EntityManagerFactory emf = created.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		for (int i = 0; i < 30; i++) {
			Employee e = new Employee();
			e.setFirstName(random(firstNames));
			e.setLastName(random(lastNames));
			String email=e.getFirstName() + "." + e.getLastName() + "@firm.com";
			email=email.toLowerCase();
			e.setEmail(email);
			em.persist(e);
		}
		em.getTransaction().commit();
	}
}
