package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    @Step("Ввести имя: '{firstName}'")
    public void enterFirstName(String firstName) {
        driver.findElement(FIRST_NAME_FIELD).sendKeys(firstName);
    }

    @Step("Ввести фамилию: '{lastName}'")
    public void enterLastName(String lastName) {
        driver.findElement(LAST_NAME_FIELD).sendKeys(lastName);
    }

    @Step("Ввод почтового индекса: '{postalCode}'")
    public void enterPostalCode(String postalCode) {
        driver.findElement(POSTAL_CODE_FIELD).sendKeys(postalCode);
    }

    @Step("Нажатие кнопку Continue")
    public void clickContinue() {
        driver.findElement(CONTINUE_BUTTON).click();
    }

    @Step("Ввод данных для оформления заказа: имя='{firstName}', фамилия='{lastName}', индекс='{postalCode}'")
    public void fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
        clickContinue();
    }

    @Step("Получение сообщения об ошибке")
    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    @Step("Проверка отображения сообщения об ошибке")
    public boolean isErrorMessageDisplayed() {
        return driver.findElements(ERROR_MESSAGE).size() > 0;
    }

    @Step("Нажатие кнопку Finish")
    public void clickFinish() {
        driver.findElement(FINISH_BUTTON).click();
    }
}