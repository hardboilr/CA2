package facade;

import entities.Address;
import entities.CityInfo;
import entities.Company;
import entities.Phone;
import exception.NotFoundException;
import exception.ExistException;
import interfaces.ICompanyFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class CompanyFacade implements ICompanyFacade {

    private EntityManagerFactory emf;

    public CompanyFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Company createCompany(Company company) throws ExistException {
        EntityManager em = getEntityManager();
        try {
            try {
                //check for existing cvr
                TypedQuery<Company> query = em.createNamedQuery("Company.findByCvr", Company.class).setParameter("cvr", company.getCvr());
            } catch (NoResultException e) {
                throw new ExistException("Company with cvr: " + company.getCvr() + " already exists. Please choose another cvr");
            }
            //check for existing phone number
            for (Phone phone : company.getPhones()) {
                Phone p = em.find(Phone.class, phone.getNumber());
                if (p != null) {
                    throw new ExistException("Phonenumber: " + p.getNumber() + " already exists. Please choose another phonenumber");
                }
            }
            //check for existing Address
            Address address = em.find(Address.class, company.getAddress().getAddressId());
            if (address != null) {
                company.setAddress(address);
            }
            //check for existing CityInfo
            CityInfo ci = em.find(CityInfo.class, company.getAddress().getCityInfo().getZipCode());
            if (ci != null) {
                company.getAddress().setCityInfo(ci);
            }
            em.getTransaction().begin();
            em.persist(company);
            em.getTransaction().commit();
            return company;
        } finally {
            em.close();
        }
    }

    @Override
    public Company getCompany(int cvr) throws NotFoundException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Company> query = em.createNamedQuery("Company.findByCvr", Company.class).setParameter("cvr", cvr);
            List<Company> companies = query.getResultList();
            if (!companies.isEmpty()) {
                return companies.get(0);
            } else {
                throw new NotFoundException("No Company found with cvr: " + cvr);
            }
        } finally {
            em.close();
        }
    }

    @Override
    public Company editCompany(Company company) throws NotFoundException {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            //check for existing company
            TypedQuery<Company> query = em.createNamedQuery("Company.findByCvr", Company.class).setParameter("cvr", company.getCvr());
            List<Company> companies = query.getResultList();
            Company editedCompany = new Company();
            if (!companies.isEmpty()) {
                editedCompany = companies.get(0);
            } else {
                throw new NotFoundException("No Company found with cvr: " + company.getCvr());
            }
            //check for existing phone numbers
            List<Phone> phonesCombined = new ArrayList();
            for (Phone phone : company.getPhones()) {
                Phone p = em.find(Phone.class, phone.getNumber());
                if (p != null) {
                    phonesCombined.add(p);
                } else {
                    phonesCombined.add(phone);
                }
            }
            //check for existing address
            Address address = em.find(Address.class, company.getAddress().getAddressId());
            Address addressCombined;
            if (address != null) {
                addressCombined = address;
            } else {
                addressCombined = company.getAddress();
                CityInfo cityInfo = em.find(CityInfo.class, company.getAddress().getCityInfo().getZipCode());
                if (cityInfo != null) {
                    addressCombined.setCityInfo(cityInfo);
                }
            }
            editedCompany.setEmail(company.getEmail());
            editedCompany.setName(company.getName());
            editedCompany.setDescription((company.getDescription()));
            editedCompany.setNumEmployees(company.getNumEmployees());
            editedCompany.setMarketValue(company.getMarketValue());
            editedCompany.setAddress(addressCombined);
            editedCompany.setPhones(phonesCombined);
            em.getTransaction().commit();
            return editedCompany;
        } finally {
            em.close();
        }
    }

    @Override
    public Company deleteCompany(int cvr) throws NotFoundException {
        EntityManager em = getEntityManager();
        try {
            //check for existing company
            TypedQuery<Company> query = em.createNamedQuery("Company.findByCvr", Company.class).setParameter("cvr", cvr);
            List<Company> companies = query.getResultList();
            Company company = new Company();
            if (!companies.isEmpty()) {
                company = companies.get(0);
                em.getTransaction().begin();
                em.remove(company);
                em.getTransaction().commit();
                return company;
            } else {
                throw new NotFoundException("No Company found with cvr: " + company.getCvr());
            }
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
    public List<Company> getCompaniesValuedMoreThan(Long value) throws Exception {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT c FROM Company c WHERE c.marketValue > :value").setParameter("value", value);
            List<Company> cList = query.getResultList();
            if (cList == null) {
                throw new Exception("fuck dig")/*throw new CompanyNotFoundException("No companies found with more than " + empCount + "employees")*/;
            }
            return cList;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Company> getCompaniesWithEmployeeCount(Long empCount) throws NotFoundException {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT c FROM Company c WHERE c.NumEmployees > :empCount").setParameter("empCount", empCount);
            List<Company> cList = query.getResultList();

            if (cList.isEmpty() || cList == null) {
                throw new NotFoundException("No companies found with more than " + empCount + " employees!");
            }
            return cList;
        } finally {
            em.close();
        }
    }

}
