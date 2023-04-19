package step_def;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import pages.GooglePage;
import utils.Driver;

public class GoogleSteps {
    GooglePage google = new GooglePage();
    @Given("I am at google home page")
    public void i_am_at_google_home_page() {
        Driver.getDriver().get("https://www.google.com");
    }
    @Then("I verify the title is Google")
    public void i_verify_the_title_is_google() {
        String expectedTitle = "Google";
        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertEquals("my message",actualTitle, expectedTitle);
    }

    @And("I verify the search")
    public void iVerifyTheSearch() {
       // google.searchBar.sendKeys("saucelabs", Keys.ENTER);
    }

    @When("I search for saucelabs")
    public void iSearchForSaucelabs() {
        google.searchBar.sendKeys("saucelabs", Keys.ENTER);
    }

    @Then("I verify the title is Saucelabs")
    public void iVerifyTheTitleIsSaucelabs() {
        String expectedTitle = "saucelabs";
        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitle));
    }
}
