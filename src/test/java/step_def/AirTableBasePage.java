package step_def;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class AirTableBasePage {
    public AirTableBasePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//div[.='Executors']/preceding-sibling::*")
    public WebElement baseAvatar;

    @FindBy(xpath = "//div[@data-testid=\"gridCell-0:0\"]")
    public WebElement cellZeroRowZeroCoordinates;
}
