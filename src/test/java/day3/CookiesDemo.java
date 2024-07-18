package day3;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class CookiesDemo {

    //@Test(priority = 1)
    void testCookies(){

        given()

        .when()
                .get("https://www.google.com/")

        .then()
                .cookie("AEC", "AVYB7crqlEmbu2MSOptlDQcO8DYd7nxlmS0HpifJbSTBzy5F65JfqYgb2g")
                .log().all();

    }

    @Test(priority = 2)
    void getCookiesInfo(){

        Response res = given()

        .when()
                .get("https://www.google.com/");

        //get single cookie info
        //String cookie_value = res.getCookie("AEC");
        //System.out.println("value of cookie is ===> " + cookie_value);

        //get all cookies info
        Map<String, String> cookie_values = res.getCookies();

        //System.out.println(cookie_values.keySet());
        for(String k: cookie_values.keySet()){
            String cookie_value = res.getCookie(k);
            System.out.println(k + " " + cookie_value);
        }
    }
}
