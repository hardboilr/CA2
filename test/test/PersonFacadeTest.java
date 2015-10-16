package test;

import deploy.DeploymentConfiguration;
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import exception.PersonNotFoundException;
import exception.ExistException;
import facade.PersonFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PersonFacadeTest {

    PersonFacade pf = new PersonFacade(Persistence.createEntityManagerFactory("pu_test"));

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
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreateGetPerson() throws ExistException {
        Hobby hobby = new Hobby("programming", "software development");
        Phone phone = new Phone("53555358", "Mobil");
        Address address = new Address("233 Oxford street", "3. tv");
        CityInfo cityinfo = new CityInfo(2800, "Lyngby");
        address.setCityInfo(cityinfo);

        Person person = new Person("Henrik", "Knudsen");
        person.addHobby(hobby);
        person.setEmail("Henrik@Knudsen.dk");
        person.addPhone(phone);
        person.setAddress(address);

        pf.createPerson(person);

        Person p = null;
        try {
            p = pf.getPerson("53555358");
        } catch (PersonNotFoundException ex) {
            Logger.getLogger(PersonFacadeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(person.getFirstName(), p.getFirstName());
    }

    @Test
    public void testEditPerson() throws PersonNotFoundException {
        Hobby hobby = new Hobby("programming", "software development");
        Phone phone = new Phone("53555359", "Mobil");
        Address address = new Address("233 Oxford street", "3. tv");
        CityInfo cityinfo = new CityInfo(2800, "Lyngby");
        address.setCityInfo(cityinfo);

        Person person = new Person("Henrik", "Knudsen");
        person.addHobby(hobby);
        person.setEmail("Henrik@Knudsen.dk");
        person.addPhone(phone);
        person.setAddress(address);

        try {
            pf.createPerson(person);
        } catch (ExistException ex) {
            Logger.getLogger(PersonFacadeTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        Person p = pf.getPerson("53555359");
        p.setFirstName("Knud");

        pf.editPerson(p, "53555359");

        p = pf.getPerson("53555359");
        assertEquals(p.getFirstName(), "Knud");
    }

    @Test
    public void testDeletePerson() {
        Hobby hobby = new Hobby("programming", "software development");
        Phone phone = new Phone("53555357", "Mobil");
        Address address = new Address("233 Oxford street", "3. tv");
        CityInfo cityinfo = new CityInfo(2800, "Lyngby");
        address.setCityInfo(cityinfo);

        Person person = new Person("Henrik", "Knudsen");
        person.addHobby(hobby);
        person.setEmail("Henrik@Knudsen.dk");
        person.addPhone(phone);
        person.setAddress(address);

        try {
            pf.createPerson(person);
        } catch (ExistException ex) {
            Logger.getLogger(PersonFacadeTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Person p = pf.getPerson("53555357");
            pf.deletePerson(p.getId());
        } catch (PersonNotFoundException ex) {
            Logger.getLogger(PersonFacadeTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertEquals(2, pf.getAllPersons().size());
    }

}
