import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;

public class LocatorTest {

    @Test
    public void checkLocator() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("login_button_container"));
        driver.findElement(By.name("password"));
        driver.findElement(By.className("login-box"));
        driver.findElement(By.tagName("body"));
        driver.findElement(By.cssSelector("#user-name"));
        driver.findElement(By.cssSelector("[id|='user']"));

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        //driver.get("https://www.saucedemo.com/inventory.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.linkText("Twitter"));
        driver.findElement(By.partialLinkText("Face"));
        driver.findElement(By.xpath("//div[@class='app_logo']"));
        driver.findElement(By.xpath("//a[@id='item_1_title_link']"));
        driver.findElement(By.xpath("//div[text()='Sauce Labs Bike Light']"));
        driver.findElement(By.xpath("//span[contains(@class,'select')]"));
        driver.findElement(By.xpath("//div[contains(text(),'Backpack')]"));
        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']//ancestor::div[@class='inventory_item']"));
        driver.findElement(By.xpath("//div[@class='header_secondary_container']//descendant::span[@class='active_option']"));
        driver.findElement(By.xpath("//div[@class='header_secondary_container']//following::option[@value='za']"));
        driver.findElement(By.xpath("//a[@id='item_2_title_link']/parent::div[@class='inventory_item_label']"));
        driver.findElement(By.xpath("//div[text()='Sauce Labs Bike Light']/preceding::div[1]"));
        driver.findElement(By.xpath("//div[@class='inventory_item_price' and text()='49.99']"));
        driver.findElement(By.cssSelector(".btn_inventory"));
        driver.findElement(By.cssSelector(".btn.btn_inventory"));
        driver.findElement(By.cssSelector(".social .social_twitter"));
        driver.findElement(By.cssSelector("button"));
        driver.findElement(By.cssSelector("button.btn_inventory"));
        driver.findElement(By.cssSelector("[id='item_0_title_link']"));
        driver.findElement(By.cssSelector("[class~='btn']"));
        driver.findElement(By.cssSelector("[class^='bm']"));
        driver.findElement(By.cssSelector("[class$='book']"));
        driver.findElement(By.cssSelector("[href*='twitter']"));

        driver.quit();
    }
}