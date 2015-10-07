package facade;

import deploy.DeploymentConfiguration;
import entities.CityInfo;
import entities.Company;
import entities.Hobby;
import entities.Person;
import interfaces.IPersonFacade;
//import exceptions.PersonNotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PersonFacade implements IPersonFacade {

    private EntityManagerFactory emf;

    public PersonFacade(EntityManagerFactory e) {
        emf = e;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Create a person, returns nothing.
     * @param person 
     */
    @Override
    public void createPerson(Person person) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    /**
     * Find a person using a phone number. 
     * @param phone
     * @return Person
     * @throws PersonNotFoundException 
     */
    @Override
    public Person getPerson(String phone) throws Exception {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT p FROM Person p WHERE :phone MEMBER OF p.phones").setParameter("phone", phone);
            Person p = (Person) query.getSingleResult();
            if (p == null) {
                throw new Exception("No person with that phone number found!");
            }
            System.out.println(p.getFirstName());
            return p;
        } finally {
            em.close();
        }
    }

    /**
     * Edit a person, returns nothing
     * @param person
     * @param phone
     * @throws PersonNotFoundException 
     */
    @Override
    public void editPerson(Person person, String phone) throws Exception {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT p FROM Person p WHERE :phone MEMBER OF p.phones").setParameter("phone", phone);
            Person edited = (Person) query.getSingleResult();
            if (edited == null) {
                throw new Exception("Requested person not found!");
            }
            em.getTransaction().begin();
            edited.setAddress(person.getAddress());
            edited.setEmail(person.getEmail());
            edited.setFirstName(person.getFirstName());
            edited.setHobbies(person.getHobbies());
            edited.setLastName(person.getLastName());
            edited.setPhones(person.getPhones());
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    /**
     * 
     * @param person
     * @throws PersonNotFoundException 
     */
    @Override
    public void deletePerson(Person person) throws Exception {
        EntityManager em = getEntityManager();
        try {
            Person p = em.find(Person.class, person);
            if (p == null) {
                throw new Exception("Requested person not found!");
            }
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    @Override
    public List<Person> getPersonsWithHobby(Hobby hobby) throws Exception {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT p FROM Person p WHERE :hobby MEMBER OF p.hobbies ").setParameter("hobby", hobby);
            List<Person> pList = query.getResultList();
            if (pList == null) {
                throw new Exception("No persons found with that hobby!");
            }
            return pList;
        } finally {
            em.close();
        }

    }

    @Override
    public List<Person> getPersonsInCity(CityInfo city) throws Exception {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT p FROM Person p WHERE p.address.city=:city").setParameter("city", city);
            List<Person> pList = query.getResultList();
            if (pList == null) {
                throw new Exception("No persons found in that city!");
            }
            return pList;

        } finally {
            em.close();
        }
    }

    @Override
    public Long getPersonCountWithHobby(Hobby hobby) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT COUNT(p.id) FROM Person p WHERE :hobby MEMBER OF p.hobbies").setParameter("hobby", hobby);
            return (Long) query.getSingleResult();
        } finally {
            em.close();
        }
    }

//-------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------
    public List<CityInfo> getZipCodes() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT c FROM CityInfo c");
        return query.getResultList();
    }

    public List<Company> getCompaniesWithEmployeeCount(Long empCount) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT c FROM Company c WHERE c.NumEmployees > :empCount").setParameter("empCount", empCount);
        return query.getResultList();
    }

    public Company getCompany(Company company) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT c FROM Company c WHERE c.cvr=:cvr OR :phones MEMBER OF c.phones").setParameter("cvr", company.getCvr()).setParameter("phones", company.getPhones());
        return (Company) query.getSingleResult();
    }
}
