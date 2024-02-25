package page_object;

import Abstract_Folder.Abstract_Component;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class landing_page extends Abstract_Component {

    static WebDriver driver;
    //page factory
    @FindBy(id = "userEmail")
    static
    WebElement signinEmail; //FindBy puts the values in the webelement variable here

    //WebElement inputEmail = driver.findElement(By.id( "userEmail"));
    @FindBy(id = "userPassword")
    static
    WebElement signinPass;
    @FindBy(id = "login")
    static
    WebElement signinButton;
    @FindBy(css = "#toast-container")
    static
    WebElement failedsigninmesg;

    public landing_page(WebDriver driver) {          //initialize this constructor - its scope doesn't affect the whole class, can create an object to use this method
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this); //initialize every element declared within this class
    }

    public static shopping_catalogue loginApplication(String idUsername, String password) {
        signinEmail.sendKeys(idUsername);
        signinPass.sendKeys(password);
        signinButton.click();
        return new shopping_catalogue(driver);
    }

    public static String failed_signin_msg(String idUsername, String password) {
        signinEmail.sendKeys(idUsername);
        signinPass.sendKeys(password);
        signinButton.click();
        waitWebElement(failedsigninmesg);
        return failedsigninmesg.getText();
    }

    public void toURL() {
        driver.get("https://rahulshettyacademy.com/client/");
    }
}
