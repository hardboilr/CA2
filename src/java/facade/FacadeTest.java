package facade;

import deploy.DeploymentConfiguration;
import entities.Company;
import interfaces.ICompanyFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FacadeTest {
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
    private EntityManager em = emf.createEntityManager();
    private ICompanyFacade compFacade = new CompanyFacade(emf);
    public String getString() {
        Company comp = null;
        try {
            comp = compFacade.getCompany(53924216);
        } catch (Exception ex) {
            Logger.getLogger(FacadeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return comp.getName();
    }
}
