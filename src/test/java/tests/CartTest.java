package tests;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

@Epic("Sauce Demo Testing")
@Feature("Корзина")
@Story("Управление товарами в корзине")
public class CartTest extends BaseTest {

    @Test (priority = 1,
            testName = "Проверка названия товара в корзине")
    @Description("Проверка соответствия названия товара в корзине")
    @Severity(SeverityLevel.NORMAL)
    public void checkProductNameInCart() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addProduct("Sauce Labs Fleece Jacket")
                .openCart();
        assertEquals(cartPage.getProductName(), "Sauce Labs Fleece Jacket");
    }

    @Test (priority = 2,
            testName = "Проверка цены товара в корзине")
    @Description("Проверка соответствия цены товара в корзине")
    @Severity(SeverityLevel.NORMAL)
    public void checkProductPriceInCart() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addProduct("Sauce Labs Fleece Jacket")
                .openCart();
        assertEquals(cartPage.getProductPrice(), "$49.99");
    }

    @Test (priority = 3,
            testName = "Переход на страницу Checkout")
    @Description("Проверка перехода на страницу оформления заказа")
    @Severity(SeverityLevel.CRITICAL)
    public void checGoToCheckout() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addProduct("Sauce Labs Fleece Jacket")
                .openCart()
                .clickCheckout();
        assertTrue(driver.getCurrentUrl().contains("checkout-step-one.html"),
                "Должен быть переход на страницу оформления заказа");
    }

    @Test (priority = 4,
            testName = "Удаление товара из корзины")
    @Description("Проверка удаления товара из корзины")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Baydalina Olga")
    public void checkRemoveProductFromCart() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addProduct("Sauce Labs Fleece Jacket")
                .openCart()
                .removeProduct();
        boolean cartIsEmpty = driver.findElements(By.className("inventory_item_name")).size() == 0;
        assertTrue(cartIsEmpty, "Корзина должна быть пустой после удаления");
    }

    @Test (priority = 5,
            description = "Проверка возврата на страницу с товарами",
            testName = "Возврат на страницу товара")
    @Description("Проверка возврата на страницу с товарами")
    @Severity(SeverityLevel.NORMAL)
    public void checkContinueShopping() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addProduct("Sauce Labs Fleece Jacket")
                .openCart()
                .clickContinueShopping();
        assertTrue(driver.getCurrentUrl().contains("inventory.html"),
                "Должен быть возврат на страницу с товарами");
    }
}