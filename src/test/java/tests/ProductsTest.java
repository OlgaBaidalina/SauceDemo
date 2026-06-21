package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;
import java.util.List;

public class ProductsTest extends BaseTest{

    @Test (priority = 1,
            description = "Проверка заголовка страницы с товарами",
            testName = "Проверка заголовка страницы Products")
    public void checkPageTitle() {
        loginPage.open()
                .login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(), "Products");
    }

    @Test (priority = 2,
            description = "Проверка обновления счетчика корзины после добавления товара",
            testName = "Добавление товара в корзину")
    public void checkAddProductToCart() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addProduct();
        assertEquals(productsPage.getQuantityCart(), 1);
    }

    @Test (priority = 3,
            description = "Проверка сортировки товаров по имени от A до Z",
            testName = "Сортировка товаров Name(A to Z)")
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
            description = "Проверка сортировки товаров по имени от Z до A",
            testName = "Сортировка товаров Name(Z to A)")
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
            description = "Проверка сортировки товаров по цене от меньшей к большей",
            testName = "Сортировка товаров Price(low to high)")
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
            description = "Проверка сортировки товаров по цене от большей к меньшей",
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

    @Test (priority = 1,
            description = "Проверка перехода на страницу корзины по иконке корзины",
            testName = "Переход в корзину")
    public void checkGoToCartByIcon() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .openCart();
        assertTrue(driver.getCurrentUrl().contains("cart.html"));
    }
}