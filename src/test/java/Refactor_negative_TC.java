import org.testng.Assert;
import org.testng.annotations.Test;
import page_object.landing_page;
import page_object.shopping_catalogue;
import test_components.TestBase;
import test_components.retry_components;

public class Refactor_negative_TC extends TestBase {
    String id = "lp2511@gmail.com";
    String pass = "Password123.";


    @Test(retryAnalyzer = retry_components.class)
    public void tc1_failed_log_in() {
        Assert.assertEquals(landing_page.failed_signin_msg(id,pass+123), "Incorrect email or password.");
    }
}
