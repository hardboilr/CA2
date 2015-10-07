package test;

import entities.Company;
import exception.CompanyNotFoundException;
import facade.CompanyFacade;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CompanyFacadeTest {

    CompanyFacade facade = new CompanyFacade(Persistence.createEntityManagerFactory("CA2_dev"));

    public CompanyFacadeTest() {
    }

    @Before
    public void setUp() {
        EntityManager em = facade.getEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("delete from Company").executeUpdate();
            em.persist(new Company(1234l, "firma", "description", 15, 300000l));
            em.persist(new Company(123l, "firma2", "description", 15, 300000l));
            em.persist(new Company(12l, "firma3", "description", 15, 300000l));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testAddCompany() throws CompanyNotFoundException {
        Company company = new Company(12345l, "firma4", "description", 15, 300000l);
        facade.createCompany(company);
        assertEquals("firma4", company.getName());
        company = facade.getCompany(company.getCvr());
        assertEquals("firma4", company.getName());
    }

    @Test
    public void testGetCompany() throws CompanyNotFoundException {
        Company company = new Company(123456l, "firma5", "description", 10, 200000l);
        facade.createCompany(company);
        Company c = facade.getCompany(123456l);
        assertEquals(c.getName(), "firma5");
    }

    @Test
    public void testGetCompanies() {
        assertEquals(3, facade.getCompanies().size());
    }

    @Test
    public void testEditCompany() throws CompanyNotFoundException {
        Company company = facade.getCompany(1234l);
        company.setName("firma9");
        facade.editCompany(company);
        assertEquals("firma9", company.getName());
        System.out.println(facade.getCompany(1234l).getName());
        company = facade.getCompany(1234l);
        assertEquals("firma9", company.getName());
    }

    @Test
    public void testDeletePerson() throws CompanyNotFoundException {
        facade.deleteCompany(12l);
        assertEquals(facade.getCompanies().size(), 2);
    }
}
