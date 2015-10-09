package utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import entities.Address;
import entities.CityInfo;
import entities.Company;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.ArrayList;
import java.util.List;

public class JSONConverter {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public JSONConverter() {

    }

    public static Person getPersonFromJson(String js) {
        System.out.println("getPersonFromJson:\n" + js);
        Person person = new Person();
        JsonObject jo = gson.fromJson(js, JsonObject.class);

        person.setFirstName(jo.get("firstName").getAsString());
        person.setLastName(jo.get("lastName").getAsString());
        person.setEmail(jo.get("email").getAsString());
        
        CityInfo city = new CityInfo();
        city.setCity(jo.get("city").getAsString());
        city.setZipCode(jo.get("zipCode").getAsInt());

        Address address = new Address();
        address.setStreet(jo.get("street").getAsString());
        address.setAdditionalInfo(jo.get("additionalInfo").getAsString());

        address.setCityInfo(city);
        person.setAddress(address);
        

        JsonArray phones = jo.get("phones").getAsJsonArray();
        List<JsonObject> phoneJsonObject = new ArrayList();
        for (int i = 0; i < phones.size(); i++) {
            phoneJsonObject.add(phones.get(i).getAsJsonObject());
        }
        for (JsonObject phoneJsonObject1 : phoneJsonObject) {
            person.addPhone(new Phone(phoneJsonObject1.get("number").getAsString(), phoneJsonObject1.get("description").getAsString()));
        }

        JsonArray hobbies = jo.get("hobbies").getAsJsonArray();
        List<JsonObject> hobbyJsonObject = new ArrayList();
        for (int i = 0; i < hobbies.size(); i++) {
            hobbyJsonObject.add(hobbies.get(i).getAsJsonObject());
        }
        for (JsonObject hobbyJsonObject1 : hobbyJsonObject) {
            person.addHobby(new Hobby(hobbyJsonObject1.get("name").getAsString(), hobbyJsonObject1.get("description").getAsString()));
        }
        return person;
    }

    public static String getJSONFromPerson(Person person) {
        JsonObject joPerson = new JsonObject();
        joPerson.addProperty("id", person.getId());
        joPerson.addProperty("firstName", person.getFirstName());
        joPerson.addProperty("lastName", person.getLastName());
        joPerson.addProperty("email", person.getEmail());
        joPerson.addProperty("street", person.getAddress().getStreet());
        joPerson.addProperty("additionalInfo", person.getAddress().getAdditionalInfo());
        joPerson.addProperty("city", person.getAddress().getCityInfo().getCity());
        joPerson.addProperty("zipCode", person.getAddress().getCityInfo().getZipCode());
        JsonArray jaPhone = new JsonArray();
        for (Phone phone : person.getPhones()) {
            JsonObject jso = new JsonObject();
            jso.addProperty("number", phone.getNumber());
            jso.addProperty("description", phone.getDescription());
            jaPhone.add(jso);
        }
        joPerson.add("phones", jaPhone);
        JsonArray jaHobby = new JsonArray();
        for (Hobby hobby : person.getHobbies()) {
            JsonObject jso = new JsonObject();
            jso.addProperty("name", hobby.getName());
            jso.addProperty("description", hobby.getDescription());
            jaHobby.add(jso);
        }
        joPerson.add("hobbies", jaHobby);
        System.out.println("getJsonFromPerson:\n" + joPerson.toString());
        return gson.toJson(joPerson);
    }

