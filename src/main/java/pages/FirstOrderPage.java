package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FirstOrderPage {
    private final WebDriver driver;

    //Поле Имя
    private final By name = By.xpath(".//input[@placeholder='* Имя']");
    //Поле Фамилия
    private final By surname = By.xpath(".//input[@placeholder='* Фамилия']");
    //Поле Адрес
    private final By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // Поле Станция Метро
    private final By metro = By.xpath(".//input[@placeholder='* Станция метро']");
    // Поле Телефон
    private final By telephone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка далее
    private final By buttonNext = By.xpath(".//button[text()='Далее']");

    public FirstOrderPage(WebDriver driver) {
        this.driver = driver;
    }


    public void fillName(String nameUser) {
        WebElement fieldName = driver.findElement(name);
        fieldName.click();
        fieldName.clear();
        fieldName.sendKeys(nameUser);
    }


    public void fillSurname(String surnameUser) {
        WebElement fieldSurname = driver.findElement(surname);
        fieldSurname.click();
        fieldSurname.clear();
        fieldSurname.sendKeys(surnameUser);
    }


    public void fillAddress(String addressUser) {
        WebElement fieldAddress = driver.findElement(address);
        fieldAddress.click();
        fieldAddress.clear();
        fieldAddress.sendKeys(addressUser);
    }


    public void fillMetro(String metroUser) {
        WebElement fieldMetro = driver.findElement(metro);
        fieldMetro.click();
        fieldMetro.clear();
        fieldMetro.sendKeys(metroUser);
        driver.findElement(By.xpath("//li[contains(@class, 'select-search__row')][1]")).click();

    }



    public void fillTelephone(String numberUser) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement fieldTelephone = wait.until(ExpectedConditions.visibilityOfElementLocated(telephone));
        fieldTelephone.clear();
        fieldTelephone.sendKeys(numberUser);
    }

    public void clickButtonNext(){
        driver.findElement(buttonNext).click();
    }

}

