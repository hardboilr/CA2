package test;

import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import exception.PersonNotFoundException;
import facade.PersonFacade;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PersonFacadeTest {

    PersonFacade pf = new PersonFacade(Persistence.createEntityManagerFactory("CA2PU"));

    public PersonFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
//        EntityManager em = pf.getEntityManager();
//        try {
//            Hobby hobby1 = new Hobby("Programming", "Software Development");
//            Phone phone1 = new Phone("51887460", "Mobil");
//            Address address1 = new Address("Lombardigade", "14 1 tv");
//            CityInfo cityInfo1 = new CityInfo(2300, "Kbh S");
//            address1.setCity(cityInfo1);
//
//            Person person1 = new Person("Sebastian", "Nielsen");
//            person1.addHobby(hobby1);
//            person1.setEmail("sebnielsen@hotmail.com");
//            person1.addPhone(phone1);
//            person1.setAddress(address1);
//
//            Hobby hobby2 = new Hobby("Programming1", "Software Development");
//            Phone phone2 = new Phone("53555358", "Mobil");
//            Address address2 = new Address("Grymersvej", "3");
//            CityInfo cityInfo2 = new CityInfo(3650, "Ølstykke");
//            address2.setCity(cityInfo2);
//
//            Person person2 = new Person("Jonas", "Rafn");
//            person2.addHobby(hobby2);
//            person2.setEmail("jonaschrafn@gmail.com");
//            person2.addPhone(phone2);
//            person2.setAddress(address2);
//
//            Hobby hobby3 = new Hobby("Programming2", "Software Development");
//            Phone phone3 = new Phone("31451231", "Mobil");
//            Address address3 = new Address("Frederiksberg Alle", "45 3 th");
//            CityInfo cityInfo3 = new CityInfo(1820, "Frederiksberg C");
//            address3.setCity(cityInfo3);
//
//            Person person3 = new Person("Tobias", "Jacobsen");
//            person1.addHobby(hobby3);
//            person1.setEmail("tobias@hotmail.com");
//            person1.addPhone(phone3);
//            person1.setAddress(address3);
//
//            em.getTransaction().begin();
////            em.createQuery("DELETE FROM InfoEntity").executeUpdate();
//            em.persist(person1);
//            em.persist(person2);
//            em.persist(person3);
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreatePerson() throws PersonNotFoundException {
        Hobby hobby = new Hobby("Programming3", "Software Development");
        Phone phone = new Phone("12345678", "Mobil");
        Address address = new Address("asdfsa", "asdf");
        CityInfo cityinfo = new CityInfo(3412, "gdasfg");
        address.setCity(cityinfo);

        Person person = new Person("Henrik", "Knudsen");
        person.addHobby(hobby);
        person.setEmail("Knud@Knudsen.dk");
        person.addPhone(phone);
        person.setAddress(address);

        pf.createPerson(person);

        Person p = pf.getPerson("12345678");
        assertEquals(person.getFirstName(), p.getFirstName());
    }

    @Test
    public void testEditPerson() throws PersonNotFoundException {
        Person p = pf.getPerson("12345678");
        p.setFirstName("Knud");

        pf.editPerson(p, "12345678");

        p = pf.getPerson("12345678");
        assertEquals(p.getFirstName(), "Knud");
    }
}
