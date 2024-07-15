package day1;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HTTPRequests {

    int id;
    @Test(priority = 1)
    void getUsers()
    {
        given()

        .when()
                .get("https://reqres.in/api/users?page=2")

        .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .log().all();
    }

    @Test(priority = 2)
    void createUser()
    {

        HashMap data = new HashMap();
        data.put("name", "rajat");
        data.put("job", "sdet");

        id = given()
                .contentType("application/json")
                .body(data)


        .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");



        //.then()
        //    .statusCode(201)
        //    .log().all();

    }

    @Test(priority = 3, dependsOnMethods = {"createUser"})
    void updateUser()
    {

        HashMap data = new HashMap();
        data.put("name", "rjt");
        data.put("job", "qa");

        given()
                .contentType("application/json")
                .body(data)

        .when()
                .put("https://reqres.in/api/users/"+id)

        .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 4, dependsOnMethods = {"createUser"})
    void deleteUser()
    {
        given()

        .when()
                .delete("https://reqres.in/api/"+id)


        .then()
                .statusCode(204)
                .log().all();
    }
}

