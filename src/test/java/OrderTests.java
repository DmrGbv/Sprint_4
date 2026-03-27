import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTests extends BaseUITest {

    private String name;
    private String surname;
    private String address;
    private String metroName;
    private String phone;

    private String date;
    private String duration;
    private String color;
    private String comment;


    public OrderTests(String name, String surname, String address, String metroName, String phone, String date, String duration, String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroName = metroName;
        this.phone = phone;

        this.date = date;
        this.duration = duration;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {"Принц", "Каспиан", "Нарния", "Лихоборы", "89997776655", "04.06.2026", "двое суток", "чёрный жемчуг", "Без комментариев"},
                {"Гарри", "Поттер", "Чулан под лестницей", "Бибирево", "12223334455", "28.04.2026", "пятеро суток", "серая безысходность", "С молнией"},
                {"Китнисс", "Эвердин", "Дистрикт 13", "Чертановская", "13335557799", "26.11.26", "сутки", "серая безысходность", "Комментарий"},
        };
    }

    @Test
    public void successOrderWithTopOrderButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

//  Открыть страницу
        mainPage.openPage();

//  Нажать кнопку принятия куки
        mainPage.clickCookieButton();

//  Найти и нажать верхнюю кнопку Заказать
        mainPage.clickTopOrderButton();

        orderPage.sendUserData(name, surname, address, metroName, phone);

        driver.findElement(By.xpath("//button[contains(text(),'Далее')]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='* Когда привезти самокат']")));

        orderPage.sendOrderData(date, duration, color, comment);

        driver.findElement(By.xpath("//div[@class='Order_Buttons__1xGrp']//button[contains(text(),'Заказать')]")).click();

        driver.findElement(By.xpath("//button[contains(text(),'Да')]")).click();

        WebElement successOrderModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Order_Modal__YZ-d3']")));
        assertTrue("Окно об успешном заказе не отображается", successOrderModal.isDisplayed());
    }

    @Test
    public void successOrderWithBotOrderButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

//  Открыть страницу
        mainPage.openPage();

//  Нажать кнопку принятия куки
        mainPage.clickCookieButton();

//  Найти и нажать нижнюю кнопку Заказать
        mainPage.scrollToBotOrderButton();
        mainPage.clickBotOrderButton();

        orderPage.sendUserData(name, surname, address, metroName, phone);

        driver.findElement(By.xpath("//button[contains(text(),'Далее')]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='* Когда привезти самокат']")));

        orderPage.sendOrderData(date, duration, color, comment);

        driver.findElement(By.xpath("//div[@class='Order_Buttons__1xGrp']//button[contains(text(),'Заказать')]")).click();

        driver.findElement(By.xpath("//button[contains(text(),'Да')]")).click();

        WebElement successOrderModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Order_Modal__YZ-d3']")));
        assertTrue("Окно об успешном заказе не отображается", successOrderModal.isDisplayed());
    }
}

//    @Test
//    public void errorOrderTest() {
////  Открыть страницу
//        mainPage.openPage();
//
////  Нажать кнопку принятия куки
//        mainPage.clickCookieButton();
//
////  Найти и нажать кнопку "Статус заказа"
//        mainPage.clickStatusButton();
//
////  Ввести несуществующий номер заказа в поле ввода номера заказа и поставить ожидание
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[placeholder='Введите номер заказа']")));
//        inputField.sendKeys("000");
//
////    Найти и нажать кнопку GO
//        mainPage.clickGoButton();
//
////    Сравнить ФР и ОР
//        WebElement errorImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[alt='Not found']")));
//        assertTrue("Сообщение об ошибке не отображается", errorImage.isDisplayed());
//    }

