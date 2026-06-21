package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    private final By PRODUCT_NAME = By.className("inventory_item_name");
    private final By PRODUCT_PRICE = By.className("inventory_item_price");
    private final By REMOVE_BUTTON = By.xpath("//button[text()='Remove']");
    private final By CHECKOUT_BUTTON = By.id("checkout");
    private final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage isLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_BUTTON));
        return this;
    }

    @Step("Получить название товара в корзине")
    public String getProductName() {
        return driver.findElement(PRODUCT_NAME).getText();
    }

    @Step("Получить цену товара в корзине")
    public String getProductPrice() {
        return driver.findElement(PRODUCT_PRICE).getText();
    }

    @Step("Нажать кнопку Checkout")
    public CheckoutPage clickCheckout() {
        driver.findElement(CHECKOUT_BUTTON).click();
        return new CheckoutPage(driver);
    }

    @Step("Удалить товар из корзины")
    public CartPage removeProduct() {
        driver.findElement(REMOVE_BUTTON).click();
        return this;
    }

    @Step("Нажать кнопку Continue Shopping")
    public ProductsPage clickContinueShopping() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
        return new ProductsPage(driver);
    }
}