package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {
    private final By shoppingCartIcon = By.cssSelector(".shopping_cart_link");
    private final By productsIcon = By.cssSelector("span[class='title']");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void addItemToCart(By locator) {
        clickOnElement(locator);
    }

    public void accessShoppingCart() {
        clickOnElement(shoppingCartIcon);
    }

    public String getPrice(By locator) {
        return getText(locator);
    }

    public Boolean checkProductsPageIsDisplayed() {
        return checkIconIsDisplayed(productsIcon);
    }
}
