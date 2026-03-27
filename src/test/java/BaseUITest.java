import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.OrderPage;

public class BaseUITest {
    WebDriver driver;
    MainPage mainPage;
    OrderPage orderPage;

    //  Открыть браузера
    @Before
    public void startBrowser() {
        driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
    }

    //  Закрыть браузер
    @After
    public void tearDown() {
        driver.quit();
    }
}
