package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    WebDriver driver;
    WebDriverWait wait;

//  Локатор кнопки принятия куки
    private final By cookieButton = By.id("rcc-confirm-button");

// Локатор блока FAQ
    private final By faqLocator = By.className("Home_FAQ__3uVm4");

// Локатор вопроса по индексу
    private By questionLocator(int index) {
        return By.id("accordion__heading-" + index);
    }

// Локатор ответа по индексу
    private By answerLocator(int index) {
        return By.id("accordion__panel-" + index);
    }

//  Локатор верхней кнопки Заказать
    private final By topOrderButton = By.xpath("//div[@class='Header_Nav__AGCXC']/button[contains(text(), 'Заказать')]");

//  Локатор нижней кнопки Заказать
    private final By botOrderButton = By.xpath("//div[@class='Home_FinishButton__1_cWm']/button[contains(text(), 'Заказать')]");


    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void openPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public void clickCookieButton() {
        driver.findElement(cookieButton).click();
    }

    public void scrollToFaq() {
        WebElement faq = wait.until(ExpectedConditions.presenceOfElementLocated(faqLocator));
        Actions actions = new Actions(driver);
        actions.moveToElement(faq).perform();
    }

    public void clickQuestion(int index) {
        wait.until(ExpectedConditions.elementToBeClickable(questionLocator(index))).click();
    }

    public String getAnswerText(int index) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator(index)));
        return driver.findElement(answerLocator(index)).getText();
    }

    public void clickBotOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(botOrderButton)).click();
    }

    public void clickTopOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(topOrderButton)).click();
    }
}
