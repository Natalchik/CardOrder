import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderPositiveTest {


    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999/");
        $(".form-field_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79059059590");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void shouldSubmitRequestWhenHyphenInName() {
        open("http://localhost:9999/");
        $(".form-field_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Иванов-Петров Иван");
        $("[data-test-id=phone] input").setValue("+79059059590");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void shouldSubmitRequestWhenOnlyOneValueInName() {
        open("http://localhost:9999/");
        $(".form-field_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Иванов");
        $("[data-test-id=phone] input").setValue("+79059059590");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }
}
