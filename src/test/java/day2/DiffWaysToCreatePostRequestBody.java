package day2;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class DiffWaysToCreatePostRequestBody {

    //1. Using Hashmaps
    @Test
    void testPostUsingHashMap(){
        HashMap data = new HashMap();
        data.put("email", "rajat-hashmap@test.com");
        data.put("first_name", "Rajat-hashmap");
        data.put("last_name", "Devadiga-hashmap");
        data.put("avatar", "https://testavatarhashmap.com");

        String courseArr[] = {"C", "C++"};
        data.put("course", courseArr);

        given()
                .contentType("application/json")
                .body(data)

        .when()
                .post("http://localhost:3000/data")

        .then()
                .statusCode(201)
                .body("email", equalTo("rajat-hashmap@test.com"))
                .body("first_name", equalTo("Rajat-hashmap"))
                .body("last_name", equalTo("Devadiga-hashmap"))
                .body("avatar", equalTo("https://testavatarhashmap.com"))
                .body("course[0]",equalTo("C"))
                .body("course[1]", equalTo("C++"))
                .header("Content-Type", "application/json")
                .log().all();
    }


}
