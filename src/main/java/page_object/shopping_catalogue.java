package page_object;

import Abstract_Folder.Abstract_Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.util.List;

public class shopping_catalogue extends Abstract_Component {

    WebDriver driver;


    //page factory
    public shopping_catalogue(WebDriver driver) {          //initialize this constructor - its scope doesn't affect the whole class, can create an object to use this method
        super(driver); //element from child to parent. every child needs to have it for parent class to be able to work
        this.driver = driver;
        PageFactory.initElements(driver, this); //initialize every element declared within this class
    }
    //        List<WebElement> shoppingProducts = driver.findElements(By.cssSelector(".card-body"));
    @FindBy(css = ".card-body")
    List<WebElement> shoppingCatalogue;

    By productList = By.cssSelector(".card-body");
    By addedConfirmBox = By.id("toast-container");
    @FindBy(id ="toast-container")
    WebElement confirmbox;



    public List<WebElement> getProductList(){
        waitforWebElement(productList);
        return shoppingCatalogue;
    }

    public WebElement getProductByName(String productName){
        WebElement product = shoppingCatalogue.stream().filter(a -> a.findElement(By.cssSelector("b")).getText().contains(productName)).findFirst().orElse(null); //find the first item, if there's nothing, then return nothing
        return product;
    }

    public void addToCart(String productName){
        WebElement product = getProductByName(productName);
        product.findElement(By.className("w-10")).click();
        waitforWebElement(addedConfirmBox);
        waitforinvisibilityWebElement(confirmbox);
        //applying page factoring within .findElement is impossible

    }

}
