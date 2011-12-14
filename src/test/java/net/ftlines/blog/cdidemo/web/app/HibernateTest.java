package net.ftlines.blog.cdidemo.web.app;

import net.ftlines.blog.cdidemo.model.Sport;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author George Armhold armhold@gmail.com
 */
public class HibernateTest
{
    private EntityManagerFactory factory;
    private EntityManager em;
    private Sport sport;

    @Before
    public void setUp() throws Exception
    {
        factory = Persistence.createEntityManagerFactory("cdidemo");
        em = factory.createEntityManager();
        sport = new Sport();
        sport.setName("baseball");
    }

    @Test
    public void testPersistBeforeTransactionBegin()
    {
        // persist the entity prior to beginning the transaction
        em.persist(sport);

        em.getTransaction().begin();
        em.flush();
        em.getTransaction().commit();
    }

    @Test
    public void testPersistAfterTransactionBegin()
    {
        em.getTransaction().begin();

        // persist the entity after beginning the transaction
        em.persist(sport);
        em.flush();
        em.getTransaction().commit();
    }


}
