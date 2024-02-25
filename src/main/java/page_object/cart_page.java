package page_object;

import Abstract_Folder.Abstract_Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class cart_page extends Abstract_Component {

    WebDriver driver;
    @FindBy(xpath = "//a[text()='Place Order ']")
    WebElement placeorderButton;
    @FindBy(xpath = "//div/div/h3")
    List<WebElement> inCartProducts;
    @FindBy(xpath = "//input[contains(@placeholder,'Select Country')]")
    WebElement countryField;

    @FindBy(xpath = "//button[text()='Checkout']")
    WebElement checkoutButton;
    By pickedCountry = By.xpath("//button/span[(text()=' United States')]");

    //page factory
    public cart_page(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkout(){
        checkoutButton.click();
    }

    public boolean verifyCartedItems(String productName){
        Boolean matchItem = inCartProducts.stream().anyMatch(a -> a.getText().contains(productName));
        return matchItem;
    }

    public void inputCountry(String countryName) {
        Actions action = new Actions(driver);
        action.sendKeys(countryField, countryName).click().perform();
        action.click(driver.findElement(pickedCountry)).build().perform();
    }

    public checkoutPage placeOrder(){
        placeorderButton.click();
        return new checkoutPage(driver);
    }
}
