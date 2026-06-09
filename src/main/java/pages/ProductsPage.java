package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public void open() {
        driver.get(BASE_URL + "/inventory.html");
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public void addProduct() {
        driver.findElement(ADD_PRODUCT).click();
    }

    public void openCart() {
        driver.findElement(CART_ICON).click();
    }

    public int getQuantityCart() {
        if (driver.findElements(CART_BADGE).size() > 0) {
            return Integer.parseInt(driver.findElement(CART_BADGE).getText());
        }
        return 0;
    }

    public void sortBy(String value) {
        Select select = new Select(driver.findElement(SORT_DROPDOWN));
        select.selectByValue(value);
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
}