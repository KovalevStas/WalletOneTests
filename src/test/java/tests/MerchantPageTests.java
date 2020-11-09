package tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Owner("Kovalev Stanislav")
@Story("Проверка страницы Единой кассы")
public class MerchantPageTests extends TestBase {

    @DisplayName("Открытие главной страницы")
    @Test
    public void openMerchantPageTest() {

        step("Открываем страницу Единой кассы", () -> {
            open("merchant/");
        });
        step("Проверяем наличие кнопки \"Подключить ваш бизнес\"", () -> {
            $(".header__btn_business").shouldBe(visible);
        });
    }

    @DisplayName("Переход на страницу единого кошелька")
    @Test
    public void openWalletPageTest() {

        step("Открываем страницу Единой кассы", () -> {
            open("merchant/");
        });

        step("Выполняем переход по кнопке \"Единый кошелек\"", () -> {
            $("#logo-select").shouldBe(visible).hover();
            $("#logo-select").$(byText("Единый кошелёк")).waitUntil(visible, 2000);
            $("#logo-select").$(byText("Единый кошелёк")).click();
        });

        step("Проверяем корректность перехода на страницу \"Единый кошелек\"", () -> {
            $(byText("Удобный кабинет")).shouldBe(visible);
        });
    }
}
