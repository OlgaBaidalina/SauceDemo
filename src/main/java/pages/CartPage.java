package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private final By PRODUCT_NAME = By.className("inventory_item_name");
    private final By PRODUCT_PRICE = By.className("inventory_item_price");
    private final By REMOVE_BUTTON = By.xpath("//button[text()='Remove']");
    private final By CHECKOUT_BUTTON = By.id("checkout");
    private final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return driver.findElement(PRODUCT_NAME).getText();
    }

    public String getProductPrice() {
        return driver.findElement(PRODUCT_PRICE).getText();
    }

    public void clickCheckout() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    public void removeProduct() {
        driver.findElement(REMOVE_BUTTON).click();
    }

    public void clickContinueShopping() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }
}