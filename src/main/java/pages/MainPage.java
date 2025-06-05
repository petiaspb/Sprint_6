package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;

    //Кнопка "Заказать" сверху
    private final By headerToOrder = By.xpath(".//button[@class='Button_Button__ra12g']");

    //Кнопка "Заказать" внизу
    private final By middleToOrder = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");

    //Раздел "Вопросы о важном"
    private final By ImportantQuestions = By.xpath(".//div[text()='Вопросы о важном']");

    //Кнопка согласие с cookies
    private final By cookiesButton = By.xpath(".//button[@id='rcc-confirm-button']");

    //Вопросы
    private final String questions = ".//div[@id='accordion__heading-%d']";

    //Ответы
    private final String answer = ".//div[@id='accordion__panel-%d']";


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getQuestions(int questionsIndex) {
        return By.xpath(String.format(questions, questionsIndex));
    }

    public By getAnswer(int questionsIndex) {
        return By.xpath(String.format(answer, questionsIndex));
    }

    public By getImportantQuestions() {
        return ImportantQuestions;
    }

    public void clickCookiesButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.elementToBeClickable(cookiesButton)).click();
    }

    public void clickHeaderToOrder() {
        driver.findElement(headerToOrder).click();
    }
    public By clickMiddleToOrder() {
        return middleToOrder;
    }


}
