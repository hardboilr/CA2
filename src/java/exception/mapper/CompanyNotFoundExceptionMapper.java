package exception.mapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exception.CompanyNotFoundException;
import exception.ErrorMessage;
import exception.ErrorMessage;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CompanyNotFoundExceptionMapper implements ExceptionMapper<CompanyNotFoundException> {

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    ServletContext context;

    @Override
    public Response toResponse(CompanyNotFoundException e) {
        boolean isDebug = context.getInitParameter("debug").toLowerCase().equals("true");
        ErrorMessage em = new ErrorMessage(e, Response.Status.NOT_FOUND.getStatusCode(), isDebug);
        return Response.status(Response.Status.NOT_FOUND).entity(gson.toJson(em)).type(MediaType.APPLICATION_JSON).build();
    }

}
