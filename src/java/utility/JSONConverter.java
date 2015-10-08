package utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entities.Company;
import entities.Person;
import entities.Phone;
import java.util.List;

public class JSONConverter {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public JSONConverter() {
    }

    public static Person getPersonFromJson(String js) {
        Person person = gson.fromJson(js, Person.class);
        return person;
    }

    public static String getJSONFromPerson(Person p) {
        return gson.toJson(p);
    }

    public static String getJSONFromPerson(List<Person> persons) {
        return gson.toJson(persons);
    }

    public static Company getCompanyFromJson(String js) {
        System.out.println(js);
        Company company = gson.fromJson(js, Company.class);
        return company;
    }

    /**
     * we would like to pass the company object directly into the "gson.toJson",
     * but can't due a stack overflow error. Therefore the "manual" parsing.
     *
     * @param c company object
     * @return string formatted as json
     */
    public static String getJSONFromCompany(Company c) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", c.getId());
        jsonObject.addProperty("email", c.getEmail());
        jsonObject.addProperty("name", c.getName());
        jsonObject.addProperty("description", c.getDescription());
        jsonObject.addProperty("cvr", c.getCvr());
        jsonObject.addProperty("NumEmployees", c.getNumEmployees());
        jsonObject.addProperty("marketValue", c.getMarketValue());
        JsonArray jsonArray = new JsonArray();
        for (Phone phone : c.getPhones()) {
            JsonObject jso = new JsonObject();
            jso.addProperty("number", phone.getNumber());
            jso.addProperty("description", phone.getDescription());
            jsonArray.add(jso);
        }
        jsonObject.add("phones", jsonArray);
        jsonObject.addProperty("street", c.getAddress().getStreet());
        jsonObject.addProperty("additionalInfo", c.getAddress().getAdditionalInfo());
        jsonObject.addProperty("zipCode", c.getAddress().getCity().getZipCode());
        jsonObject.addProperty("city", c.getAddress().getCity().getCity());
        return gson.toJson(jsonObject);
    }

    /**
     * we would like to pass the company object directly into the "gson.toJson",
     * but can't due a stack overflow error. Therefore the "manual" parsing.
     * OBS!!!--> we nest all companies in a companies-object: "jsoCompanies" .
     * Alternatively, return the jsonArray directly: "jsoCompanyArray"
     *
     * @param companies company object
     * @return string formatted as json
     */
    public static String getJSONFromCompany(List<Company> companies) {
        JsonArray jsoCompanyArray = new JsonArray();
        JsonObject jsoCompanies = new JsonObject();
        for (Company c : companies) {
            JsonObject jsoCompany = new JsonObject();
            jsoCompany.addProperty("id", c.getId());
            jsoCompany.addProperty("email", c.getEmail());
            jsoCompany.addProperty("name", c.getName());
            jsoCompany.addProperty("description", c.getDescription());
            jsoCompany.addProperty("cvr", c.getCvr());
            jsoCompany.addProperty("NumEmployees", c.getNumEmployees());
            jsoCompany.addProperty("marketValue", c.getMarketValue());
            JsonArray jsoPhoneArray = new JsonArray();
            for (Phone phone : c.getPhones()) {
                JsonObject jsoPhone = new JsonObject();
                jsoPhone.addProperty("number", phone.getNumber());
                jsoPhone.addProperty("description", phone.getDescription());
                jsoPhoneArray.add(jsoPhone);
            }
            jsoCompany.add("phones", jsoPhoneArray);
            jsoCompany.addProperty("street", c.getAddress().getStreet());
            jsoCompany.addProperty("additionalInfo", c.getAddress().getAdditionalInfo());
            jsoCompany.addProperty("zipCode", c.getAddress().getCity().getZipCode());
            jsoCompany.addProperty("city", c.getAddress().getCity().getCity());
            jsoCompanyArray.add(jsoCompany);
        }
        jsoCompanies.add("companies", jsoCompanyArray);
        return gson.toJson(jsoCompanies);
    }
}
