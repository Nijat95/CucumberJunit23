package step_def;

import Api.models.Myfields;
import Api.models.Record;
import Api.models.RequestBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.AirTableLoginPage;
import utils.APIUtil;
import utils.Config;
import utils.Driver;

import java.util.ArrayList;
import java.util.List;

public class AirTableSteps {
    Faker faker = new Faker();
    String resource = "/Table%201";

    String recordID;
    @When("user hit an GET endpoint")
    public void user_hit_an_get_endpoint() throws JsonProcessingException {
        APIUtil.hitGet(resource);
    }
    @Then("user will receive status {string}")
    public void user_will_receive_status(String statusCode) {
        //String strParse = Integer.toString(APIUtil.getResponse().statusCode());
        String str = String.valueOf(APIUtil.getResponse().statusCode());
        Assert.assertEquals(statusCode, str);
    }

    @When("user hit Retrieve Record")
    public void user_hit_retrieve_record() throws JsonProcessingException {
        APIUtil.hitGet(resource + "/recq85oNlzzYn100Q");
    }


    @And("user verify the first name")
    public void userVerifyTheFirstName() {
        //Henry

        String actualName = APIUtil.getResponseBody()
                .getFields()
                .getFirstName();
        Assert.assertEquals(actualName,"Henry");
    }

    @When("a user creates a record")
    public void aUserCreatesARecord() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String fakeEmail = faker.internet().emailAddress();
        int index = fakeEmail.indexOf("@");
        String emailProvider = fakeEmail.substring(index);

        Myfields fields = new Myfields();
        fields.setFirstName(firstName);
        fields.setLastName(lastName);
        fields.setAge(faker.number().numberBetween(16,30));
        fields.setEmail(firstName + "."+lastName+emailProvider);
        fields.setStudent(faker.random().nextBoolean());
        fields.setAddress(faker.address().streetAddress());

        Record record = new Record();
        record.setFields(fields);

        List<Record> records = new ArrayList<>();
        records.add(record);

        RequestBody requestBody = new RequestBody();
        requestBody.setRecords(records);

        APIUtil.hitPost(resource,requestBody);
    }

    @When("a user is saving the recordID")
    public void aUserIsSavingTheRecordID() {
        recordID = APIUtil.getResponseBody().getRecords().get(0).getId();
    }

    @And("a user updates email")
    public void aUserUpdatesEmail() {
        Myfields fields = new Myfields();
        fields.setEmail(faker.internet().emailAddress());
        Record record = new Record();
        record.setFields(fields);
        record.setId(recordID);

        List<Record> records = new ArrayList<>();
        records.add(record);

        RequestBody requestBody = new RequestBody();
        requestBody.setRecords(records);

        APIUtil.hitPatch(resource,requestBody);
    }

    @When("user deletes the recordID")
    public void userDeletesTheRecordID() {
        APIUtil.hitDelete(resource,recordID);
    }

    @When("a user tries to create a record with invalid payload")
    public void aUserTriesToCreateARecordWithInvalidPayload() {
        RequestBody requestBody = new RequestBody();
        APIUtil.hitPost(resource,requestBody);
    }


    @Then("user will receive status code : {int}")
    public void userWillReceiveStatusCode(int statusCode) {
        Assert.assertEquals(statusCode,APIUtil.getResponse().statusCode());
    }

    @Given("user logs in the airtable account")
    public void user_logs_in_the_airtable_account() throws InterruptedException {
        AirTableLoginPage airTableLoginPage = new AirTableLoginPage();
        Driver.getDriver().get(Config.getProperty("loginPageAirtableUrl"));
        Thread.sleep(15000);
        airTableLoginPage.emailField.sendKeys(Config.getProperty("loginAirtableTable"));
        airTableLoginPage.passwordField.sendKeys(Config.getProperty("passwordAirtable"));
        airTableLoginPage.submitButton.click();

    }
    @When("user selects a base")
    public void user_selects_a_base() {
        AirTableBasePage airTableBasePage = new AirTableBasePage();
        airTableBasePage.baseAvatar.click();
    }
    @When("adds a new record")
    public void adds_a_new_record() {
        AirTableBasePage airTableBasePage = new AirTableBasePage();
        airTableBasePage.cellZeroRowZeroCoordinates.clear();
        airTableBasePage.cellZeroRowZeroCoordinates.sendKeys("Ninja");
    }
    @Then("will verify the record was saved, using api call")
    public void will_verify_the_record_was_saved_using_api_call() throws JsonProcessingException {
        APIUtil.hitGet(resource);
        String actualName = APIUtil.getResponseBody()
                .getRecords()
                .get(0)
                .getFields()
                .getFirstName();
        Assert.assertEquals(actualName,"Ninja");
    }


}
