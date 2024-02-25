import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page_object.*;
import test_components.TestBase;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Refactor_TC extends TestBase {
    String id = "lp2511@gmail.com";
    String pass = "Password123.";
    String productName = "ADIDAS";
    String countryName = "United";

    @Test(dataProvider = "getData")
    public void tc1_submit_order(String id, String pass, String productName) {
        shopping_catalogue shoppingList = landing_page.loginApplication(id, pass);
        shoppingList.addToCart(productName);
        cart_page place_order = shoppingList.gotoCart(); //parent method can be accessed thru child class
        Boolean match = place_order.verifyCartedItems(productName);
        Assert.assertTrue(match);
        place_order.checkout();
        place_order.inputCountry(countryName);
        checkoutPage confirm_checkout = place_order.placeOrder();
        Boolean confirm_msg = confirm_checkout.checkoutMessage();
        Assert.assertTrue(confirm_msg);
    }

    @Test(dataProvider = "jsonDATA", groups = {"alternative"},enabled = false)
    public void tc1_submit_order_alter(HashMap<String, String> input) {
        shopping_catalogue shoppingList = landing_page.loginApplication(input.get("email"), input.get("password"));
        shoppingList.addToCart(input.get("productName"));
        cart_page place_order = shoppingList.gotoCart(); //parent method can be accessed thru child class
        Boolean match = place_order.verifyCartedItems(input.get("productName"));
        Assert.assertTrue(match);
        place_order.checkout();
        place_order.inputCountry(countryName);
        checkoutPage confirm_checkout = place_order.placeOrder();
        Boolean confirm_msg = confirm_checkout.checkoutMessage();
        Assert.assertTrue(confirm_msg);
    }

    @Test(dependsOnMethods = {"tc1_submit_order"})
    public void tc2_check_orderHistory() {
        shopping_catalogue order = landing_page.loginApplication(id, pass);
//        shopping_catalogue order = new shopping_catalogue(driver);
        orders_page orderspage = order.gotoOrders();
        Assert.assertTrue(orderspage.check_productName(productName));

    }

    @DataProvider
    public Object[][] getData() {
        return new Object[][]{{"lp2511@gmail.com", "Password123.", "ADIDAS"}, {"lp2511@gmail.com", "Password123.", "ZARA"}};
    }

    @DataProvider
    public Object[][] hashData() {
        HashMap<Object, Object> map = new HashMap<Object, Object>();
        map.put("email", id);
        map.put("password", pass);
        map.put("productName", productName);

        HashMap<Object, Object> map1 = new HashMap<Object, Object>();
        map1.put("email", id);
        map1.put("password", pass);
        map1.put("productName", "ZARA");


        return new Object[][]{{map}, {map1}};
    }

    @DataProvider
    public Object[][] jsonDATA() throws IOException {
        List<HashMap<String,String>> data = getJSONdatatoMap(System.getProperty("user.dir") + "//src//test//java//data//Purchase_Order.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }



}
