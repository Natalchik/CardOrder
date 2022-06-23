import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderNegativeTest {
    @Test
    void shouldShowErrorIfFalseName() {
        open("http://localhost:9999/");
        $(".form-field_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Ivanov");
        $("[data-test-id=phone] input").setValue("+79059059590");
        $("[data-test-id=agreement] input").click();
        $("[type=button]").click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }
    @Test
    void shouldShowErrorIfEmptyNameField() {
        open("http://localhost:9999/");
        $(".form-field_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79059059590");
        $("[data-test-id=agreement] input").click();
        $("[type=button]").click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));

    }
    @Test
    void shouldShowErrorIfEmptyPhoneField() {
        open("http://localhost:9999/");
        $(".form-field_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Иванов");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement] input").click();
        $("[type=button]").click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));

    }
    @Test
    void shouldShowErrorIfNumbersInPhoneEqual12() {
        open("http://localhost:9999/");
        $(".form-field_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Иванов");
        $("[data-test-id=phone] input").setValue("+790590590901");
        $("[data-test-id=agreement] input").click();
        $("[type=button]").click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }
    @Test
    void shouldShowErrorIfNumbersInPhoneEqual10() {
        open("http://localhost:9999/");
        $(".form-field_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Иванов");
        $("[data-test-id=phone] input").setValue("+7905905909");
        $("[data-test-id=agreement] input").click();
        $("[type=button]").click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }
    @Test
    void shouldShowErrorIfNoAgreementTerms() {
        open("http://localhost:9999/");
        $(".form-field_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Иванов");
        $("[data-test-id=phone] input").setValue("+79059059090");
        $("[type=button]").click();
        $(".input_invalid .checkbox__text").shouldHave(Condition.exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));

    }
    @Test
    void shouldShowErrorIfEmptyForm() {
        open("http://localhost:9999/");
        $(".form-field_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement] input").click();
        $("[type=button]").click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));

    }
}
