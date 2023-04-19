package Api.test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class MyFirstAPITest {
    //1. Hitting an endpoint or hit the api
    //2. What type of request it will be (get,post,patch,delete),
    // auth, headers, url(endpoint), params, body (request/response)
    @Test
    public void getListOfTheBooks(){
       Response response = RestAssured.get("https://simple-books-api.glitch.me/books");
        //System.out.println(response.asString());
        //System.out.println(response.statusCode());
        Assert.assertEquals(200, response.statusCode());

        JsonPath jsonPath = response.jsonPath();
        //String str = jsonPath.get("[1].name").toString();
        //System.out.println(str);
        int size = jsonPath.getList("").size();

       for(int i = 0; i < size; i++) {
           String names = jsonPath.get("["+i+"].name");
           System.out.println(names);
       }
    }
    //authorization example
    @Test
    public void  getAllOrders(){
        Response response = RestAssured.given()
                        .header("Authorization","Bearer 4ac4c43422b440abe7d8a93793af8d9667566de864092e08dc2cde357f2ae185")
                .get("https://simple-books-api.glitch.me/orders");
                System.out.println(response.statusCode());

    }

    @Test
    public void airtableAuth(){
        Response response = RestAssured.given()
                .header("Authorization","Bearer keyomr0SHXuDYOQm1")
                .urlEncodingEnabled(false)
                .get("https://api.airtable.com/v0/appUeY5G12Tsf6qv6/Table%201");

        System.out.println(response.statusCode());
    }

}
