package facade;

import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import exception.PersonNotFoundException;
import exception.PhoneExistException;
import interfaces.IPersonFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class PersonFacade implements IPersonFacade {

    private EntityManagerFactory emf;

    public PersonFacade(EntityManagerFactory e) {
        emf = e;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Person createPerson(Person person) throws PhoneExistException {
        EntityManager em = getEntityManager();

        for (Phone phone : person.getPhones()) {
            Phone p = em.find(Phone.class, phone.getNumber());
            if (p != null) {
                throw new PhoneExistException();
            }
        }

        List<Hobby> hobs = new ArrayList();
        try {
            for (Hobby hobby : person.getHobbies()) {
                Hobby hob = em.find(Hobby.class, hobby.getName());
                if (hob != null) {
                    hobs.add(hob);
                } else {
                    hobs.add(hobby);
                }
            }
            person.setHobbies(hobs);

            Address ad = em.find(Address.class, person.getAddress().getStreet());
            if (ad != null) {
                person.setAddress(ad);
            }

            CityInfo ci = em.find(CityInfo.class, person.getAddress().getCityInfo().getZipCode());
            if (ci != null) {
                person.getAddress().setCityInfo(ci);
            }

            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            return person;
        } finally {
            em.close();
        }
    }

    @Override
    public Person editPerson(Person person, String phoneNumber) throws PersonNotFoundException {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT p FROM Person p WHERE :phone MEMBER OF p.phones", Person.class).setParameter("phone", phoneNumber);
            Person existingPerson = new Person();
            try {
                existingPerson = (Person) query.getSingleResult();
            } catch (NoResultException e) {
                throw new PersonNotFoundException("Requested person not found!");
            }
            CityInfo ci = em.find(CityInfo.class, existingPerson.getAddress().getCityInfo().getZipCode());
            if (ci != null) {
                person.getAddress().setCityInfo(ci);
            }

            Address ad = em.find(Address.class, existingPerson.getAddress().getStreet());
            if (ad != null) {
                person.setAddress(ad);
            }

            em.getTransaction().begin();
            existingPerson.setAddress(person.getAddress());
            existingPerson.setEmail(person.getEmail());
            existingPerson.setFirstName(person.getFirstName());
            existingPerson.setHobbies(person.getHobbies());
            existingPerson.setLastName(person.getLastName());
            existingPerson.setPhones(person.getPhones());
            em.getTransaction().commit();
            return person;

        } finally {
            em.close();
        }
    }

    @Override
    public Person getPerson(String phoneNumber) throws PersonNotFoundException {
        EntityManager em = getEntityManager();
        Phone phone = new Phone();
        phone.setNumber(phoneNumber);
        try {
            Query query = em.createQuery("SELECT p FROM Person p WHERE :phone MEMBER OF p.phones", Person.class).setParameter("phone", phone);
            Person p = (Person) query.getSingleResult();
            if (p == null) {
                throw new PersonNotFoundException("No person with that phone number found!");
            }
            return p;
        } finally {
            em.close();
        }
    }

    @Override
    public Person deletePerson(Long id) throws PersonNotFoundException {
        EntityManager em = getEntityManager();
        try {
            Person p = em.find(Person.class, id);
            if (p == null) {
                throw new PersonNotFoundException("Requested person not found!");
            }
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Person> getPersonsWithHobby(String hobby) throws PersonNotFoundException {
        EntityManager em = getEntityManager();
        Hobby hobbydb = em.find(Hobby.class, hobby);
        try {
            Query query = em.createQuery("SELECT p FROM Person p WHERE :hobby MEMBER OF p.hobbies ").setParameter("hobby", hobbydb);
            List<Person> pList = query.getResultList();
            if (pList == null) {
                throw new PersonNotFoundException("No persons found with that hobby!");
            }
            return pList;
        } finally {
            em.close();
        }

    }

    @Override
    public List<Person> getPersonsInCity(int zipcode) throws PersonNotFoundException {
        EntityManager em = getEntityManager();
        CityInfo city = em.find(CityInfo.class, zipcode);
        try {
            Query query = em.createQuery("SELECT p FROM Person p WHERE p.address.city=:city").setParameter("city", city);
            List<Person> pList = query.getResultList();
            if (pList == null) {
                throw new PersonNotFoundException("No persons found in that city!");
            }
            return pList;

        } finally {
            em.close();
        }
    }

    @Override
    public Long getPersonCountWithHobby(String hobby) {
        EntityManager em = getEntityManager();
        Hobby hobbydb = em.find(Hobby.class, hobby);
        try {
            Query query = em.createQuery("SELECT COUNT(p.id) FROM Person p WHERE :hobby MEMBER OF p.hobbies").setParameter("hobby", hobbydb);
            return (Long) query.getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Person> getAllPersons() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT p FROM Person p");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
