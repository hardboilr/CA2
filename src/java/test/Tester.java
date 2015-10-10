package test;

import deploy.DeploymentConfiguration;
import entities.Person;
import exception.PersonNotFoundException;
import facade.PersonFacade;
import interfaces.IPersonFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Tester {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
        IPersonFacade facade = new PersonFacade(emf);

        try {
            facade.deletePerson(2016L);
            facade.deletePerson(2017L);
//            Person p = facade.getPerson("53555359");
//            System.out.println("firstname " + p.getFirstName());
//            p.setFirstName("Henrik");
//            facade.editPerson(p, "53555359");
//            Person p1 = facade.getPerson("53555359");
//            System.out.println("firstname " + p1.getFirstName());
        } catch (PersonNotFoundException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
