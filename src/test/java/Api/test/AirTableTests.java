package Api.test;

import Api.models.Myfields;
import Api.models.Record;
import Api.models.RequestBody;
import Api.models.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utils.Config;
import utils.Driver;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class AirTableTests {

    String myRecordID;
    @Test
    public void a_getRecords() throws JsonProcessingException {
        Response response = RestAssured.given()
                .header("Authorization", "Bearer keyomr0SHXuDYOQm1")
                .urlEncodingEnabled(false)
                .get(Config.getProperty("baseUrl"));
        System.out.println(response.statusCode());

        ObjectMapper objm = new ObjectMapper();
        ResponseBody rb = objm.readValue(response.asString(), ResponseBody.class);

        //System.out.println(rb.getRecords().get(8).getFields().getFirstName());

        int size = rb.getRecords().size();

//        for(int i = 0; i < size; i++){
//            if(rb.getRecords().get(i).getFields().getFirstName().startsWith("A")){
//                String firstName = rb.getRecords().get(i).getFields().getFirstName();
//                String lastName = rb.getRecords().get(i).getFields().getLastName();
//                String email = rb.getRecords().get(i).getFields().getEmail();
//                boolean studentYes = rb.getRecords().get(i).getFields().isStudent();
//                int age = rb.getRecords().get(i).getFields().getAge();
//                System.out.println(firstName + " " + lastName + " " + email + " " + studentYes + " " + age);
//            }
//        }

        for (Record elements : rb.getRecords()) {
            if (elements.getFields().getFirstName().charAt(0) == 'A') {
                System.out.println(elements.getFields().getFirstName()
                        + " " + elements.getFields().getLastName()
                        + " " + elements.getFields().getEmail()
                        + " " + elements.getFields().isStudent()
                        + " " + elements.getFields().getAge()

                );
            }
        }
    }

    @Test
    public void b_postRecord() throws JsonProcessingException {
//        {
//            "records": [
//            {
//                "fields": {
//                "firstName": "{{$randomFirstName}}",
//                        "lastName": "{{$randomLastName}}",
//                        "email": "{{$randomEmail}}",
//                        "student": {{$randomBoolean}},
//                "address": "{{$randomStreetAddress}}",
//                        "age": 25
//
//            }
//            }
//    ]
//        }
        Myfields newStudent = new Myfields();
        newStudent.setFirstName("Nijat");
        newStudent.setLastName("Ganiyev");
        newStudent.setEmail("Nijat@gmail.com");
        newStudent.setStudent(true);
        newStudent.setAddress("123 Address St");
        newStudent.setAge(27);

        Record record = new Record();
        record.setFields(newStudent);

        List<Record> records = new ArrayList<>();
        records.add(record);

        RequestBody requestBody = new RequestBody();
        requestBody.setRecords(records);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonValue = objectMapper.writeValueAsString(requestBody);
        System.out.println(jsonValue);


        Response response = RestAssured.given()
                .header("Authorization", "Bearer keyomr0SHXuDYOQm1")
                .urlEncodingEnabled(false)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(Config.getProperty("baseUrl"));

        System.out.println(response.statusCode());

        ResponseBody rb = objectMapper.readValue(response.asString(),ResponseBody.class);
        myRecordID = rb.getRecords().get(0).getId();
    }

    @Test
    public void c_patchRecord() throws JsonProcessingException {
        Myfields studentUpdate = new Myfields();
        studentUpdate.setFirstName("Mijar");
        studentUpdate.setLastName("Aniy");
        studentUpdate.setEmail("Mijar@gmail.com");
        studentUpdate.setStudent(true);
        studentUpdate.setAddress("123 Address St");
        studentUpdate.setAge(21);

        Record record = new Record();
        record.setFields(studentUpdate);
        record.setId(myRecordID);

        List<Record> records = new ArrayList<>();
        records.add(record);

        RequestBody requestBody = new RequestBody();
        requestBody.setRecords(records);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonValue = objectMapper.writeValueAsString(requestBody);

        Response response = RestAssured.given()
                .header("Authorization", "Bearer keyomr0SHXuDYOQm1")
                .urlEncodingEnabled(false)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .patch(Config.getProperty("baseUrl"));

        System.out.println(response.statusCode());
    }

    @Test
    public void d_deleteRecord(){

        String queryParam = "records[]";
        String recordID = myRecordID;

        Response response = RestAssured.given()
                .header("Authorization", "Bearer keyomr0SHXuDYOQm1")
                .urlEncodingEnabled(false)
                .queryParam(queryParam,recordID)
                .delete(Config.getProperty("baseUrl"));


        System.out.println(response.statusCode());


    }
}