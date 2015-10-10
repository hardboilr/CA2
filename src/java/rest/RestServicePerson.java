package rest;

import com.jayway.restassured.response.ResponseBody;
import deploy.DeploymentConfiguration;
import entities.Person;
import exception.PersonNotFoundException;
import exception.PhoneExistException;
import facade.PersonFacade;
import interfaces.IPersonFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    @GET
    @Path("/complete")
    @Produces("application/json")
    public Response getAllPersons() {
        List<Person> persons = facade.getAllPersons();
        return Response.ok(JSONConverter.getJSONFromPerson(persons)).build();
    }

    @GET
    @Path("/get/{phone}")
    @Produces("application/json")
    public Response getPerson(@PathParam("phone") String phone) throws PersonNotFoundException {
            Person p = facade.getPerson(phone);
            return Response.ok(JSONConverter.getJSONFromPerson(p)).build();
    }

    @GET
    @Path("/hobby/{hobby}")
    @Produces("application/json")
    public Response getPersonsWithHobby(@PathParam("hobby") String hobby) throws PersonNotFoundException {
            List<Person> personList = facade.getPersonsWithHobby(hobby);
            return Response.ok(JSONConverter.getJSONFromPerson(personList)).build();
    }

    @GET
    @Path("/city/{zipcode}")
    @Produces("application/json")
    public Response getPersonsInCity(@PathParam("zipcode") int zipcode) throws PersonNotFoundException {
            List<Person> personList = facade.getPersonsInCity(zipcode);
            return Response.ok(JSONConverter.getJSONFromPerson(personList)).build();
    }

    @GET
    @Path("/hobbycount/{hobby}")
    @Produces("application/json")
    public Response getPersonCountWithHobby(@PathParam("hobby") String hobby) throws PersonNotFoundException {
            Long count = facade.getPersonCountWithHobby(hobby);
            return Response.ok(JSONConverter.getJSONFromCount(count)).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response createPerson(String person) throws PhoneExistException {
            Person p = JSONConverter.getPersonFromJson(person);
            return Response.status(Response.Status.CREATED).entity(JSONConverter.getJSONFromPerson(facade.createPerson(p))).build();
    }

    @PUT
    @Path("/edit/{phone}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response editPerson(@PathParam("phone") String phone, String person) throws PersonNotFoundException {
        Person p = JSONConverter.getPersonFromJson(person);
            return Response.ok(JSONConverter.getJSONFromPerson(facade.editPerson(p, phone))).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces("application/json")
    public Response deletePerson(@PathParam("id") long id) throws PersonNotFoundException {
            return Response.status(Response.Status.OK).entity(JSONConverter.getJSONFromPerson(facade.deletePerson(id))).build();
    }
}
