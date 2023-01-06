package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddressPage extends BasePage {
    private final By firstNameLocator = By.id("first-name");
    private final By lastNameLocator = By.id("last-name");
    private final By zipOrPostalCodeLocator = By.id("postal-code");
    private final By continueButtonLocator = By.id("continue");

    public AddressPage(WebDriver driver) {
        super(driver);
    }

    public void fillInAddressInformation(String firstName, String lastName, String zipOrPostalCode) {
        sendKeys(firstNameLocator, firstName);
        sendKeys(lastNameLocator, lastName);
        sendKeys(zipOrPostalCodeLocator, zipOrPostalCode);
    }

    public void accessOverviewPage() {
        clickOnElement(continueButtonLocator);
    }
}
