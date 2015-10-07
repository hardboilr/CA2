/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entities.CityInfo;
import entities.Company;
import exception.CompanyNotFoundException;
//import exception.CompanyNotFoundException;
import interfaces.ICompanyFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author sebastiannielsen
 */
public class CompanyFacade implements ICompanyFacade {

    private EntityManagerFactory emf;

    public CompanyFacade(EntityManagerFactory e) {
        emf = e;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Company createCompany(Company c) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return c;
        } finally {
            em.close();
        }
    }

    @Override
    public Company getCompany(long cvr) throws CompanyNotFoundException {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT c FROM Company c WHERE c.cvr=:input").setParameter("input", cvr);
            Company c = (Company) query.getSingleResult();
            return c;
        } catch (NoResultException e) {
            throw new CompanyNotFoundException("No Company found with provided cvr");
        } finally {
            em.close();
        }
    }

    @Override
    public List<Company> getCompanies() {
        EntityManager em = getEntityManager();
        try {
            List<Company> companies = em.createQuery("select c from Company c").getResultList();
            return companies;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Company> getCompaniesInCity(CityInfo city) throws Exception {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT c FROM Company c WHERE c.address.city=:city").setParameter("city", city);
            List<Company> cList = query.getResultList();
            if (cList == null) {
                throw new Exception("fuck dig") /*CompanyNotFoundException("No companies found in that city!")*/;
            }
            return cList;

        } finally {
            em.close();
        }
    }

    @Override
    public List<Company> getCompaniesWithEmployeeCount(Long empCount) throws Exception {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT c FROM Company c WHERE c.NumEmployees > :empCount").setParameter("empCount", empCount);
            List<Company> cList = query.getResultList();
            if (cList == null) {
                throw new Exception("fuck dig") /*CompanyNotFoundException("No companies found with more than " + empCount + "employees")*/;
            }
            return cList;

        } catch (NoResultException e) {
            throw new Exception("fuck dig") /*CompanyNotFoundException("No companies found with more than " + empCount + "employees")*/;
        } finally {
            em.close();
        }

    }

    @Override
    public Company editCompany(Company c) throws CompanyNotFoundException {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT c FROM Company c WHERE c.cvr=:cvr").setParameter("cvr", c.getCvr());
            Company edited = (Company) query.getSingleResult();
            edited.setCvr(c.getCvr());
            edited.setName(c.getName());
            edited.setDescription(c.getDescription());
            edited.setMarketValue(c.getMarketValue());
            edited.setNumEmployees(c.getNumEmployees());
            edited.setEmail(c.getEmail());
            edited.setAddress(c.getAddress());
            em.getTransaction().begin();
            em.merge(edited);
            em.getTransaction().commit();
            return edited;
        } catch (NoResultException e) {
            throw new CompanyNotFoundException("No Company found with provided cvr");
        } finally {
            em.close();
        }
    }

    @Override
    public Company deleteCompany(long cvr) throws Exception {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT c FROM Company c WHERE c.cvr=:cvr").setParameter("cvr", cvr);
            Company c = (Company) query.getSingleResult();
            if (c == null) {
                throw new Exception("fuck dig") /*CompanyNotFoundException("No Company found with provided cvr")*/;
            }
            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
            return c;
        } finally {
            em.close();
        }
    }

}
