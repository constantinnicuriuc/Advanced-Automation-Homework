package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderConfirmationPage extends BasePage {
    private final By confirmationText = By.cssSelector(".complete-text");

    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public String getConfirmationText() {
        return getText(confirmationText);
    }
}
