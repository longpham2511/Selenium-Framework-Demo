package Abstract_Folder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_object.cart_page;
import page_object.orders_page;

import java.time.Duration;

public class Abstract_Component {
    static WebDriver driver;

    public Abstract_Component(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void waitforWebElement(By findByWebElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findByWebElement));
    }
    public static void waitWebElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitforinvisibilityWebElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    @FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
    WebElement cartHeader;
    @FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
    WebElement ordersHeader;



    public cart_page gotoCart(){
        cartHeader.click();
        cart_page cartpage = new cart_page(driver);
        return cartpage;
    }

    public orders_page gotoOrders(){
        ordersHeader.click();
        return new orders_page(driver);
    }



}
