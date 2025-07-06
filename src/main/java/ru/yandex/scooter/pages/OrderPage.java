package ru.yandex.scooter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.scooter.data.OrderData;

public class OrderPage {
    private WebDriver driver;

    // Первый экран
    private By nameInput = By.xpath("//input[@placeholder='* Имя']");
    private By surnameInput = By.xpath("//input[@placeholder='* Фамилия']");
    private By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private By phoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By nextButton = By.xpath("//button[text()='Далее']");

    // Второй экран
    private By dateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private By blackColorCheckbox = By.id("black");
    private By greyColorCheckbox = By.id("grey");
    private By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private By orderButton = By.xpath("//button[text()='Заказать']");
    private By confirmOrderButton = By.xpath("//button[text()='Да']");
    private By orderSuccessPopup = By.className("Order_ModalHeader__3FDaJ");

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
        return driver.findElements(orderSuccessPopup).size() > 0;
    }
}