package day2;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class DiffWaysToCreatePostRequestBody {

    //1. Using Hashmaps
    //@Test(priority = 1)
    void testPostUsingHashMap(){
        HashMap data = new HashMap();
        data.put("email", "rajat-hashmap@test.com");
        data.put("first_name", "Rajat-hashmap");
        data.put("last_name", "Devadiga-hashmap");
        data.put("avatar", "https://testavatarhashmap.com");

        String[] courseArr = {"C", "C++"};
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

    //Deleting student record
    @Test(priority = 2)
    void testDelete(){

        given()

        .when()
                .delete("http://localhost:3000/data/7")
        .then()
                .statusCode(200);

    }

    //2. Using ORG.JSON
    //@Test(priority = 1)
    void testPostUsingJsonLibrary(){


        JSONObject data = new JSONObject();
        data.put("email", "rajat-JSON@test.com");
        data.put("first_name", "Rajat-JSON");
        data.put("last_name", "Devadiga-JSON");
        data.put("avatar", "https://testavatarJSON.com");

        String[] courses = {"C", "C++"};
        data.put("courses", courses);

        given()
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("http://localhost:3000/data")

                .then()
                .statusCode(201)
                .body("email", equalTo("rajat-JSON@test.com"))
                .body("first_name", equalTo("Rajat-JSON"))
                .body("last_name", equalTo("Devadiga-JSON"))
                .body("avatar", equalTo("https://testavatarJSON.com"))
                .body("courses[0]",equalTo("C"))
                .body("courses[1]", equalTo("C++"))
                .header("Content-Type", "application/json")
                .log().all();
    }

    //3. Using POJO
    //@Test(priority = 1)
    void testPostUsingPOJO(){

        Pojo_PostRequest data = new Pojo_PostRequest();

        data.setEmail("rajat-POJO@test.com");
        data.setFirst_name("Rajat-POJO");
        data.setLast_name("Devadiga-POJO");
        data.setAvatar("https://testavatarPOJO.com");

        String[] courses = {"C", "C++"};
        data.setCourses(courses);


        given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("http://localhost:3000/data")

                .then()
                .statusCode(201)
                .body("email", equalTo("rajat-POJO@test.com"))
                .body("first_name", equalTo("Rajat-POJO"))
                .body("last_name", equalTo("Devadiga-POJO"))
                .body("avatar", equalTo("https://testavatarPOJO.com"))
                .body("courses[0]",equalTo("C"))
                .body("courses[1]", equalTo("C++"))
                .header("Content-Type", "application/json")
                .log().all();
    }

    //4. Using External JSON File
    @Test(priority = 1)
    void testUsingExternalJsonFile() throws FileNotFoundException {

        File f = new File("body.json");
        FileReader fr = new FileReader(f);
        JSONTokener jt = new JSONTokener(fr);

        JSONObject data = new JSONObject(jt);


        given()
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("http://localhost:3000/data")

                .then()
                .statusCode(201)
                .body("email", equalTo("rajat-External@test.com"))
                .body("first_name", equalTo("Rajat-External"))
                .body("last_name", equalTo("Devadiga-External"))
                .body("avatar", equalTo("https://testavatarExternal.com"))
                .body("courses[0]",equalTo("C"))
                .body("courses[1]", equalTo("C++"))
                .header("Content-Type", "application/json")
                .log().all();
    }

}
