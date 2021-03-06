package exception.mapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import exception.CompanyNotFoundException;
import exception.PersonNotFoundException;
import exception.PhoneExistException;
import java.util.Arrays;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PhoneExistExceptionMapper implements ExceptionMapper<PhoneExistException> {

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    ServletContext context;

    @Override
    public Response toResponse(PhoneExistException e) {
        JsonObject jo = new JsonObject();
        if(Boolean.valueOf(context.getInitParameter("debug"))){
            jo.addProperty("StackTrace", Arrays.toString(e.getStackTrace()));
        }
        jo.addProperty("Message", e.getMessage());
        return Response.status(Response.Status.CONFLICT).entity(jo.toString()).build();
    }

}

