package tests;

import config.WebDriverConfig;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.link;
import static io.qameta.allure.Allure.step;

@Owner("Kovalev Stanislav")
@Story("Проверка десо магазина")
public class DemoShopTests {

    final WebDriverConfig config = ConfigFactory.newInstance().create(WebDriverConfig.class);

    final String base_url = config.url();

    @DisplayName("Проверока демо магазина с успешной оплатой")
    @Test
    public void openPageTest() {
        link("GitHub", base_url);

        step("Открываем главную страницу", () -> {
            open(base_url);
        });
        step("Нажатие кнопки \"Купить\"", () -> {
            $(".demo__item_sweater form").click();
            $(".order_info").$(withText("Толстовка")).isDisplayed();
        });
        step("Выбор способа оплаты TestCardRUB", () -> {
            $("#TestCardRUB a").click();
            $(".scenario_header").shouldHave(text("TestCardRUB"));
        });
        step("Заполнение данных карты", () -> {
            $(".selectbox-desktop_wrapper").click();
            $$(".selectbox-desktop-option").first().click();
            $("#CardHolderName").setValue("Test User");
            $(".__month").setValue("01");
            $(".__year").setValue("01");
            $(".CardCvv").setValue("111").pressEnter();
        });


    }

}