    public static String getJSONFromPerson(List<Person> persons) {
        JsonArray jaPersons = new JsonArray();
        JsonObject joPersons = new JsonObject();
        for (Person p : persons) {
            JsonObject jo = new JsonObject();
            jo.addProperty("id", p.getId());
            jo.addProperty("firstName", p.getFirstName());
            jo.addProperty("lastName", p.getLastName());
            jo.addProperty("email", p.getEmail());
            jo.addProperty("street", p.getAddress().getStreet());
            jo.addProperty("additionalInfo", p.getAddress().getAdditionalInfo());
            jo.addProperty("city", p.getAddress().getCityInfo().getCity());
            jo.addProperty("zipcode", p.getAddress().getCityInfo().getZipCode());
            JsonArray jaPhone = new JsonArray();
            for (Phone phone : p.getPhones()) {
                JsonObject jso = new JsonObject();
                jso.addProperty("number", phone.getNumber());
                jso.addProperty("description", phone.getDescription());
                jaPhone.add(jso);
            }
            jo.add("phones", jaPhone);
            JsonArray jaHobby = new JsonArray();
            for (Hobby hobby : p.getHobbies()) {
                JsonObject jso = new JsonObject();
                jso.addProperty("name", hobby.getName());
                jso.addProperty("description", hobby.getDescription());
                jaHobby.add(jso);
            }
            jo.add("hobbies", jaHobby);
            jaPersons.add(jo);
        }
        joPersons.add("persons", jaPersons);
        return gson.toJson(jaPersons);
    }

    public static String getJSONFromCount(Long count) {
        JsonObject jo = new JsonObject();
        jo.addProperty("count", count);
        return gson.toJson(jo);
    }

    public static Company getCompanyFromJson(String js) {
        System.out.println("getCompanyFromJson:" + js);
        
        Company company = new Company();
        
        JsonObject jo = gson.fromJson(js, JsonObject.class);
        
        company.setEmail(jo.get("email").getAsString());
        company.setName(jo.get("name").getAsString());
        company.setDescription(jo.get("description").getAsString());
        company.setCvr(jo.get("cvr").getAsInt());
        company.setNumEmployees(jo.get("numEmployees").getAsInt());
        company.setMarketValue(jo.get("marketValue").getAsLong());
        
        CityInfo city = new CityInfo();
        city.setCity(jo.get("city").getAsString());
        city.setZipCode(jo.get("zipCode").getAsInt());
        
        Address address = new Address();
        address.setCityInfo(city);
        address.setStreet(jo.get("street").getAsString());
        address.setAdditionalInfo(jo.get("additionalInfo").getAsString());
        
        company.setAddress(address);
        
        JsonArray phones = jo.get("phones").getAsJsonArray();
        List<JsonObject> phoneJsonObject = new ArrayList();
        for (int i = 0; i < phones.size(); i++) {
            phoneJsonObject.add(phones.get(i).getAsJsonObject());
        }
        for (JsonObject phoneJsonObject1 : phoneJsonObject) {
            company.addPhone(new Phone(phoneJsonObject1.get("number").getAsString(), phoneJsonObject1.get("description").getAsString()));
        }
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
        jsonObject.addProperty("numEmployees", c.getNumEmployees());
        jsonObject.addProperty("marketValue", c.getMarketValue());
        JsonArray jsonArray = new JsonArray();
        for (Phone phone : c.getPhones()) {
            JsonObject jso = new JsonObject();
            jso.addProperty("number", phone.getNumber());
            jso.addProperty("description", phone.getDescription());
            jsonArray.add(jso);
        }
        jsonObject.add("phones", jsonArray);
        System.out.println("Street: " + c.getAddress().getStreet());
        jsonObject.addProperty("street", c.getAddress().getStreet());
        jsonObject.addProperty("additionalInfo", c.getAddress().getAdditionalInfo());
        jsonObject.addProperty("zipCode", c.getAddress().getCityInfo().getZipCode());
        jsonObject.addProperty("city", c.getAddress().getCityInfo().getCity());
        System.out.println("getJSONFromCompany: " + jsonObject.toString());
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
            jsoCompany.addProperty("numEmployees", c.getNumEmployees());
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
            jsoCompany.addProperty("zipCode", c.getAddress().getCityInfo().getZipCode());
            jsoCompany.addProperty("city", c.getAddress().getCityInfo().getCity());
            jsoCompanyArray.add(jsoCompany);
        }
        jsoCompanies.add("companies", jsoCompanyArray);
        return gson.toJson(jsoCompanyArray);
    }
}
