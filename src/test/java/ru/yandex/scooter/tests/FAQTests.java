package ru.yandex.scooter.tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.scooter.pages.MainPage;
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

    @ParameterizedTest(name = "Проверка видимости ответа для вопроса {0}")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7})
    public void testFAQAnswerVisibility(int questionIndex) {
        assertFalse(mainPage.isAnswerVisible(questionIndex));
        mainPage.clickQuestion(questionIndex);
        assertTrue(mainPage.isAnswerVisible(questionIndex));
    }
}