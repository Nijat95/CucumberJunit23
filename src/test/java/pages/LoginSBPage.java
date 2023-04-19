package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class LoginSBPage {
    public LoginSBPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(css = "#ctl00_MainContent_username")
    public WebElement usernameInput;

    @FindBy(css = "#ctl00_MainContent_password")
    public WebElement passwordInput;

    @FindBy(css = "#ctl00_MainContent_login_button")
    public WebElement loginBtn;

    @FindBy (css = "#ctl00_MainContent_status")
    public WebElement getErrorMessage;


}
