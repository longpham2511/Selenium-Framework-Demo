package page_object;

import Abstract_Folder.Abstract_Component;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class orders_page extends Abstract_Component {
    WebDriver driver;
    public orders_page(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//tbody/tr/td[2]")
    List<WebElement> productName_column;
    @FindBy(xpath = "//tbody/tr/th")
    List<WebElement> orderID_column;

    public boolean check_productName(String productName){
        boolean check = productName_column.stream().anyMatch(a->a.getText().contains(productName));
        return check;
    }

}
