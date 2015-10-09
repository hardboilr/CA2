package test;

import static com.jayway.restassured.RestAssured.basePath;
import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.defaultParser;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static com.jayway.restassured.http.ContentType.JSON;
import com.jayway.restassured.parsing.Parser;
import entities.Address;
import entities.CityInfo;
import entities.Company;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import utility.JSONConverter;

public class RestApiPersonTest {

    public RestApiPersonTest() {
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        baseURI = "http://localhost:8080";
        defaultParser = Parser.JSON;
        basePath = "/CA2/api/person";
    }

    @Test //PASSED
    public void testGetComplete() {
        when().
                get("/complete").
                then().
                contentType(JSON).
                statusCode(200).body("id", hasItems(1, 2, 999, 1000)).
                body("firstName", hasItems("Willie", "Gerald", "Lisa", "Wayne", "Adam")).
                body("lastName", hasItems("Kennedy", "Ward", "Bailey", "Johnston", "Larson")).
                body("email", hasItems("nlarsonl2@yelp.com", "ljamescw@fc2.com", "nreynoldsar@typepad.com", "sarnold7o@guardian.co.uk", "jpierce5b@edublogs.org")).
                body("phones.number[0]", hasItems("27038971", "50206142", "66575394")). //get first phone number (JsonPath)
                //                body("phones.number[-1]", hasItems("14572726", "16216755", "86001486")). //get last phone number (JsonPath)
                body("hobbies.name[0]", hasItems("grand theft auto", "mushroom hunting", "soapmaking")). //get first phone number (JsonPath)
                //                body("hobbies.name[-1]", hasItems("geocaching", "grand theft auto", "sewing")).
                body("street", hasItems("680 Park Meadow Place"));
    }

    @Test //PASSED
    public void testGetPerson() {
        when().
                get("get/27038971"). //id 1
                then().
                contentType(JSON).
                statusCode(200).body("firstName", equalTo("Christine")).
                body("lastName", equalTo("Thomas")).
                body("email", equalTo("cthomas0@live.com")).
                body("phones.number", hasItems("27038971", "50206142", "66575394")).
                body("hobbies.name", hasItems("grand theft auto", "mushroom hunting", "soapmaking")).
                body("street", equalTo("5 Bellgrove Trail"));
        when().
                get("get/14572726"). //id 1000
                then().
                contentType(JSON).
                statusCode(200).body("firstName", equalTo("Willie")).
                body("lastName", equalTo("Simpson")).
                body("email", equalTo("wsimpsonrr@eepurl.com")).
                body("phones.number", hasItems("14572726", "16216755", "86001486")).
                body("hobbies.name", hasItems("geocaching", "grand theft auto", "sewing")).
                body("street", equalTo("680 Park Meadow Place"));
    }

    @Test //PASSED
    public void createPerson() {
        Person p = new Person("Tobias", "Jacobsen");
        p.setEmail("tobias.cbs@gmail.com");
        Phone phone1 = new Phone("98765432", "iphone");
        Phone phone2 = new Phone("87654321", "stationary");
        p.addPhone(phone1);
        p.addPhone(phone2);
        CityInfo cityInfo = new CityInfo(2000, "Frederiksberg");
        Address address = new Address("Smallegade 46A", "2. th.", cityInfo);
        p.setAddress(address);

        Hobby hob1 = new Hobby("druk", "drinking ftw");
        Hobby hob2 = new Hobby("dance", "dancing and having a gay time");
        p.addHobby(hob1);
        p.addHobby(hob2);

        given().
                contentType(JSON).
                body(JSONConverter.getJSONFromPerson(p)).
                when().
                post().
                then().
                contentType(JSON).
                statusCode(201).body("firstName", equalTo(p.getFirstName())).
                body("lastName", equalTo(p.getLastName())).
                body("hobbies.name", hasItems(p.getHobbies().get(0).getName()));
    }

    @Test
    public void editPerson() {
        Person p = new Person("Tobias", "Jacobsen");
        p.setEmail("tobias.cbs@gmail.com");
        Phone phone1 = new Phone("51887460", "iphone");
        Phone phone2 = new Phone("53555358", "stationary");
        p.addPhone(phone1);
        p.addPhone(phone2);
        CityInfo cityInfo = new CityInfo(2000, "Frederiksberg");
        Address address = new Address("Smallegade 46A", "2. th.", cityInfo);
        p.setAddress(address);
        Hobby hob1 = new Hobby("druk", "drinking ftw");
        Hobby hob2 = new Hobby("dance", "dancing and having a gay time");
        p.addHobby(hob1);
        p.addHobby(hob2);

        given().
                contentType(JSON).
                body(JSONConverter.getJSONFromPerson(p)).
                when().
                put("/edit/00207391").
                then().
                contentType(JSON).
                statusCode(200).body("firstName", equalTo(p.getFirstName())).
                body("lastName", equalTo(p.getLastName())).
                body("hobbies.name", hasItems(p.getHobbies().get(0).getName()));
    }
    
        @Test //wait for facade implementation
    public void testDeletePerson() {
        when().
                delete("delete/73").
                then().
                contentType(JSON).
                statusCode(200).body("firstName", equalTo("Kevin")).
                body("lastName", equalTo("Gutierrez"));
    }
}