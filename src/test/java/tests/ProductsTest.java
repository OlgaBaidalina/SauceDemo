package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;
import java.util.List;

public class ProductsTest extends BaseTest{

    // Заголовок страницы с товарами
    @Test
    public void checkPageTitle() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(), "Products");
    }

    // Обновление счетчика корзины после добавления товара
    @Test
    public void checkAddProductToCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct();
        assertEquals(productsPage.getQuantityCart(), 1);
    }

    // Сортировка "Name (A to Z)"
    @Test
    public void checkSortByNameAZ() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.sortBy("az");
        List<String> names = productsPage.getAllProductNames();
        for (int i = 0; i < names.size() - 1; i++) {
            assertTrue(names.get(i).compareTo(names.get(i + 1)) <= 0);
        }
    }

    // Сортировка "Name (Z to A)"
    @Test
    public void checkSortByNameZA() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.sortBy("za");
        List<String> names = productsPage.getAllProductNames();
        for (int i = 0; i < names.size() - 1; i++) {
            assertTrue(names.get(i).compareTo(names.get(i + 1)) >= 0);
        }
    }

    // Сортировка "Price (low to high)"
    @Test
    public void checkSortByPriceLowToHigh() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.sortBy("lohi");
        List<Double> prices = productsPage.getAllProductPrices();
        for (int i = 0; i < prices.size() - 1; i++) {
            assertTrue(prices.get(i) <= prices.get(i + 1));
        }
    }

    // Сортировка "Price (high to low)"
    @Test
    public void checkSortByPriceHighToLow() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.sortBy("hilo");
        List<Double> prices = productsPage.getAllProductPrices();
        for (int i = 0; i < prices.size() - 1; i++) {
            assertTrue(prices.get(i) >= prices.get(i + 1));
        }
    }

    // Переход на страницу корзины по клику на иконку корзины
    @Test
    public void checkGoToCartByIcon() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.openCart();
        assertTrue(driver.getCurrentUrl().contains("cart.html"));
    }
}