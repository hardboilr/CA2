package rest;

import deploy.DeploymentConfiguration;
import entities.Company;
import exception.CompanyNotFoundException;
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
import org.apache.commons.lang3.NotImplementedException;
import utility.JSONConverter;

/**
 * TODO a. Get a list of companies with more than xx employes b. Get a list of
 * companies with marketvalue more than xx
 */
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
    public Response getCompany(@PathParam("cvr") long cvr) {
        try {
            Company c = facade.getCompany(cvr);
            return Response.ok(JSONConverter.getJSONFromCompany(c)).build();
        } catch (CompanyNotFoundException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
        }
    }

    /*Not implemented*/
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

    /*Not implemented*/
    @GET
    @Path("employees/{employees}")
    @Produces("application/json")
    public Response getCompanyMoreEmployeesThan(@PathParam("employees") long employees){
            try{List<Company> companies = facade.getCompaniesWithEmployeeCount(employees);
            return Response.ok(JSONConverter.getJSONFromCompany(companies)).build();
            } catch(CompanyNotFoundException ex){
                return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
            }
    }

    /*OK*/
    @POST
    @Consumes("application/json")
    public Response createCompany(String company) {
        Company c = JSONConverter.getCompanyFromJson(company);
        return Response.status(Response.Status.CREATED).entity(JSONConverter.getJSONFromCompany(facade.createCompany(c))).build();
    }

    /*OK*/
    @PUT
    @Consumes("application/json")
    public Response editCompany(String company) {
        Company c = JSONConverter.getCompanyFromJson(company);
        try {
            return Response.ok(JSONConverter.getJSONFromCompany(facade.editCompany(c))).build();
        } catch (CompanyNotFoundException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
        }
    }

    /*OK*/
    @DELETE
    @Consumes("application/json")
    @Path("/delete/{cvr}")
    public Response deleteCompany(@PathParam("cvr") long cvr) {
        try {
            Company c = facade.deleteCompany(cvr);
            return Response.ok(JSONConverter.getJSONFromCompany(c)).build();
        } catch (CompanyNotFoundException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
