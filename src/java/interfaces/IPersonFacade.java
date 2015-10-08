package interfaces;

import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import exception.PersonNotFoundException;
import exception.PhoneExistException;
import java.util.List;

public interface IPersonFacade {

    public Person createPerson(Person person) throws PhoneExistException;

    public Person getPerson(String phone) throws PersonNotFoundException;

    public Person editPerson(Person person, String phone) throws PersonNotFoundException;

    public Person deletePerson(Long id) throws PersonNotFoundException;

    public List<Person> getPersonsWithHobby(String hobby) throws PersonNotFoundException;

    public List<Person> getPersonsInCity(int zipcode) throws PersonNotFoundException;

    public Long getPersonCountWithHobby(String hobby) throws PersonNotFoundException;

    public List<Person> getAllPersons();
}
