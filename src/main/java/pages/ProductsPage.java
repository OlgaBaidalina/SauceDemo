package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {

    private final By TITLE = By.cssSelector("[data-test=title]");
    private final By ADD_PRODUCT = By.cssSelector("[id$='fleece-jacket']");
    private final By CART_ICON = By.cssSelector("[class^='shopping']");
    private final By CART_BADGE = By.className("shopping_cart_badge");
    private final By SORT_DROPDOWN = By.className("product_sort_container");
    private final By ALL_PRODUCT_NAMES = By.className("inventory_item_name");
    private final By ALL_PRODUCT_PRICES = By.className("inventory_item_price");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public ProductsPage open() {
        driver.get(BASE_URL + "/inventory.html");
        return this;
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public ProductsPage addProduct() {
        driver.findElement(ADD_PRODUCT).click();
        return this;
    }

    public CartPage openCart() {
        driver.findElement(CART_ICON).click();
        return new CartPage(driver);
    }

    public int getQuantityCart() {
        if (driver.findElements(CART_BADGE).size() > 0) {
            return Integer.parseInt(driver.findElement(CART_BADGE).getText());
        }
        return 0;
    }

    public ProductsPage sortBy(String value) {
        Select select = new Select(driver.findElement(SORT_DROPDOWN));
        select.selectByValue(value);
        return this;
    }

    public List<String> getAllProductNames() {
        List<String> names = new ArrayList<>();
        for (WebElement element : driver.findElements(ALL_PRODUCT_NAMES)) {
            names.add(element.getText());
        }
        return names;
    }

    public List<Double> getAllProductPrices() {
        List<Double> prices = new ArrayList<>();
        for (WebElement element : driver.findElements(ALL_PRODUCT_PRICES)) {
            String priceText = element.getText().replace("$", "");
            prices.add(Double.parseDouble(priceText));
        }
        return prices;
    }

    public ProductsPage isLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }
}