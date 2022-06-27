import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderNegativeTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldShowErrorIfFalseName() {
        $("[data-test-id=name] input").setValue("Ivanov");
        $("[data-test-id=phone] input").setValue("+79059059590");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldShowErrorIfEmptyNameField() {
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79059059590");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));

    }

    @Test
    void shouldShowErrorIfEmptyPhoneField() {
        $("[data-test-id=name] input").setValue("Иванов");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));

    }

    @Test
    void shouldShowErrorIfNumbersInPhoneEqual12() {
        $("[data-test-id=name] input").setValue("Иванов");
        $("[data-test-id=phone] input").setValue("+790590590901");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldShowErrorIfNumbersInPhoneEqual10() {
        $("[data-test-id=name] input").setValue("Иванов");
        $("[data-test-id=phone] input").setValue("+7905905909");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldShowErrorIfNoAgreementTerms() {
        $("[data-test-id=name] input").setValue("Иванов");
        $("[data-test-id=phone] input").setValue("+79059059090");
        $("[type=button]").click();
        $("[data-test-id='agreement'].input_invalid .checkbox__text").shouldHave(Condition.exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));

    }

    @Test
    void shouldShowErrorIfEmptyForm() {
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));

    }
}
