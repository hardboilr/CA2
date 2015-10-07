/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import facade.FacadeTest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author tobias
 */
@Path("company")
public class RestServiceCompany {

    @Context
    private UriInfo context;

    FacadeTest facade = new FacadeTest();
    /**
     * Creates a new instance of RestServiceCompany
     */
    public RestServiceCompany() {
        
    }

    /**
     * Retrieves representation of an instance of rest.RestServiceCompany
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/complete")
    @Produces("application/text")
    public String getJson() {
        String test = facade.getString();
        System.out.println(test);
        return test;
    }

    /**
     * PUT method for updating or creating an instance of RestServiceCompany
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
