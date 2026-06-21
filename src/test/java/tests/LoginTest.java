package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test (priority = 1,
            description = "Проверка успешного входа в систему с корректными данными",
            testName = "Успешный логин")
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
            description = "Проверка отображения ошибок при вводе некорректных данных",
            testName = "Негативный логин")
    public void negativeLogin(String user, String password, String errorMessage) {
        loginPage.open()
                .login(user, password);
        assertEquals(loginPage.getErrorMessage(), errorMessage);
    }
}