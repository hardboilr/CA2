package interfaces;

import entities.Person;
import exception.ExistException;
import exception.NotFoundException;
import java.util.List;

public interface IPersonFacade {

    public Person createPerson(Person person) throws ExistException;

    public Person getPerson(String phone) throws NotFoundException;

    public Person editPerson(Person person) throws NotFoundException;

    public Person deletePerson(String phone) throws NotFoundException;

    public List<Person> getPersonsWithHobby(String hobby) throws NotFoundException;

    public List<Person> getPersonsInCity(int zipcode) throws NotFoundException;

    public Long getPersonCountWithHobby(String hobby) throws NotFoundException;

    public List<Person> getAllPersons();
}
