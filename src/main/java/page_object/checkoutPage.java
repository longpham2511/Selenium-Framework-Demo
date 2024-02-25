package page_object;

import Abstract_Folder.Abstract_Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class checkoutPage extends Abstract_Component {
    WebDriver driver;

    public checkoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public Boolean checkoutMessage(){
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Boolean match = confirmMessage.contains("THANKYOU FOR THE ORDER.");
        return match;
    }
}
