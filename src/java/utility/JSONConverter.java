/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entities.Address;
import entities.CityInfo;
import entities.Company;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sebastiannielsen
 */
public class JSONConverter {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public JSONConverter() {

    }

    public static Person getPersonFromJson(String js) {
        Person person = new Person();
        JsonObject jo = gson.fromJson(js, JsonObject.class);

        person.setFirstName(jo.get("firstName").getAsString());
        person.setLastName(jo.get("lastName").getAsString());
        person.setEmail(jo.get("email").getAsString());

        Address address = new Address();
        address.setStreet(jo.get("street").getAsString());
        address.setAdditionalInfo(jo.get("additionalInfo").getAsString());
        address.addInfoEntity(person);

        CityInfo city = new CityInfo();
        city.setCity(jo.get("city").getAsString());
        city.setZipCode(jo.get("zipcode").getAsInt());
        city.addAddress(address);

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
        joPerson.addProperty("city", person.getAddress().getCity().getCity());
        joPerson.addProperty("zipcode", person.getAddress().getCity().getZipCode());
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
            jo.addProperty("city", p.getAddress().getCity().getCity());
            jo.addProperty("zipcode", p.getAddress().getCity().getZipCode());
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
        Company company = gson.fromJson(js, Company.class);
        return company;
    }

    public static String getJSONFromCompany(Company c) {
        return gson.toJson(c);
    }

    public static String getJSONFromCompany(List<Company> companies) {
        return gson.toJson(companies);
    }

}
