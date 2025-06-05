package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SecondOrderPage;
import pages.MainPage;
import pages.FirstOrderPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParameterizedTestOrderPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage Scooter;
    private FirstOrderPage user;
    private SecondOrderPage finalUser;


    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.get("https://qa-scooter.praktikum-services.ru/");

        Scooter = new MainPage(driver);
        user = new FirstOrderPage(driver);
        finalUser = new SecondOrderPage(driver);
        Scooter.clickCookiesButton();
    }

    @AfterEach
    void teardown() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ignored) {
        }
        driver.quit();
    }

    @ParameterizedTest
    @MethodSource("parameters")
    public void testOrderWithDifferentData(
            String buttonLocation,
            String name,
            String surname,
            String adrees,
            String metro,
            String number,
            int date,
            String days,
            String comment){

        if ("HEADER".equals(buttonLocation)) {
            Scooter.clickHeaderToOrder();
        } else if ("MIDDLE".equals(buttonLocation)) {
            WebElement element = driver.findElement(Scooter.clickMiddleToOrder());
            ((JavascriptExecutor)driver).executeScript(
                    "arguments[0].scrollIntoView({block: 'center'});",
                    element
            );
            element.click();
        }


        Scooter.clickHeaderToOrder();

        String title = driver.getTitle();
        assertEquals("undefined", title);

        user.fillName(name);
        user.fillSurname(surname);
        user.fillAddress(adrees);
        user.fillMetro(metro);
        user.fillTelephone(number);
        user.clickButtonNext();
        String titleOrder = driver.getTitle();
        assertEquals("undefined", titleOrder);
        finalUser.fillDeliveryDateWithCurrentDatePlusDays(date);
        finalUser.selectOption(days);
        finalUser.сhoiceColorScooter();
        finalUser.writeComment(comment);
        finalUser.clickButtonOrder();
        finalUser.clickButtonYes();
        assertTrue(finalUser.isOrderSuccessDisplayed());

    }


    static Stream<Arguments> parameters(){
        return Stream.of(
                Arguments.of("HEADER","Горшенев","Михаил","Сказки,1",
                        "Лубянка","+79111234567",1,"сутки","Пойдем приятель в лес!"),
                Arguments.of("MIDDLE","Дизель","Вин","Нью Йорк",
                        "Сокольники","+79997654321",2,"трое суток","Погнали!")
        );
    }
}