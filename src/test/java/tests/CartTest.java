package tests;

import org.openqa.selenium.By;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test (priority = 1,
            description = "Проверка соответствия названия товара в корзине",
            testName = "Проверка названия товара в корзине")
    public void checkProductNameInCart() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addProduct()
                .openCart();
        assertEquals(cartPage.getProductName(), "Sauce Labs Fleece Jacket");
    }

    @Test (priority = 2,
            description = "Проверка соответствия цены товара в корзине",
            testName = "Проверка цены товара в корзине")
    public void checkProductPriceInCart() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addProduct()
                .openCart();
        assertEquals(cartPage.getProductPrice(), "$49.99");
    }

    @Test (priority = 3,
            description = "Проверка перехода на страницу оформления заказа",
            testName = "Переход на страницу Checkout")
    public void checGoToCheckout() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addProduct()
                .openCart()
                .clickCheckout();
        assertTrue(driver.getCurrentUrl().contains("checkout-step-one.html"),
                "Должен быть переход на страницу оформления заказа");
    }

    @Test (priority = 4,
            description = "Проверка удаления товара из корзины",
            testName = "Удаление товара из корзины")
    public void checkRemoveProductFromCart() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addProduct()
                .openCart()
                .removeProduct();
        boolean cartIsEmpty = driver.findElements(By.className("inventory_item_name")).size() == 0;
        assertTrue(cartIsEmpty, "Корзина должна быть пустой после удаления");
    }

    @Test (priority = 5,
            description = "Проверка возврата на страницу с товарами",
            testName = "Возврат на страницу товара")
    public void checkContinueShopping() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addProduct()
                .openCart()
                .clickContinueShopping();
        assertTrue(driver.getCurrentUrl().contains("inventory.html"),
                "Должен быть возврат на страницу с товарами");
    }
}