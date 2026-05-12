package tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CheckoutTest extends BaseTest{

    // Ошибки при пустом поле First Name
    @Test
    public void checkErrorWhenFirstNameIsEmpty() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct();
        productsPage.openCart();
        cartPage.clickCheckout();
        checkoutPage.fillCheckoutInfo("", "Абрамова", "12345");
        assertTrue(checkoutPage.isErrorMessageDisplayed());
        assertEquals(checkoutPage.getErrorMessage(), "Error: First Name is required");
    }

    // Ошибка при пустом поле Last Name
    @Test
    public void checkErrorWhenLastNameIsEmpty() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct();
        productsPage.openCart();
        cartPage.clickCheckout();
        checkoutPage.fillCheckoutInfo("Елена", "", "12345");
        assertTrue(checkoutPage.isErrorMessageDisplayed());
        assertEquals(checkoutPage.getErrorMessage(), "Error: Last Name is required");
    }

    // Ошибка при пустом поле Postal Code
    @Test
    public void checkErrorWhenPostalCodeIsEmpty() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct();
        productsPage.openCart();
        cartPage.clickCheckout();
        checkoutPage.fillCheckoutInfo("Елена", "Абрамова", "");
        assertTrue(checkoutPage.isErrorMessageDisplayed());
        assertEquals(checkoutPage.getErrorMessage(), "Error: Postal Code is required");
    }

    // Переход на Overview после заполнения полей
    @Test
    public void checkGoToOverviewStep() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct();
        productsPage.openCart();
        cartPage.clickCheckout();
        checkoutPage.fillCheckoutInfo("Елена", "Абрамова", "12345");
        assertTrue(driver.getCurrentUrl().contains("checkout-step-two.html"),
                "Должен быть переход на страницу Overview");
    }

    // Завершение заказа по кнопке Finish
    @Test
    public void checkFinishOrder() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct();
        productsPage.openCart();
        cartPage.clickCheckout();
        checkoutPage.fillCheckoutInfo("Елена", "Абрамова", "12345");
        checkoutPage.clickFinish();
        assertTrue(driver.getCurrentUrl().contains("checkout-complete.html"),
                "Должен быть переход на страницу об успешном завершении оформления заказа");
    }
}