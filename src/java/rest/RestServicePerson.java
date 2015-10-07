package rest;

import deploy.DeploymentConfiguration;
import entities.Person;
import facade.PersonFacade;
import interfaces.IPersonFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

@Path("person")
public class RestServicePerson {

    @Context
    private UriInfo context;

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
    IPersonFacade facade = new PersonFacade(emf);

    public RestServicePerson() {
    }

    /**
     * Retrieves representation of an instance of rest.RestServicePerson
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/complete/{phone}")
    @Produces("application/text")
    public String getJson() {
        Person p = null;
        try {
            p = facade.getPerson("00055320");
        } catch (Exception ex) {
            Logger.getLogger(RestServicePerson.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p.getFirstName();
    }
    
//    @GET
//    @Path("/hobby/{hobby}")
    

//    @PUT
//    @Path("complete/{phone}")
//    @Consumes("application/text")
//    public void putJson(String content) {
//    }
}
