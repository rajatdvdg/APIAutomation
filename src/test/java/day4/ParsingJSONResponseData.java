package day4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;


public class ParsingJSONResponseData {

    @Test(priority = 1)
    void testJsonResponse(){

        //Approach 1
        /*given()
                .contentType(ContentType.JSON)

        .when()
                .get("http://localhost:3000/data")

        .then()
                .statusCode(200)
                .header("Content-Type", "application/json")
                .body("[0].id", equalTo("8"));

         */

        //Approach 2
        Response res = given()
                .contentType(ContentType.JSON)

                .when()
                .get("http://localhost:3000/data");

        assertEquals(res.getStatusCode(), 200);
        assertEquals(res.header("Content-Type"), "application/json");
        String first_name = res.jsonPath().get("[0].first_name").toString();
        Assert.assertEquals(first_name, "Lindsay");
    }

    @Test(priority = 2)
    void testJsonResponseBodyData(){

        Response res = given()
                .contentType(ContentType.JSON)

        .when()
                .get("http://localhost:3000/store");

        /*Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.header("Content-Type"), "application/json");
        String first_name = res.jsonPath().get("[0].first_name").toString();
        Assert.assertEquals(first_name, "Lindsay");*/

        //JSONObject Class

        JSONObject jo = new JSONObject(res.asString()); //Convert response to JSON Object Type
        /*for(int i=0; i<jo.getJSONArray("data").length(); i++){
            String first_name = jo.getJSONArray("data").getJSONObject(i).get("first_name").toString();
            System.out.println(first_name);
        }*/

        // Search for a first name in JSON
        boolean status = false;
        for(int i=0; i<jo.getJSONArray("data").length(); i++){
            String first_name = jo.getJSONArray("data").getJSONObject(i).get("first_name").toString();
            if(first_name.equals("George")){
                status = true;
                break;
            }
        }

        Assert.assertEquals(status, true);

        // Validate total price of books
        double total_price = 0;
        for(int i=0; i<jo.getJSONArray("data").length(); i++){
            String price = jo.getJSONArray("data").getJSONObject(i).get("price").toString();

            total_price = total_price + Double.parseDouble(price);
        }

       // System.out.println("Total Price: ");
        Assert.assertEquals(total_price, 100);
    }
}
