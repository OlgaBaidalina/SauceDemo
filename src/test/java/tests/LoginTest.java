package tests;

import io.qameta.allure.*;
import jdk.jfr.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Epic("Sauce Demo Testing")
@Feature("Авторизация")
@Story("Логин пользователя")
public class LoginTest extends BaseTest {

    @Test (priority = 1,
            testName = "Успешный логин")
    @Description("Проверка успешного входа в систему с корректными данными")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Baydalina Olga")
    @TmsLink("TC-202")
    @Issue("BUG-111")
    public void checkLoginWithPositiveCred() {
        loginPage.open()
                .login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(), "Products");
    }

    @DataProvider(name = "Тестовые данные для негативного логина")
    public Object[][] loginData() {
        return  new Object[][] {
                {"standard_user", "", "Epic sadface: Password is required"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"Olga", "12345", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test (dataProvider = "Тестовые данные для негативного логина",
            priority = 2,
            testName = "Негативный логин")
    @Description ("Проверка отображения ошибок при вводе некорректных данных")
    @Severity(SeverityLevel.NORMAL)
    public void negativeLogin(String user, String password, String errorMessage) {
        loginPage.open()
                .login(user, password);
        assertEquals(loginPage.getErrorMessage(), errorMessage);
    }
}