package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage {
    private final By finishCheckoutButton = By.id("finish");
    private final By cartQuantityLocator = By.cssSelector("div[class='cart_quantity']");
    private final By itemNameLocator = By.cssSelector("div[class='inventory_item_name']");


    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public void finishCheckout() {
        clickOnElement(finishCheckoutButton);
    }

    public String getCartQuantity() {
        return getText(cartQuantityLocator);
    }

    public String getCartItemName() {
        return getText(itemNameLocator);
    }
}
