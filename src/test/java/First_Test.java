import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

@Test
public class First_Test {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup(); //download chromedriver
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        WebDriverWait exWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        Actions action = new Actions(driver);

        String id = "lp2511@gmail.com";
        String pass = "Password123.";
        String productName = "ADIDAS";
        String countryName = "United";

        driver.get("https://rahulshettyacademy.com/client/");
        driver.manage().window().maximize();
        driver.findElement(By.id("userEmail")).sendKeys(id);
        driver.findElement(By.id("userPassword")).sendKeys(pass);
        driver.findElement(By.id("login")).click();
        List<WebElement> shoppingProducts = driver.findElements(By.cssSelector(".card-body"));
        WebElement pickedprod = shoppingProducts.stream().filter(a -> a.findElement(By.cssSelector("b")).getText().contains(productName)).findFirst().orElse(null); //find the first item, if there's nothing, then return nothing
        pickedprod.findElement(By.className("w-10")).click();
        exWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("toast-container"))));
        driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
        List<WebElement> prodCart = driver.findElements(By.xpath("//div/div/h3"));
        Boolean matchItem = prodCart.stream().anyMatch(a -> a.getText().contains(productName));
        Assert.assertTrue(matchItem);
        driver.findElement(By.xpath("//button[text()='Checkout']")).click();
        action.click(driver.findElement(By.xpath("//input[contains(@placeholder,'Select Country')]")))
                .sendKeys(countryName).click().perform();
        action.click(exWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button/span[(text()=' United States')]")))).build().perform();
        //winthin a list of similar item from the parent class i.e. country list. use this to move within the child class //button[contains(@class,'ta-item')][4]
        driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equals("THANKYOU FOR THE ORDER."));
        driver.close();

    }
}
