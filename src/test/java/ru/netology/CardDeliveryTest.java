package ru.netology;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardDeliveryTest {
    String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    @Test
    void TestFormCardDelivery() {
        open("http://localhost:9999");
        $("[data-test-id = city] input").setValue("Москва");
        $("[placeholder = 'Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder = 'Дата встречи']").setValue(date);
        $("[data-test-id = name] input").setValue("Иванов Иван");
        $("[data-test-id = phone] input").setValue("+71234567890");
        $("[data-test-id = agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id=notification]").shouldHave(exactText("Успешно! Встреча успешно забронирована на " + date), Duration.ofSeconds(15));
    }
}
