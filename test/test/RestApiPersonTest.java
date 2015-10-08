package test;

import static com.jayway.restassured.RestAssured.basePath;
import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.defaultParser;
import static com.jayway.restassured.RestAssured.when;
import com.jayway.restassured.parsing.Parser;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class RestApiPersonTest {

    public RestApiPersonTest() {
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        baseURI = "http://localhost:8080";
        defaultParser = Parser.JSON;
        basePath = "/CA2/api/person";
    }

    /*
     JSON example-1
     {
     "firstName": "xxx",
     "lastName" : "xxx",
     "email" : "xxx",
     "phones": [{"number":"xx","description":"xx"},{"number":"xx","description":"xx"},..]
     "street": "xxxx",
     "additionalInfo" : "xxxx",
     "zipcode": "nnnn",
     "city" : "xxxx"
     }
     */
    @Test
    public void testGetAll() {
        when().
                get("complete").
                then().
                statusCode(200).body("firstName", hasItems("Christine", "Tis"));
    }

    @Test
    public void testGet() {
        when().
                get("complete/1").
                then().
                statusCode(200).body("firstName", equalTo("Christine")).
                body("lastName", equalTo("Thomas")).
                body("email", equalTo("cthomas0@live.com")).
                body("phones.number", hasItems(50206142, 27038971, 66575394)).
                body("street", equalTo("5 Bellgrove Trail"));

        when().
                get("complete/1000").
                then().
                statusCode(200).body("firstName", equalTo("Willie")).
                body("lastName", equalTo("Simpson")).
                body("email", equalTo("wsimpsonrr@eepurl.com")).
                body("phones.number", hasItems(14572726, 16216755, 86001486)).
                body("street", equalTo("680 Park Meadow Place"));
    }

    /*
     {
     "id":1,
     "name": "Lars Mortensen",
     "email":"lam@lam.dk",
     "phones": [{"number":"xx","description":"xx"},{"number":"xx","description":"xx"},..]
     }
     */
    @Test
    public void testGetContactInfo() {

    }

}
