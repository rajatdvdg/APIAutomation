package day3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HeadersDemo {

    //@Test(priority = 1)
    void testHeaders(){

        given()

        .when()
                .get("https://www.google.com/")

        .then()
                .header("Content-Type", "text/html; charset=ISO-8859-1")
                .header("Content-Encoding", "gzip")
                .header("Server", "gws");

    }

    @Test(priority = 2)
    void getHeaders(){

        Response res = given()

        .when()
                .get("https://www.google.com/");

        //get single header info
        //String header_value = res.getHeader("Content-Type");
        //System.out.println("The value of content-type is ===> " + header_value);

        //get all headers info
        Headers headers_values = res.getHeaders();
        for(Header k: headers_values)  {
            System.out.println(k + " "+k.getValue());
        }
    }
}
