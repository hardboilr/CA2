/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.CityInfo;
import entities.Hobby;
import entities.Person;
//import exceptions.PersonNotFoundException;
import java.util.List;

/**
 *
 * @author Jonas
 */
public interface IPersonFacade {

    public void createPerson(Person person);

    public Person getPerson(String phone) throws Exception;

    public void editPerson(Person person, String phone) throws Exception;

    public void deletePerson(Person person) throws Exception;

    public List<Person> getPersonsWithHobby(Hobby hobby) throws Exception;

    public List<Person> getPersonsInCity(CityInfo city) throws Exception;

    public Long getPersonCountWithHobby(Hobby hobby);
}
