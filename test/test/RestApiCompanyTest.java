package test;

import static com.jayway.restassured.RestAssured.basePath;
import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.defaultParser;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static com.jayway.restassured.http.ContentType.JSON;
import com.jayway.restassured.parsing.Parser;
import entities.Company;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 * @author Tobias Jacobsen
 */
public class RestApiCompanyTest {

    public RestApiCompanyTest() {
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        baseURI = "http://localhost:8080";
        defaultParser = Parser.JSON;
        basePath = "/CA2/api/company";
    }

    /*
     JSON example-1
     {
     "name": "xxx",
     "email" : "xxx",
     "phones": [{"number":"xx","description":"xx"},{"number":"xx","description":"xx"},..]
     "description":"xxx",
     "cvr":"xxx",
     "numEmployees:"xxx",
     "marketValue":"xxx",
     "street": "xxxx",
     "additionalInfo" : "xxxx",
     "zipcode": "nnnn",
     "city" : "xxxx"
     }
     */
    @Test
    public void testGetComplete() {
        when().
                get("complete/1001").
                then().
                statusCode(200).body("name", equalTo("Youspan")).
                body("email", equalTo("sphillips0@networkadvertising.org")).
                body("phones.number", hasItems(98249125, 65603329, 90231555)).
                body("cvr", equalTo(53924216));
    }

    /*
     {  
     'cvr':'xxx',
     'name':'xxx',
     'description':'xxx',
     'NumEmployees':'xxx',
     'marketValue':'xxx'
     }
     */
    @Test
    public void testGetCvr() {
        when().
                get("cvr/78618755").
                then().
                statusCode(200).body("cvr", equalTo(78618755)).
                body("name", equalTo("Jaxnation")).
                body("description", equalTo("auctor sed tristique in tempus sit amet sem")).
                body("numEmployees", equalTo(7849)).
                body("marketValue", equalTo(79612098));
    }

    @Test
    public void testCreateCompany() {
        Company c = new Company(31203083l, "Tobias Company", "The bestest company", 1, 1000000l);
        given().
                contentType(JSON).
                body(c).
                when().
                post().
                then().
                statusCode(201).body("cvr", equalTo(c.getCvr())).
                body("name", equalTo(c.getName()));
    }

    @Test
    public void testUpdateCompany() {
        Company c = new Company(31203083l, "Tobias Company", "The bestest company", 1, 1000000l);
        given().
                contentType(JSON).
                body(c).
                when().
                post().
                then().
                statusCode(200).body("cvr", equalTo(c.getCvr())).
                body("name", equalTo(c.getName()));
    }

    @Test
    public void testDeleteCompany() {
        Company c = new Company(31203083l, "Tobias Company", "The bestest company", 1, 1000000l);
        when().
                delete("deletee/40544424").
                then().
                statusCode(200).body("cvr", equalTo("40544424")).
                body("name", equalTo("Edgetag"));
    }
}
