package ru.yandex.scooter.tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.scooter.pages.MainPage;
import ru.yandex.scooter.pages.OrderPage;
import ru.yandex.scooter.data.OrderData;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderTests {
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

    @ParameterizedTest(name = "Заказ от {0} через кнопку {1}")
    @MethodSource("orderDataProvider")
    public void testOrderFlow(OrderData data, boolean useTopButton) {
        if (useTopButton) {
            mainPage.clickOrderButtonTop();
        } else {
            mainPage.clickOrderButtonBottom();
        }

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillOrderForm(data);
        orderPage.submitOrder();
        assertTrue(orderPage.isOrderSuccessDisplayed(), "Окно успешного заказа должно появиться");
    }

    static Stream<org.junit.jupiter.params.provider.Arguments> orderDataProvider() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(
                        new OrderData("Иван", "Иванов", "ул. Пушкина, д. 10", "+79990000001", "07.07.2025", "black", "Позвонить за час"),
                        true),
                org.junit.jupiter.params.provider.Arguments.of(
                        new OrderData("Мария", "Петрова", "пр. Ленина, д. 5", "+79990000002", "08.07.2025", "grey", "Без звонка"),
                        false)
        );
    }
}