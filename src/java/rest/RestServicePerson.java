package rest;

import deploy.DeploymentConfiguration;
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import exception.PersonNotFoundException;
import exception.PhoneExistException;
import facade.PersonFacade;
import interfaces.IPersonFacade;
import java.util.List;
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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response;
import utility.JSONConverter;

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
    @Path("/complete")
    @Produces("application/json")
    public Response getAllPersons() {
        List<Person> persons = null;
        try {
            persons = facade.getAllPersons();
        } catch (Exception ex) {
            Logger.getLogger(RestServicePerson.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.ok(JSONConverter.getJSONFromPerson(persons)).build();
    }

    @GET
    @Path("/get/{phone}")
    @Produces("application/json")
    public Response getPerson(@PathParam("phone") String phone) throws PersonNotFoundException {
        try {
            Person p = facade.getPerson(phone);
            return Response.ok(JSONConverter.getJSONFromPerson(p)).build();
        } catch (PersonNotFoundException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/hobby/{hobby}")
    @Produces("application/json")
    public Response getPersonsWithHobby(@PathParam("hobby") String hobby) {
        try {
            List<Person> personList = facade.getPersonsWithHobby(hobby);
            return Response.ok(JSONConverter.getJSONFromPerson(personList)).build();
        } catch (PersonNotFoundException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/city/{zipcode}")
    @Produces("application/json")
    public Response getPersonsInCity(@PathParam("zipcode") int zipcode) {
        try {
            List<Person> personList = facade.getPersonsInCity(zipcode);
            return Response.ok(JSONConverter.getJSONFromPerson(personList)).build();
        } catch (PersonNotFoundException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/hobbycount/{hobby}")
    @Produces("application/json")
    public Response getPersonCountWithHobby(@PathParam("hobby") String hobby) {
        try {
            Long count = facade.getPersonCountWithHobby(hobby);
            return Response.ok(JSONConverter.getJSONFromCount(count)).build();
        } catch (PersonNotFoundException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes("application/json")
    public Response createPerson(String person) throws PhoneExistException {
        Person p = JSONConverter.getPersonFromJson(person);
        System.out.println(p.getEmail());
        System.out.println(p.getFirstName());
        System.out.println(p.getLastName());
        System.out.println(p.getAddress().getStreet());
        System.out.println(p.getAddress().getAdditionalInfo());
        System.out.println(p.getAddress().getCityInfo().getCity());
        System.out.println(p.getAddress().getCityInfo().getZipCode());
        for (Phone phone : p.getPhones()) {
            System.out.println(phone.getNumber());
            System.out.println(phone.getDescription());
        }
        for (Hobby hobby : p.getHobbies()) {
            System.out.println(hobby.getName());
            System.out.println(hobby.getDescription());
        }
        return Response.status(Response.Status.CREATED).entity(facade.createPerson(p)).build();
    }

    @PUT
    @Path("{phone}")
    @Consumes("application/json")
    public Response editPerson(@PathParam("phone") String phone, String person) {
        Person p = JSONConverter.getPersonFromJson(person);
        try {
            return Response.ok(facade.editPerson(p, phone)).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deletePerson(@PathParam("id") long id) {
        try {
            return Response.ok(facade.deletePerson(id)).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
        EntityManager em = emf.createEntityManager();
        Person p = new Person();
        p.setFirstName("Tobias");
        p.setLastName("afsklj");
        Address address = new Address();
        address.setAdditionalInfo(("2.tv"));
        address.setStreet("smallegade 46");
        CityInfo ci = em.find(CityInfo.class, 2800);
        address.setCityInfo(ci);
        p.setAddress(address);
        Phone phone = new Phone();
        phone.setNumber("31203083");
        p.addPhone(phone);
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        em.close();
        
    }
}
