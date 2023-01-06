package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{
    private final By usernameField = By.name("user-name");
    private final By passwordField = By.name("password");
    private final By loginButton = By.name("login-button");

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void navigateToLoginPage() {
        this.driver.get("https://www.saucedemo.com/");
    }

    public void fillInCredentials(String username, String password) {
        sendKeys(usernameField, username);
        sendKeys(passwordField, password);
    }

    public void login() {
        clickOnElement(loginButton);
    }
}
