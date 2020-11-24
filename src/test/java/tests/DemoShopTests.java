package tests;

import io.qameta.allure.AllureId;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Owner("Kovalev Stanislav")
@Story("Проверка демо магазина")
public class DemoShopTests extends TestBase {
    @DisplayName("Проверока демо магазина с успешной оплатой")
    @AllureId("1221")
    @Issue("QC-7")
    @Test
    public void TestCardRubPositiveTest() {
        step("Открываем главную страницу", () ->
                open("merchant/")
        );

        step("Нажатие кнопки \"Купить\"", () -> {
            $(".demo__item_sweater form").click();
            switchTo().window(1);
            $("#TestCardRUB").waitUntil(visible, 10000);
        });
        step("Выбор способа оплаты TestCardRUB", () -> {
            $("[data-paymenttypeid='TestCardRUB']").click();
            $(".scenario_header").shouldHave(text("TestCardRUB"));
        });
        step("Заполнение данных карты", () -> {
            $(".selectbox-desktop_wrapper").click();
            $$(".selectbox-desktop-option").first().click();
            $("#CardHolderName").setValue("Test User");
            $("#CardExpireMonth").setValue("01");
            $("#CardExpireYear").setValue("2020");
            $("#CardCvv").setValue("111");
            $(".button__submit").shouldBe(enabled);
        });
    }
}
