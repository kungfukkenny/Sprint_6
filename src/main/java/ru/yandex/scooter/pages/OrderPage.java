package ru.yandex.scooter.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.scooter.data.OrderData;
import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;
    private final By nameInput = By.xpath("//input[@placeholder='* Имя']");
    private final By surnameInput = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By phoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[text()='Далее']");
    private final By dateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By blackColorCheckbox = By.id("black");
    private final By greyColorCheckbox = By.id("grey");
    private final By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//button[text()='Заказать']");
    private final By confirmOrderButton = By.xpath("//button[text()='Да']");
    private final By orderSuccessPopup = By.className("Order_ModalHeader__3FDaJ");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillOrderForm(OrderData data) {
        driver.findElement(nameInput).sendKeys(data.getName());
        driver.findElement(surnameInput).sendKeys(data.getSurname());
        driver.findElement(addressInput).sendKeys(data.getAddress());
        driver.findElement(phoneInput).sendKeys(data.getPhone());
        driver.findElement(nextButton).click();
        driver.findElement(dateInput).sendKeys(data.getDate());

        if ("black".equalsIgnoreCase(data.getColor())) {
            driver.findElement(blackColorCheckbox).click();
        } else if ("grey".equalsIgnoreCase(data.getColor())) {
            driver.findElement(greyColorCheckbox).click();
        }

        driver.findElement(commentInput).sendKeys(data.getComment());
    }

    public void submitOrder() {
        driver.findElement(orderButton).click();
        driver.findElement(confirmOrderButton).click();
    }

    public boolean isOrderSuccessDisplayed() {
        try {
            WebElement successPopup = driver.findElement(orderSuccessPopup);
            return successPopup.isDisplayed() &&
                    successPopup.getText().contains("Заказ оформлен");
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}