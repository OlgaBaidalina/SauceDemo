package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    // Название добавленного товара совпадает в корзине
    @Test
    public void checkProductNameInCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct();
        productsPage.openCart();
        Assert.assertEquals(cartPage.getProductName(), "Sauce Labs Fleece Jacket");
    }

    // Цена добавленного товара совпадает в корзине
    @Test
    public void checkProductPriceInCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct();
        productsPage.openCart();
        Assert.assertEquals(cartPage.getProductPrice(), "$49.99");
    }

    // Переход на страницу оформления (Checkout)
    @Test
    public void checGoToCheckout() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct();
        productsPage.openCart();
        cartPage.clickCheckout();
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one.html"),
                "Должен быть переход на страницу оформления заказа");
    }

    //Удаление товара из корзины
    @Test
    public void checkRemoveProductFromCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct();
        productsPage.openCart();
        cartPage.removeProduct();
        boolean cartIsEmpty = driver.findElements(By.className("inventory_item_name")).size() == 0;
        Assert.assertTrue(cartIsEmpty, "Корзина должна быть пустой после удаления");
    }

    //Возврат к товарам на страницу с продуктами
    @Test
    public void checkContinueShopping() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct();
        productsPage.openCart();
        cartPage.clickContinueShopping();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),
                "Должен быть возврат на страницу с товарами");
    }
}