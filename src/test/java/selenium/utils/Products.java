package selenium.utils;

import org.openqa.selenium.By;

public enum Products {
    BACKPACK(By.id("add-to-cart-sauce-labs-backpack"), By.cssSelector(composeSelector(1)), 29.99, "Sauce Labs Backpack"),
    BIKE_LIGHT(By.id("add-to-cart-sauce-labs-bike-light"), By.cssSelector(composeSelector(2)), 9.99, "Sauce Labs Bike Light"),
    T_SHIRT(By.id("add-to-cart-sauce-labs-bolt-t-shirt"), By.cssSelector(composeSelector(3)), 15.99, "Sauce Labs Bolt T-Shirt"),
    FLEECE_JACKET(By.id("add-to-cart-sauce-labs-fleece-jacket"), By.cssSelector(composeSelector(4)), 49.99, "Sauce Labs Fleece Jacket"),
    ONESIE(By.id("add-to-cart-sauce-labs-onesie"), By.cssSelector(composeSelector(5)), 7.99, "Sauce Labs Onesie"),
    T_SHIT_ALL_THE_THINGS(By.id("add-to-cart-test.allthethings()-t-shirt-(red)"), By.cssSelector(composeSelector(6)), 15.99, "Test.allTheThings() T-Shirt (Red)");

    private final By addToCartLocator;
    private final By priceLocator;
    private final double price;
    private final String itemName;

    Products(By addToCartLocator, By priceLocator, double price, String itemName) {
        this.addToCartLocator = addToCartLocator;
        this.priceLocator = priceLocator;
        this.price = price;
        this.itemName = itemName;
    }

    private static String composeSelector(int itemNumber) {
        return "#inventory_container > div > div:nth-child(" + itemNumber + ") > div.inventory_item_description > div.pricebar > div";
    }

    public By getAddToCartLocator() {
        return addToCartLocator;
    }

    public By getPriceLocator() {
        return priceLocator;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceAsText() {
        return "$" + Double.toString(price);
    }

    public String getItemName() {
        return itemName;
    }
}
