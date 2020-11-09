package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class WalletPageTests extends TestBase {

    @DisplayName("Открытие страницы Единого кошелька")
    @Test
    public void searchProvidersTest() {
        step("Открываем страницу Единого кошелька", () -> {
            open("wallet/");
        });

        step("Вводим значение в строку поиска", () -> {
            $(".prsearch__input").setValue("колхоз").pressEnter();
        });

        step("Проверяем корректность поиска", () -> {
            $(".prlst__item").waitUntil(visible, 5000);
            $$(".prlst__item").shouldHaveSize(1)
                    .first().shouldHave(text("Удивительный колхоз"));
        });
    }
}
