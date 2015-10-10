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
import entities.Phone;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import utility.JSONConverter;

public class RestApiCompanyTest {

    public RestApiCompanyTest() {
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        baseURI = "http://localhost:8080";
        defaultParser = Parser.JSON;
        basePath = "/CA2/api/company";
    }

    @Test //PASSED
    public void testGetComplete() {
        when().
                get("/complete").
                then().
                contentType(JSON).
                statusCode(200).body("id", hasItems(1001, 1002, 1999, 2000)).
                body("name", hasItems("Jaxnation", "Buzzdog", "Pixonyx", "Youspan", "Zoozzy")).
                body("cvr", hasItems(53924216, 84030264, 90892298, 95344769, 78618755)).
                body("phones.number[0]", hasItems("65603329", "90231555", "98249125")). //get first phone number (JsonPath)
                body("street", hasItems("9369 Fordem Drive"));
    }

    @Test //PASSED
    public void testGetCvr() {
        when().
                get("/94727634").
                then().
                contentType(JSON).
                statusCode(200).body("cvr", equalTo(94727634)).
                body("name", equalTo("Zoomcast")).
                body("description", equalTo("sapien iaculis congue vivamus metus arcu")).
                body("numEmployees", equalTo(24226)).
                body("street", equalTo("803 Garrison Place")).
                body("additionalInfo", equalTo("st.tv")).
                body("zipCode", equalTo(2690)).
                body("city", equalTo("Karlslunde"));
    }

    @Test //PASSED
    public void testCreateCompany() {
        Company c = new Company(31203083, "Tobias Company", "The bestest company", 1, 1000000l);
        Phone phone1 = new Phone("46403083", "iphone");
        Phone phone2 = new Phone("12345678", "stationary");
        c.addPhone(phone1);
        c.addPhone(phone2);
        CityInfo cityInfo = new CityInfo(2000, "Frederiksberg");
        Address address = new Address("Smallegade 46A", "2. th.", cityInfo);
        c.setAddress(address);
        c.setEmail("tobias.cbs@gmail.com");
        given().
                auth().basic("test","test").
                body(JSONConverter.getJSONFromCompany(c)).
                when().
                post().
                then().
                contentType(JSON).
                statusCode(201).body("cvr", equalTo(c.getCvr())).
                body("name", equalTo(c.getName()));
    }

    @Test //PASSED
    public void testUpdateCompany() {
        Company c = new Company(31203083, "Tobias Company", "The bestest company", 1, 1000000l);
        c.setCvr(53924216);
        Phone phone1 = new Phone("31203083", "iphone");
        Phone phone2 = new Phone("29654310", "stationary");
        c.addPhone(phone1);
        c.addPhone(phone2);
        CityInfo cityInfo = new CityInfo(2000, "Frederiksberg");
        Address address = new Address("Smallegade 46A", "2. th.", cityInfo);
        c.setAddress(address);
        c.setEmail("tobias.cbs@gmail.com");
        given().
                auth().basic("test", "test").
                body(JSONConverter.getJSONFromCompany(c)).
                when().
                put().
                then().
                contentType(JSON).
                statusCode(200).body("cvr", equalTo(c.getCvr())).
                body("name", equalTo(c.getName()));
    }

    @Test //wait for facade implementation
    public void testDeleteCompany() {
        given().
                auth().basic("test", "test").
                when().
                delete("delete/41362104").
                then().
                contentType(JSON).
                statusCode(200).body("cvr", equalTo(41362104)).
                body("name", equalTo("Pixope"));
    }
}
