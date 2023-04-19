package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class WikiPage {

    public WikiPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//input[@id=\"searchInput\"]")
    public WebElement searchInput;

    @FindBy(xpath = "//button[@ type=\"submit\"]")
    public WebElement submitBtn;

    @FindBy(xpath = "//h1[@ id=\"firstHeading\"]")
    public WebElement getTextHeader;

    @FindBy(xpath = "//div[@class='fn']")
    public WebElement gettHeaderForImage;


}
