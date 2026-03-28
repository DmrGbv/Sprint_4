import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

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
//  Открыть страницу
        mainPage.openPage();

//  Нажать кнопку принятия куки
        mainPage.clickCookieButton();

//  Найти и нажать верхнюю кнопку Заказать
        mainPage.clickTopOrderButton();

        orderPage.sendUserData(name, surname, address, metroName, phone);

        orderPage.clickNextButton();

        orderPage.sendOrderData(date, duration, color, comment);

        orderPage.clickOrderButton();

        orderPage.clickYesButton();

        assertTrue("Окно об успешном заказе не отображается", orderPage.isVisibleSuccessOrderModal());
    }

    @Test
    public void successOrderWithBotOrderButton() {
        //  Открыть страницу
        mainPage.openPage();

//  Нажать кнопку принятия куки
        mainPage.clickCookieButton();

//  Найти и нажать нижнюю кнопку Заказать
        mainPage.clickBotOrderButton();

        orderPage.sendUserData(name, surname, address, metroName, phone);

        orderPage.clickNextButton();

        orderPage.sendOrderData(date, duration, color, comment);

        orderPage.clickOrderButton();

        orderPage.clickYesButton();

        assertTrue("Окно об успешном заказе не отображается", orderPage.isVisibleSuccessOrderModal());
    }
}