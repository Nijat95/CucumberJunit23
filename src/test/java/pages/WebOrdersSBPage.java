package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class WebOrdersSBPage {
    public WebOrdersSBPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy (css = ".login_info")
    public WebElement getWelcomeMsg;

    @FindBy (xpath = "//a[.='Order']")
    public WebElement getOrdersTab;

    @FindBy (xpath = "//select[@ id=\"ctl00_MainContent_fmwOrder_ddlProduct\"]")
    public WebElement getProductDropdown;
}
