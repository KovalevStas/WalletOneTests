package tests;

import allure.JiraIssue;
import io.qameta.allure.AllureId;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Owner("Kovalev Stanislav")
@Story("Проверка страницы Единой кассы")
public class MerchantPageTests extends TestBase {

    @DisplayName("Открытие главной страницы")
    @AllureId("1222")
    @JiraIssue("QC-7")
    @Test
    public void openMerchantPageTest() {

        step("Открываем страницу Единой кассы", () ->
                open("merchant/")
        );
        step("Проверяем наличие кнопки \"Подключить ваш бизнес\"", () -> {
            $(".header__btn_business").shouldBe(visible);
        });
    }

    @DisplayName("Переход на страницу единого кошелька")
    @AllureId("1223")
    @JiraIssue("QC-7")
    @Test
    public void openWalletPageTest() {

        step("Открываем страницу Единой кассы", () -> {
            open("merchant/");
        });

        step("Выполняем переход по кнопке \"Единый кошелек\"", () -> {
            $("#logo-select").shouldBe(visible).hover();
            sleep(1000);
            $("#logo-select").$(byText("Единый кошелёк")).click();
        });

        step("Проверяем корректность перехода на страницу \"Единый кошелек\"", () -> {
            $(byText("Удобный кабинет")).shouldBe(visible);
        });
    }
}
