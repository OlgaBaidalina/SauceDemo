package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import java.util.List;

@Epic("Sauce Demo Testing")
@Feature("Товары")
@Story("Просмотр и сортировка товаров")
public class ProductsTest extends BaseTest{

    @Test (priority = 1,
            testName = "Проверка заголовка страницы Products")
    @Description("Проверка заголовка страницы с товарами")
    @Severity(SeverityLevel.NORMAL)
    public void checkPageTitle() {
        loginPage.open()
                .login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(), "Products");
    }

    @Test (priority = 2,
            testName = "Добавление товара в корзину")
    @Description("Проверка обновления счетчика корзины после добавления товара")
    @Severity(SeverityLevel.CRITICAL)
    public void checkAddProductToCart() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addProduct("Sauce Labs Fleece Jacket");
        assertEquals(productsPage.getQuantityCart(), 1);
    }

    @Test (priority = 3,
            testName = "Сортировка товаров Name(A to Z)")
    @Description("Проверка сортировки товаров по имени от A до Z")
    @Severity(SeverityLevel.NORMAL)
    public void checkSortByNameAZ() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .sortBy("az");
        List<String> names = productsPage.getAllProductNames();
        for (int i = 0; i < names.size() - 1; i++) {
            assertTrue(names.get(i).compareTo(names.get(i + 1)) <= 0);
        }
    }

    @Test (priority = 4,
            testName = "Сортировка товаров Name(Z to A)")
    @Description("Проверка сортировки товаров по имени от Z до A")
    @Severity(SeverityLevel.NORMAL)
    public void checkSortByNameZA() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .sortBy("za");
        List<String> names = productsPage.getAllProductNames();
        for (int i = 0; i < names.size() - 1; i++) {
            assertTrue(names.get(i).compareTo(names.get(i + 1)) >= 0);
        }
    }

    @Test (priority = 5,
            testName = "Сортировка товаров Price(low to high)")
    @Description("Проверка сортировки товаров по цене от меньшей к большей")
    @Severity(SeverityLevel.NORMAL)
    public void checkSortByPriceLowToHigh() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .sortBy("lohi");
        List<Double> prices = productsPage.getAllProductPrices();
        for (int i = 0; i < prices.size() - 1; i++) {
            assertTrue(prices.get(i) <= prices.get(i + 1));
        }
    }

    @Test (priority = 6,
            testName = "Сортировка товаров Price(high to low)")
    public void checkSortByPriceHighToLow() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .sortBy("hilo");
        List<Double> prices = productsPage.getAllProductPrices();
        for (int i = 0; i < prices.size() - 1; i++) {
            assertTrue(prices.get(i) >= prices.get(i + 1));
        }
    }

    @Test (priority = 7,
            testName = "Переход в корзину")
    @Description("Проверка перехода на страницу корзины по иконке корзины")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Baydalina Olga")
    public void checkGoToCartByIcon() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .openCart();
        assertTrue(driver.getCurrentUrl().contains("cart.html"));
    }
}