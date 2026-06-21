package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {

    private final By TITLE = By.cssSelector("[data-test=title]");
    private final String ADD_PRODUCT_PATTERN = "//*[text()='%s']/ancestor::div[@class='inventory_item']//button[text()='Add to cart']";
    private final By CART_ICON = By.cssSelector("[class^='shopping']");
    private final By CART_BADGE = By.className("shopping_cart_badge");
    private final By SORT_DROPDOWN = By.className("product_sort_container");
    private final By ALL_PRODUCT_NAMES = By.className("inventory_item_name");
    private final By ALL_PRODUCT_PRICES = By.className("inventory_item_price");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public ProductsPage isLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }

    @Step("Открытие страницы товаров")
    public ProductsPage open() {
        driver.get(BASE_URL + "/inventory.html");
        return isLoad();
    }

    @Step("Получение заголовка страницы")
    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    @Step("Добавление товара с именем '{productName}' в корзину")
    public ProductsPage addProduct(String productName) {
            driver.findElement(By.xpath(String.format(ADD_PRODUCT_PATTERN, productName))).click();
            return this;
    }

    @Step("Открытие корзины")
    public CartPage openCart() {
        driver.findElement(CART_ICON).click();
        return new CartPage(driver);
    }

    @Step("Получение количества товаров в корзине")
    public int getQuantityCart() {
        if (driver.findElements(CART_BADGE).size() > 0) {
            return Integer.parseInt(driver.findElement(CART_BADGE).getText());
        }
        return 0;
    }

    @Step("Сортировка товаров по: '{value}'")
    public ProductsPage sortBy(String value) {
        Select select = new Select(driver.findElement(SORT_DROPDOWN));
        select.selectByValue(value);
        return this;
    }

    @Step("Получение списка названий всех товаров")
    public List<String> getAllProductNames() {
        List<String> names = new ArrayList<>();
        for (WebElement element : driver.findElements(ALL_PRODUCT_NAMES)) {
            names.add(element.getText());
        }
        return names;
    }

    @Step("Получение списка цен всех товаров")
    public List<Double> getAllProductPrices() {
        List<Double> prices = new ArrayList<>();
        for (WebElement element : driver.findElements(ALL_PRODUCT_PRICES)) {
            String priceText = element.getText().replace("$", "");
            prices.add(Double.parseDouble(priceText));
        }
        return prices;
    }
}