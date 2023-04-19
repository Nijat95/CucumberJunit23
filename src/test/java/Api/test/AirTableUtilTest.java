package Api.test;

import Api.models.Myfields;
import Api.models.Record;
import Api.models.RequestBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import org.junit.FixMethodOrder;
import org.junit.Test;
import utils.APIUtil;

import java.util.ArrayList;
import java.util.List;

public class AirTableUtilTest {
    public static String myRecord;

    @Test
    public void ZtestGetMethod() throws JsonProcessingException {
        String resource = "/Table%201";
        APIUtil.hitGet(resource);
    }

    @Test
    public void testCPostMethod(){
        String resource = "/Table%201";
        Faker faker = new Faker();

        String fName = faker.name().firstName();
        String lName = faker.name().lastName();
        String email = fName + lName + "@gmail.com";

        Myfields fields = new Myfields();
        fields.setFirstName("Elliot");
        fields.setLastName("Smith");
        fields.setAddress(faker.address().streetAddress());
        fields.setStudent(true);
        fields.setEmail("Elliot@gmail.com");
        fields.setAge(30);

        Record record = new Record();
        record.setFields(fields);

        List<Record> records = new ArrayList<>();
        records.add(record);

        RequestBody requestBody = new RequestBody();
        requestBody.setRecords(records);

        APIUtil.hitPost(resource,requestBody);
        myRecord = APIUtil.getResponseBody().getRecords().get(0).getId();

    }

    @Test
    public void testZPatchMethod(){
        String resource = "/Table%201";


        Myfields fields = new Myfields();
        fields.setFirstName("Elliot");
        fields.setLastName("Smith");
        fields.setAddress("123 Address St");
        fields.setStudent(true);
        fields.setEmail("Elliot@gmail.com");
        fields.setAge(30);

        Record record = new Record();
        record.setFields(fields);
        record.setId(myRecord);

        List<Record> records = new ArrayList<>();
        records.add(record);

        RequestBody requestBody = new RequestBody();
        requestBody.setRecords(records);

        APIUtil.hitPatch(resource,requestBody);


    }

    @Test
    public void testADelete(){
        String resource = "/Table%201";
        String recordToBeDeleted = myRecord;
        APIUtil.hitDelete(resource,recordToBeDeleted);
        System.out.println(APIUtil.getResponse().asString());
    }


}
