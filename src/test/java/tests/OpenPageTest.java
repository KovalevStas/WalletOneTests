package tests;

import com.codeborne.selenide.Condition;
import config.WebDriverConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.link;
import static io.qameta.allure.Allure.step;

public class OpenPageTest {

    final WebDriverConfig config = ConfigFactory.newInstance().create(WebDriverConfig.class);

    final String base_url = config.url();

    @DisplayName("Открытие главной страницы")
    @Test
    public void openPageTest() {
        link("GitHub", base_url);

        step("Открываем главную страницу", () -> {
            open(base_url);
        });
        step("Проверяем наличие кнопки \"Подключить ваш бизнес\"", () -> {
        $(".header__btn_business").shouldBe(visible);
        });

    }
}
