package tests;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class AllureTests extends TestBase {
    public static final String
            SEARCHVALUE = "selenide",
            ISSUENUMBER = "2355";
    public static SelenideElement
            headerSearch = $(".header-search-input"),
            issuesTab = $("#issues-tab"),
            selenideRepo = $("a[href=\"/selenide/selenide\"]");

    @Test
    void onlySelenideTest() {
        open(baseUrl);
        headerSearch.click();
        headerSearch.setValue(SEARCHVALUE).pressEnter();
        selenideRepo.click();

        issuesTab.shouldBe(visible);
        issuesTab.click();

        $(withText(ISSUENUMBER)).should(exist);
    }

    @Test
    void stepLambdaTest() {
        step("Открыть главную сстраницу ", () -> {
            open(baseUrl);
        });
        step("Найти репозиторий через поиск: {SEARCHVALUE}", () -> {
            headerSearch.click();
            headerSearch.setValue(SEARCHVALUE).pressEnter();
            selenideRepo.click();
        });
        step("Переход на вкладку Issue", () -> {
            issuesTab.shouldBe(visible);
            issuesTab.click();
        });
        step("Проверка, что существует issue {ISSUENUMBER}");
        $(withText(ISSUENUMBER)).should(exist);
    }

    @Test
    void annotationStepTest() {
        WebSteps webSteps = new WebSteps();
        webSteps
                .openMainPage()
                .findRepo(SEARCHVALUE)
                .selectTab()
                .checkIssueNumber(ISSUENUMBER);
    }
}
