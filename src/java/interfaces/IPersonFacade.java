/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import exception.PersonNotFoundException;
import java.util.List;

/**
 *
 * @author Jonas
 */
public interface IPersonFacade {

    public Person createPerson(Person person);

    public Person getPerson(String phone) throws PersonNotFoundException;

    public Person editPerson(Person person, String phone) throws PersonNotFoundException;

    public Person deletePerson(Long id) throws Exception;

    public List<Person> getPersonsWithHobby(String hobby) throws PersonNotFoundException;

    public List<Person> getPersonsInCity(int zipcode) throws PersonNotFoundException;

    public Long getPersonCountWithHobby(String hobby) throws PersonNotFoundException;

    public List<Person> getAllPersons();
}
