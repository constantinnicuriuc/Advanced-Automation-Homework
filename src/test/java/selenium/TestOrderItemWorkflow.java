package selenium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import selenium.pages.AddressPage;
import selenium.pages.CheckoutOverviewPage;
import selenium.pages.LoginPage;
import selenium.pages.OrderConfirmationPage;
import selenium.pages.ProductPage;
import selenium.pages.ShoppingCartPage;
import selenium.utils.AddressInformation;
import selenium.utils.Credentials;
import selenium.utils.Products;

public class TestOrderItemWorkflow extends BaseTest {

    @Test
    public void orderItem_completeWorkflow() {
        // Test setup
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        AddressPage addressPage = new AddressPage(driver);
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);

        //Navigate to application
        loginPage.navigateToLoginPage();

        //Log in
        loginPage.fillInCredentials(Credentials.STANDARD_USER.getUsername(), Credentials.STANDARD_USER.getPassword());
        loginPage.login();

        //Check item price then add item to cart
        Assertions.assertTrue(productPage.checkProductsPageIsDisplayed());
        Assertions.assertEquals(Products.BACKPACK.getPriceAsText(), productPage.getPrice(Products.BACKPACK.getPriceLocator()));
        productPage.addItemToCart(Products.BACKPACK.getAddToCartLocator());
        productPage.accessShoppingCart();

        //Checkout with the selected item
        Assertions.assertEquals("1", checkoutOverviewPage.getCartQuantity());
        Assertions.assertEquals(Products.BACKPACK.getItemName(), checkoutOverviewPage.getCartItemName());
        shoppingCartPage.checkout();

        //Fill in address information
        addressPage.fillInAddressInformation(AddressInformation.ADDRESS_1.getFirstName(), AddressInformation.ADDRESS_1.getLastName(), AddressInformation.ADDRESS_1.getZipOrPostalCode());
        addressPage.accessOverviewPage();

        //Check the overview page and finish the checkout process
        checkoutOverviewPage.finishCheckout();

        //Validate the order confirmation page
        Assertions.assertEquals("Your order has been dispatched, and will arrive just as fast as the pony can get there!", orderConfirmationPage.getConfirmationText());
    }
}
