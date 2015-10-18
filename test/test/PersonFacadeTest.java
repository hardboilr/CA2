package test;

import deploy.DeploymentConfiguration;
import entities.Address;
import entities.AddressId;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import exception.ExistException;
import exception.NotFoundException;
import facade.PersonFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

public class PersonFacadeTest {

    PersonFacade facade = new PersonFacade(Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME));
    private final String PHONE_ID1 = "50206142";
    private final String PHONE_ID4 = "29564310";
    private final String PHONE_ID5 = "01234567";

    public PersonFacadeTest() {
    }

    @BeforeClass
    public static void beforeClass() {
        DeploymentConfiguration.setTestModeOn();
    }

    @Test
    public void testCreateGetPerson() throws ExistException, NotFoundException {
        String hobbyName = "test hobby";
        String hobbyDescription = "test description";
        Hobby hobby = new Hobby(hobbyName, hobbyDescription);
        String phone1No = "53555358";
        String phone2No = "31203083";
        Phone phone1 = new Phone(phone1No, "Mobile");
        Phone phone2 = new Phone(phone2No, "Mobile, work");
        String zipCode = "2800";
        String city = "Kgs. Lyngby";
        String street = "Test street 1";
        String additionalInfo = "1.tv.";
        CityInfo cityinfo = new CityInfo(zipCode, city);
        AddressId addressId = new AddressId(street, additionalInfo);
        Address address = new Address(addressId, cityinfo);
        String firstName = "Nil";
        String lastName = "Nillersen";
        String email = "nil@nillersen.dk";
        Person person = new Person(firstName, lastName);
        person.addHobby(hobby);
        person.setEmail(email);
        person.addPhone(phone1);
        person.addPhone(phone2);
        person.setAddress(address);
        Person p = facade.createPerson(person);
        assertEquals(email, p.getEmail());
        assertEquals(firstName, p.getFirstName());
        assertEquals(lastName, p.getLastName());
        assertEquals(hobbyName, p.getHobbies().get(0).getName());
        assertEquals(hobbyDescription, p.getHobbies().get(0).getDescription());
        assertTrue(p.getHobbies().size() == 1);
        assertTrue(p.getPhones().size() == 2);
        assertEquals(street, p.getAddress().getAddressId().getStreet());
        assertEquals(additionalInfo, p.getAddress().getAddressId().getAdditionalInfo());
        assertEquals(city, p.getAddress().getCityInfo().getCity());
        assertEquals(zipCode, p.getAddress().getCityInfo().getZipCode());
        p = facade.getPerson(phone2No);
        assertEquals(email, p.getEmail());
        assertEquals(firstName, p.getFirstName());
        assertEquals(lastName, p.getLastName());
        assertEquals(hobbyName, p.getHobbies().get(0).getName());
        assertEquals(hobbyDescription, p.getHobbies().get(0).getDescription());
        assertTrue(p.getHobbies().size() == 1);
        assertTrue(p.getPhones().size() == 2);
        assertEquals(street, p.getAddress().getAddressId().getStreet());
        assertEquals(additionalInfo, p.getAddress().getAddressId().getAdditionalInfo());
        assertEquals(city, p.getAddress().getCityInfo().getCity());
        assertEquals(zipCode, p.getAddress().getCityInfo().getZipCode());
    }

    @Test
    public void testEditPerson() throws NotFoundException, ExistException {
        String email = "test@testesen.dk";
        String firstName = "Test";
        String lastName = "Testesen";
        String hobby1Name = "test activity 1";
        String hobby2Name = "test activity 2";
        String hobby3Name = "dance";
        String phone2Number = "88888888";
        String phone3Number = "99999999";
        Hobby hobby1 = new Hobby(hobby1Name, "test activity 1 description");
        Hobby hobby2 = new Hobby(hobby2Name, "test activity 2 description");
        Hobby hobby3 = new Hobby(hobby3Name, "test activity 3 description");
        Phone phone1 = new Phone(PHONE_ID1, "existing number");
        Phone phone2 = new Phone(phone2Number, "test mobile");
        Phone phone3 = new Phone(phone3Number, "test stationary");
        String street = "233 Oxford street";
        String additionalInfo = "3. tv.";
        String zipCode = "4070";
        String city = "Kirke Hyllinge";
        AddressId addressId = new AddressId(street, additionalInfo);
        CityInfo cityInfo = new CityInfo(zipCode, city);
        Address address = new Address(addressId, cityInfo);
        Person person = new Person(firstName, lastName);
        List<Hobby> hobbies = new ArrayList();
        hobbies.add(hobby1);
        hobbies.add(hobby2);
        hobbies.add(hobby3);
        person.setHobbies(hobbies);
        List<Phone> phones = new ArrayList();
        phones.add(phone1);
        phones.add(phone2);
        phones.add(phone3);
        person.setPhones(phones);
        person.setEmail(email);
        person.setAddress(address);
        Person p = facade.editPerson(person);
        assertEquals(email, p.getEmail());
        assertEquals(firstName, p.getFirstName());
        assertEquals(lastName, p.getLastName());
        assertTrue(p.getHobbies().size() == 4);
        assertTrue(p.getPhones().size() == 3);
        assertEquals(street, p.getAddress().getAddressId().getStreet());
        assertEquals(additionalInfo, p.getAddress().getAddressId().getAdditionalInfo());
        assertEquals(zipCode, p.getAddress().getCityInfo().getZipCode());
        assertEquals(city, p.getAddress().getCityInfo().getCity());
        p = facade.getPerson(PHONE_ID1);
        assertEquals(p.getFirstName(), "Test");
    }

    @Test
    public void testDeletePerson() throws NotFoundException {
        Person p = facade.getPerson(PHONE_ID5);
        String firstName = "Julius";
        String lastName = "Juliusen";
        String email = "julius@juliusen";
        String street = "Borups Alle 150";
        String additionalInfo = "4.th.";
        String zipCode = "2000";
        String city = "Frederiksberg";
        String phoneNumberDescription = "main number";
        String phoneNumber = "01234567";
        String hobbyName = "dance";
        String hobbyDescription = "dancing and having a gay time";
        assertEquals(firstName, p.getFirstName());
        assertEquals(lastName, p.getLastName());
        assertEquals(email, p.getEmail());
        assertEquals(street, p.getAddress().getAddressId().getStreet());
        assertEquals(additionalInfo, p.getAddress().getAddressId().getAdditionalInfo());
        assertEquals(zipCode, p.getAddress().getCityInfo().getZipCode());
        assertEquals(city, p.getAddress().getCityInfo().getCity());
        assertEquals(phoneNumberDescription, p.getPhones().get(0).getDescription());
        assertEquals(phoneNumber, p.getPhones().get(0).getNumber());
        assertEquals(hobbyName, p.getHobbies().get(0).getName());
        assertEquals(hobbyDescription, p.getHobbies().get(0).getDescription());
        p = facade.deletePerson(PHONE_ID5);
        assertEquals(firstName, p.getFirstName());
        assertEquals(lastName, p.getLastName());
        assertEquals(email, p.getEmail());
        assertEquals(street, p.getAddress().getAddressId().getStreet());
        assertEquals(additionalInfo, p.getAddress().getAddressId().getAdditionalInfo());
        assertEquals(zipCode, p.getAddress().getCityInfo().getZipCode());
        assertEquals(city, p.getAddress().getCityInfo().getCity());
        assertEquals(phoneNumberDescription, p.getPhones().get(0).getDescription());
        assertEquals(phoneNumber, p.getPhones().get(0).getNumber());
        assertEquals(hobbyName, p.getHobbies().get(0).getName());
        assertEquals(hobbyDescription, p.getHobbies().get(0).getDescription());
    }
    
    @Test(expected = NotFoundException.class)
    public void DeletePersonNotFoundException() throws NotFoundException {
        Person p = facade.deletePerson(PHONE_ID4);
        assertEquals(p.getFirstName(), "Peter");
        assertEquals(p.getLastName(), "Petersen");
        p = facade.deletePerson(PHONE_ID4);
    }

}
