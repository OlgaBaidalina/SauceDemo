package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CheckoutTest extends BaseTest{

    @DataProvider(name ="Данные для проверки ошибок Checkout")
    public Object[][] checkoutErrorsData() {
        return new Object[][]{
                {"", "Абрамова", "12345", "Error: First Name is required"},
                {"Елена", "", "12345", "Error: Last Name is required"},
                {"Елена", "Абрамова", "", "Error: Postal Code is required"}
        };
    }

    @Test(dataProvider = "Данные для проверки ошибок Checkout",
            priority = 1,
            description = "Проверка отображения ошибок при пустых полях оформления заказа",
            testName = "Проверка ошибок Checkout")
    public void checkCheckoutErrors(String firstName, String lastName, String postalCode, String expectedError) {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct();
        productsPage.openCart();
        cartPage.clickCheckout();
        checkoutPage.fillCheckoutInfo(firstName, lastName, postalCode);
        assertTrue(checkoutPage.isErrorMessageDisplayed());
        assertEquals(checkoutPage.getErrorMessage(), expectedError);
    }

    @Test(priority = 2,
            description = "Проверка перехода на страницу Overview после заполнения данных",
            testName = "Переход на страницу Overview")
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

    @Test(priority = 3,
            description = "Проверка успешного завершения оформления заказа",
            testName = "Успешное завершение заказа")
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