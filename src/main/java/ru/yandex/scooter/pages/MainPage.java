package ru.yandex.scooter.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class MainPage {
    private final WebDriver driver;
    private final By orderButtonTop = By.xpath("//button[text()='Заказать']");
    private final By orderButtonBottom = By.xpath("(//button[text()='Заказать'])[last()]");
    private final By questions = By.className("accordion__button");
    private final By answers = By.className("accordion__panel");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }

    public void clickOrderButtonBottom() {
        driver.findElement(orderButtonBottom).click();
    }

    public void clickQuestion(int index) {
        WebElement question = driver.findElements(questions).get(index);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", question);
        question.click();
        waitForAnswerVisibility(index);
    }

    public boolean isAnswerVisible(int index) {
        List<WebElement> answerElements = driver.findElements(answers);
        return answerElements.get(index).isDisplayed();
    }

    private void waitForAnswerVisibility(int index) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(
                        driver.findElements(answers).get(index)
                ));
    }
}