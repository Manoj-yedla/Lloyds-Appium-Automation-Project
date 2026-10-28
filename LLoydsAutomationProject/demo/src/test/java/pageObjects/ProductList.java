package pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import utilities.MobileActions;

public class ProductList {

    @AndroidFindBy(id="com.androidsample.generalstore:id/rvProductList")
    public MobileElement productList;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[2]")
    public MobileElement addToCart;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@resource-id=\"com.androidsample.generalstore:id/appbar_btn_cart\"]")
    public MobileElement cartButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/toolbar_title\"]")
    public MobileElement getcartTitle;


    public AppiumDriver<MobileElement> driver;
    public MobileActions utility;

    public ProductList(AppiumDriver<MobileElement> driver,MobileActions utility) {
        this.driver=driver;
        this.utility=utility;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickOnTheAddToCart()
    {
        addToCart.click();
    }

    public  void clickOnTheCartButton()
    {
        cartButton.click();
    }

    public void scrollToProduct(String text)
    {
        productList.click();
        utility.scrollToText(text);
    }

    public String getCartTitle()
    {
        String title = this.getcartTitle.getText();
        return title;
    }

}
