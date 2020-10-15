package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.CustomDriver;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static helpers.AttachmentHelper.*;
import static org.openqa.selenium.logging.LogType.BROWSER;

public class TestBase {

    @BeforeAll
    static void start() {
        SelenideLogger.addListener("allure", new AllureSelenide()
                .savePageSource(true)
                .screenshots(true));
        Configuration.browser = CustomDriver.class.getName();
    }

    @AfterEach
    @Step("Attachments")
    public void finish() {
        attachScreenshot("Last screenshots");
        attachPageSource();
        attachAsText("Browser console logs",getConsoleLogs());
        attachVideo();
        closeWebDriver();
    }
    public static String getConsoleLogs(){
        return String.join("\n", Selenide.getWebDriverLogs(BROWSER));
    }
}
