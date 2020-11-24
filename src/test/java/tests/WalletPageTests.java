package tests;

import allure.JiraIssue;
import io.qameta.allure.AllureId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class WalletPageTests extends TestBase {

    @DisplayName("Открытие страницы Единого кошелька")
    @AllureId("1218")
    @JiraIssue("QC-7")
    @Test
    public void searchProvidersTest() {
        step("Открываем страницу Единого кошелька", () ->
                open("wallet/")
        );

        step("Вводим значение в строку поиска", () -> {
            $(".prsearch__input").setValue("колхоз").pressEnter();
        });

        step("Проверяем корректность поиска", () -> {
            $(".prlst__item").waitUntil(visible, 5000);
            $$(".prlst__item").shouldHaveSize(1)
                    .first().shouldHave(text("Удивительный колхоз"));
        });
    }

    @DisplayName("Открытие окна информации об офисе")
    @AllureId("1219")
    @JiraIssue("QC-7")
    @Test
    public void showInformTest() {
        step("Открываем страницу Единого кошелька", () ->
                open("wallet/")
        );

        step("Наводим курсор на метку на карте", () -> {
            $(".map-marker__2").scrollTo();
            sleep(2000);
            $(".map-marker__2").hover();
        });

        step("Проверяем, что появилос окно с информацией", () -> {
            $(withText("Офис в России")).shouldBe(visible);
        });
    }

    @DisplayName("Смена языка страницы")
    @AllureId("1220")
    @JiraIssue("QC-7")
    @Test
    public void changeLanguageTest() {
        step("Открываем страницу Единого кошелька", () ->
                open("wallet/")
        );

        step("Выбираем английский язык", () -> {
            $(".country").scrollTo().hover();
            $(".country-item__en").waitUntil(visible, 2000);
            $(".country-item__en a").click();
        });

        step("Проверяем, что на старнице изменился язык", () -> {
            $(byText("Convenient money transfer")).shouldBe(visible);
        });
    }
}
