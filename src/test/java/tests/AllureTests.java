package tests;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class AllureTests extends TestBase {
    public static final String
            SEARCH_VALUE = "selenide",
            ISSUE_NUMBER = "2355";
    public static SelenideElement
            headerSearch = $(".header-search-input"),
            issuesTab = $("#issues-tab"),
            selenideRepo = $("a[href=\"/selenide/selenide\"]");

    @Test
    void onlySelenideTest() {
        open(baseUrl);
        headerSearch.click();
        headerSearch.setValue(SEARCH_VALUE).pressEnter();
        selenideRepo.click();

        issuesTab.shouldBe(visible);
        issuesTab.click();

        $(withText(ISSUE_NUMBER)).should(exist);
    }

    @Test
    void stepLambdaTest() {
        step("Open Main page", () -> open(baseUrl));
        step("Find repository: " + SEARCH_VALUE, () -> {
            headerSearch.click();
            headerSearch.setValue(SEARCH_VALUE).pressEnter();
            selenideRepo.click();
        });
        step("Select Tab: Issue", () -> {
            issuesTab.shouldBe(visible);
            issuesTab.click();
        });
        step("Check issue number: " + ISSUE_NUMBER, () -> {
            $(withText(ISSUE_NUMBER)).should(exist);
        });
    }

    @Test
    void annotationStepTest() {
        WebSteps webSteps = new WebSteps();
        webSteps
                .openMainPage()
                .findRepo(SEARCH_VALUE)
                .selectTab()
                .checkIssueNumber(ISSUE_NUMBER);
    }
}
