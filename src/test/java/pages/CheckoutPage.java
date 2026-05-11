package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    private final By FIRST_NAME_FIELD = By.id("first-name");
    private final By LAST_NAME_FIELD = By.id("last-name");
    private final By POSTAL_CODE_FIELD = By.id("postal-code");
    private final By CONTINUE_BUTTON = By.id("continue");
    private final By CANCEL_BUTTON = By.id("cancel");
    private final By ERROR_MESSAGE = By.xpath("//h3[@data-test='error']");
    private final By FINISH_BUTTON = By.id("finish");
    private final By COMPLETE_HEADER = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void enterFirstName(String firstName) {
        driver.findElement(FIRST_NAME_FIELD).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(LAST_NAME_FIELD).sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        driver.findElement(POSTAL_CODE_FIELD).sendKeys(postalCode);
    }

    public void clickContinue() {
        driver.findElement(CONTINUE_BUTTON).click();
    }

    public void fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
        clickContinue();
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }


    public boolean isErrorMessageDisplayed() {
        return driver.findElements(ERROR_MESSAGE).size() > 0;
    }

    public void clickFinish() {
        driver.findElement(FINISH_BUTTON).click();
    }
}