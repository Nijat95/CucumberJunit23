package Api.test;

import Api.models.books.BookRecords;
import Api.models.books.CreateOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

public class BookApiTest  {

    @Test
    public void createJsonObject() throws JsonProcessingException {
        BookRecords records = new BookRecords();
        CreateOrder createOrder = new CreateOrder();
        records.setId("myRandomID");
        records.setBookId(1);
        records.setCustomerName("Nijat");
        records.setQuantity(10);

        ObjectMapper objectMapper = new ObjectMapper(); // --> This is from Jackson library
        String recordsJson = objectMapper.writeValueAsString(records);

        System.out.println(recordsJson);




    }

    @Test
    public void postAnOrder() throws JsonProcessingException {
        CreateOrder createOrder = new CreateOrder();
        createOrder.setBookId(1);
        createOrder.setCustomerName("Jim Smith");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(createOrder);
        System.out.println(requestBody);

        Response response = RestAssured.given()
                .header("Authorization", "Bearer 4ac4c43422b440abe7d8a93793af8d9667566de864092e08dc2cde357f2ae185")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("https://simple-books-api.glitch.me/orders");

        System.out.println(response.statusCode());
        System.out.println(response.asString());

    }

}
