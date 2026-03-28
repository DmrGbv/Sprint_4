package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    WebDriver driver;
    WebDriverWait wait;

//  Локатор поля Имя формы Заказа
    private final By fieldName = By.xpath("//input[@placeholder='* Имя']");

//  Локатор поля Фамилия формы Заказа
    private final By fieldSurname = By.xpath("//input[@placeholder='* Фамилия']");

//  Локатор поля Адрес формы Заказа
    private final By fieldAddress = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");

//  Локатор выпадающего списка Метро формы Заказа
    private final By fieldMetro = By.xpath("//input[@placeholder='* Станция метро']");

//  Локатор элемента выпадающего списка Метро
    private By metroItem(String metroName) {
        return By.xpath("//div[text()='" + metroName + "']");
    }

//  Локатор поля Телефон формы Заказа
    private final By fieldPhone = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");

//  Локатор кнопки Далее формы Заказа
    private final By nextButton = By.xpath("//button[contains(text(),'Далее')]");

//  Локатор поля Дата формы Заказа
    private final By fieldDate = By.xpath("//input[@placeholder='* Когда привезти самокат']");

//  Локатор стрелочки выпадающего списка Срок аренды формы Заказа
    private final By rentDurationArrow = By.xpath("//span[@class='Dropdown-arrow']");

//  Локатор стрелочки выпадающего списка Срок аренды формы Заказа
    private final By rentDurationDropDownMenu = By.xpath("//div[@class='Dropdown-menu']");

//  Локатор элемента выпадающего списка Срок аренды формы Заказа
    private By rentDurationItem(String duration) {
        return By.xpath("//div[@class='Dropdown-option' and text()='" + duration + "']");
    }

//  Локатор чек-бокса выбора Цвета формы Заказа
    private By colorItem(String color) {
        return By.xpath("//label[@class='Checkbox_Label__3wxSf' and text()='" + color + "']");
    }

//  Локатор поля Комментарий формы Заказа
    private final By fieldComment = By.xpath("//input[@placeholder='Комментарий для курьера']");

//  Локатор кнопки Заказать формы Заказа
    private final By orderButton = By.xpath("//div[@class='Order_Buttons__1xGrp']//button[contains(text(),'Заказать')]");

//  Локатор кнопки Да формы оформления Заказа
    private final By yesButton = By.xpath("//button[contains(text(),'Да')]");

//  Локатор сообщения об успешном оформлении заказа
    private final By successOrderMessage = By.xpath("//div[Order_ModalHeader__3FDaJ and text()='Заказ оформлен']");
    
    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void chooseMetroStation(String metroName) {
        driver.findElement(fieldMetro).sendKeys(metroName);
        driver.findElement(metroItem(metroName)).click();
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void chooseRentDuration(String duration) {
        driver.findElement(rentDurationArrow).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(rentDurationDropDownMenu));
        driver.findElement(rentDurationItem(duration)).click();
    }

    public void chooseColor(String color) {
        driver.findElement(colorItem(color)).click();
    }

    public void sendUserData(String name, String surname, String address, String metroName, String phone) {
        driver.findElement(fieldName).sendKeys(name);
        driver.findElement(fieldSurname).sendKeys(surname);
        driver.findElement(fieldAddress).sendKeys(address);
        chooseMetroStation(metroName);
        driver.findElement(fieldPhone).sendKeys(phone);
    }

    public void sendOrderData(String date, String duration, String color, String comment){
        wait.until(ExpectedConditions.elementToBeClickable(fieldDate));
        driver.findElement(fieldDate).sendKeys(date);
        chooseRentDuration(duration);
        chooseColor(color);
        driver.findElement(fieldComment).sendKeys(comment);
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void clickYesButton() {
        driver.findElement(yesButton).click();
    }

    public WebElement getSuccessOrderModal() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successOrderMessage));
    }


}
