// FAQTests.java
package ru.yandex.scooter.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.scooter.pages.MainPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FAQTests {

    private WebDriver driver;
    private MainPage mainPage;

    @BeforeAll
    public void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        mainPage.openPage();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testFAQAnswersVisibility() {
        int questionsCount = 8;

        for (int i = 0; i < questionsCount; i++) {

            assertFalse(mainPage.isAnswerVisible(i), "Ответ должен быть изначально скрыт");

            mainPage.clickQuestion(i);


            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOf(driver.findElements(By.className("accordion__panel")).get(i)));


            assertTrue(mainPage.isAnswerVisible(i), "Ответ должен отображаться после клика");
        }
    }
}
