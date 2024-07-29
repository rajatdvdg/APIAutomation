package day5;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class ParsingXMLResponse {

    @Test
    void testXMLResponse(){

        //Approach 1

        given()

        .when()
                .get("GET - https://thetestrequest.com/authors.xml")

        .then()
                .statusCode(200)
                .header("Content-Type","application/xml; charset=utf-8")
                .body()
    }

}
