package rest;

import deploy.DeploymentConfiguration;
import entities.Company;
import exception.NotFoundException;
import exception.ExistException;
import facade.CompanyFacade;
import interfaces.ICompanyFacade;
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

@Path("company")
public class RestServiceCompany {

    @Context
    private UriInfo context;

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);

    ICompanyFacade facade = new CompanyFacade(emf);

    public RestServiceCompany() {
    }

    @GET
    @Path("/complete")
    @Produces("application/json")
    public Response getCompanies() {
        List<Company> companies = facade.getCompanies();
        return Response.ok(JSONConverter.getJSONFromCompany(companies)).build();
    }

    @GET
    @Path("{cvr}")
    @Produces("application/json")
    public Response getCompany(@PathParam("cvr") int cvr) {
        try {
            Company c = facade.getCompany(cvr);
            return Response.ok(JSONConverter.getJSONFromCompany(c)).build();
        } catch (NotFoundException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
        }
    }

    @GET
    @Path("marketvalue/{marketvalue}")
    @Produces("application/json")
    public Response getCompanyValuedMoreThan(@PathParam("marketvalue") long marketvalue) {
        try{List<Company> companies = facade.getCompaniesValuedMoreThan(marketvalue);
            return Response.ok(JSONConverter.getJSONFromCompany(companies)).build();
            } catch (Exception ex){
                return Response.ok(null).build();
//                return Response.ok(JSONConverter.getJSONFromString(ex.getMessage())).build();
            }
    }

    @GET
    @Path("employees/{employees}")
    @Produces("application/json")
    public Response getCompanyMoreEmployeesThan(@PathParam("employees") long employees){
            try{List<Company> companies = facade.getCompaniesWithEmployeeCount(employees);
            return Response.ok(JSONConverter.getJSONFromCompany(companies)).build();
            } catch(NotFoundException ex){
                return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
            }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response createCompany(String company) {
        try {
            Company c = JSONConverter.getCompanyFromJson(company);
            return Response.status(Response.Status.CREATED).entity(JSONConverter.getJSONFromCompany(facade.createCompany(c))).build();
        } catch (ExistException ex) {
            return Response.status(Response.Status.FORBIDDEN).entity(ex.getMessage()).build();
        }
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response editCompany(String company) {
        Company c = JSONConverter.getCompanyFromJson(company);
        try {
            return Response.ok(JSONConverter.getJSONFromCompany(facade.editCompany(c))).build();
        } catch (NotFoundException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
        }
    }

    @DELETE
    @Path("/delete/{cvr}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response deleteCompany(@PathParam("cvr") int cvr) {
        try {
            Company c = facade.deleteCompany(cvr);
            return Response.ok(JSONConverter.getJSONFromCompany(c)).build();
        } catch (NotFoundException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
        }
    }
}
