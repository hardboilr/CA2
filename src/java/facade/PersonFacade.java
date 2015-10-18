package facade;

import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import exception.ExistException;
import exception.NotFoundException;
import interfaces.IPersonFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class PersonFacade implements IPersonFacade {

    private EntityManagerFactory emf;

    public PersonFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Person createPerson(Person person) throws ExistException {
        EntityManager em = getEntityManager();
        try {
            //check for existing phone number and throw exception
            for (Phone phone : person.getPhones()) {
                Phone p = em.find(Phone.class, phone.getNumber());
                if (p != null) {
                    throw new ExistException("Phonenumber: " + p.getNumber() + " already exists. Please choose another phonenumber");
                }
            }

            //check for existing hobbies
            List<Hobby> hobbiesCombined = new ArrayList();
            for (Hobby hobby : person.getHobbies()) {
                Hobby hob = em.find(Hobby.class, hobby.getName());
                if (hob != null) {
                    hobbiesCombined.add(hob);
                } else {
                    hobbiesCombined.add(hobby);
                }
            }
            person.setHobbies(hobbiesCombined);
            //check for existing address
            Address address = em.find(Address.class, person.getAddress().getAddressId());
            if (address != null) {
                person.setAddress(address);
            }
            //check for existing cityInfo
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
    public Person getPerson(String phoneNumber) throws NotFoundException {
        EntityManager em = getEntityManager();
        Phone phone = new Phone();
        phone.setNumber(phoneNumber);
        try {
            TypedQuery<Person> query = em.createNamedQuery("Person.findByPhone", Person.class).setParameter("phone", phone);
            List<Person> persons = query.getResultList();
            if (!persons.isEmpty()) {
                return persons.get(0);
            } else {
                throw new NotFoundException(phoneNotFound(phoneNumber));
            }
        } finally {
            em.close();
        }
    }

    @Override
    public Person editPerson(Person person) throws NotFoundException {
        EntityManager em = getEntityManager();
        String phoneNumber = person.getPhones().get(0).getNumber();
        Phone phone = new Phone(phoneNumber, "");
        try {
            //check for existing person and throw exception
            TypedQuery<Person> query = em.createNamedQuery("Person.findByPhone", Person.class).setParameter("phone", phone);
            List<Person> persons = query.getResultList();
            Person editedPerson = new Person();
            if (!persons.isEmpty()) {
                editedPerson = persons.get(0);
            } else {
                throw new NotFoundException(phoneNotFound(phoneNumber));
            }
            //check for existing phone numbers
            List<Phone> phonesCombined = new ArrayList();
            for (Phone ph : person.getPhones()) {
                Phone p = em.find(Phone.class, ph.getNumber());
                if (p != null) {
                    phonesCombined.add(p);
                } else {
                    phonesCombined.add(ph);
                }
            }
            editedPerson.setPhones(phonesCombined);
            //check for existing hobbies
            List<Hobby> hobbiesCombined = new ArrayList();
            for (Hobby hobby : person.getHobbies()) {
                Hobby hob = em.find(Hobby.class, hobby.getName());
                if (hob != null) {
                    hobbiesCombined.add(hob);
                } else {
                    hobbiesCombined.add(hobby);
                }
            }
            //add the hobbies to the edited person (setHobbies overwrite existing hobbies)
            for (Hobby hobby : hobbiesCombined) {
                editedPerson.addHobby(hobby);
            }
            //check for existing address
            Address address = em.find(Address.class, person.getAddress().getAddressId());
            if (address != null) {
                editedPerson.setAddress(address);
            } else {
                editedPerson.setAddress(person.getAddress());
            }
            CityInfo cityInfo = em.find(CityInfo.class, person.getAddress().getCityInfo().getZipCode());
            if (cityInfo != null) {
                editedPerson.getAddress().setCityInfo(cityInfo);
            }
            editedPerson.setEmail(person.getEmail());
            editedPerson.setFirstName(person.getFirstName());
            editedPerson.setLastName(person.getLastName());
            em.getTransaction().begin();
            em.merge(editedPerson);
            em.getTransaction().commit();
            return editedPerson;
        } finally {
            em.close();
        }
    }

    @Override
    public Person deletePerson(String phoneNumber) throws NotFoundException {
        EntityManager em = getEntityManager();
        Phone phone = new Phone();
        phone.setNumber(phoneNumber);
        try {
            //check for existing person
            TypedQuery<Person> query = em.createNamedQuery("Person.findByPhone", Person.class).setParameter("phone", phone);
            List<Person> persons = query.getResultList();
            Person person = new Person();
            if (!persons.isEmpty()) {
                person = persons.get(0);
                em.getTransaction().begin();
                em.remove(person);
                em.getTransaction().commit();
                return person;
            } else {
                throw new NotFoundException(phoneNotFound(phoneNumber));
            }
        } finally {
            em.close();
        }
    }

    @Override
    public List<Person> getPersonsWithHobby(String hobby) throws NotFoundException {
        EntityManager em = getEntityManager();
        Hobby hobbydb = em.find(Hobby.class, hobby);
        try {
            Query query = em.createQuery("SELECT p FROM Person p WHERE :hobby MEMBER OF p.hobbies ").setParameter("hobby", hobbydb);
            List<Person> pList = query.getResultList();
            if (pList == null) {
                throw new NotFoundException("No persons found with that hobby!");
            }
            return pList;
        } finally {
            em.close();
        }

    }

    @Override
    public List<Person> getPersonsInCity(int zipcode) throws NotFoundException {
        EntityManager em = getEntityManager();
        CityInfo city = em.find(CityInfo.class, zipcode);
        try {
            Query query = em.createQuery("SELECT p FROM Person p WHERE p.address.city=:city").setParameter("city", city);
            List<Person> pList = query.getResultList();
            if (pList == null) {
                throw new NotFoundException("No persons found in that city!");
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

    private String phoneNotFound(String phone) {
        return "No person with " + phone + " found.";
    }
}
