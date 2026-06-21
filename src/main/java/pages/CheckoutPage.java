package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    private final By FIRST_NAME_FIELD = By.id("first-name");
    private final By LAST_NAME_FIELD = By.id("last-name");
    private final By POSTAL_CODE_FIELD = By.id("postal-code");
    private final By CONTINUE_BUTTON = By.id("continue");
    private final By ERROR_MESSAGE = By.xpath("//h3[@data-test='error']");
    private final By FINISH_BUTTON = By.id("finish");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage enterFirstName(String firstName) {
        driver.findElement(FIRST_NAME_FIELD).sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        driver.findElement(LAST_NAME_FIELD).sendKeys(lastName);
        return this;
    }

    public CheckoutPage enterPostalCode(String postalCode) {
        driver.findElement(POSTAL_CODE_FIELD).sendKeys(postalCode);
        return this;
    }

    public CheckoutPage clickContinue() {
        driver.findElement(CONTINUE_BUTTON).click();
        return this;
    }
    public CheckoutPage fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
        clickContinue();
        return this;
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    public boolean isErrorMessageDisplayed() {
        return driver.findElements(ERROR_MESSAGE).size() > 0;
    }

    public CheckoutPage clickFinish() {
        driver.findElement(FINISH_BUTTON).click();
        return this;
    }

    public CheckoutPage isLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CONTINUE_BUTTON));
        return this;
    }
}