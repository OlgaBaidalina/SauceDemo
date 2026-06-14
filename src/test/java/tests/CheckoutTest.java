package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Epic("Sauce Demo Testing")
@Feature("Оформление заказа")
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
            testName = "Проверка ошибок Checkout")
    @Description("Проверка отображения ошибок при пустых полях оформления заказа")
    @Severity(SeverityLevel.NORMAL)
    public void checkCheckoutErrors(String firstName, String lastName, String postalCode, String expectedError) {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Fleece Jacket");
        productsPage.openCart();
        cartPage.clickCheckout();
        checkoutPage.fillCheckoutInfo(firstName, lastName, postalCode);
        assertTrue(checkoutPage.isErrorMessageDisplayed());
        assertEquals(checkoutPage.getErrorMessage(), expectedError);
    }

    @Test(priority = 2,
            testName = "Переход на страницу Overview")
    @Description("Проверка перехода на страницу Overview после заполнения данных")
    @Severity(SeverityLevel.CRITICAL)
    public void checkGoToOverviewStep() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Fleece Jacket");
        productsPage.openCart();
        cartPage.clickCheckout();
        checkoutPage.fillCheckoutInfo("Елена", "Абрамова", "12345");
        assertTrue(driver.getCurrentUrl().contains("checkout-step-two.html"),
                "Должен быть переход на страницу Overview");
    }

    @Test(priority = 3,
            testName = "Успешное завершение заказа")
    @Description("Проверка успешного завершения оформления заказа")
    @Severity(SeverityLevel.BLOCKER)
    public void checkFinishOrder() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Fleece Jacket");
        productsPage.openCart();
        cartPage.clickCheckout();
        checkoutPage.fillCheckoutInfo("Елена", "Абрамова", "12345");
        checkoutPage.clickFinish();
        assertTrue(driver.getCurrentUrl().contains("checkout-complete.html"),
                "Должен быть переход на страницу об успешном завершении оформления заказа");
    }
}