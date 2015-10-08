package test;

import deploy.DeploymentConfiguration;
import entities.Person;
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
    
    Person p = null;
        try {
            p = facade.getPerson("00207391");
        } catch (Exception ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("firstName " + p.getFirstName());
}
}
