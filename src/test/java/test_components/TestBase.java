package test_components;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page_object.landing_page;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class TestBase {
    public WebDriver driver; //global variable


    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fileInput = new FileInputStream(System.getProperty("user.dir") + "//src//main//Test_resources//globaldata.properties"); //set user.dir so that local vairables from different machine can find this file.
        prop.load(fileInput);
        String browsername = System.getProperty("browser") !=null ? System.getProperty("browser"):prop.getProperty("browser");
        //                  find if the browser is null or not     go and get value from mvn, if this is null get the globaldata properpties

        if (browsername.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup(); //download chromedriver
            driver = new ChromeDriver();
        } else if (browsername.equalsIgnoreCase("firefox")) {
            //initiate browser
            System.setProperty("geckodriver.exe", "//Users//longp//Downloads//geckodriver-v0.34.0-win64");
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //after initialize the browser, its properties can be set here
        driver.manage().window().maximize();
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public landing_page landing_application() throws IOException {
        WebDriver driver = initializeDriver();
        landing_page landingpage = new landing_page(driver);
        landingpage.toURL();
        return landingpage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }


    public List<HashMap<String, String>> getJSONdatatoMap(String s) throws IOException {
        //read json to string
        String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//java//data//Purchase_Order.json"), StandardCharsets.UTF_8);
        //convert String to HashMap -> Jackson Databind
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;

    }

    public String getScreenshot(String tcName, WebDriver driver) throws IOException {
        TakesScreenshot takescreenshot = (TakesScreenshot) driver;
        File source = takescreenshot.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//report//" + tcName + ".png"); //put file location here
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") + "//report//" + tcName + ".png";
    } //screenshot function - this function will be used in the extent report
}
