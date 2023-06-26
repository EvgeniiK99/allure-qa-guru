package tests;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static tests.AllureTests.*;

public class WebSteps {
    @Step("Open Main page")
    public WebSteps openMainPage() {
        open(baseUrl);
        return this;
    }

    @Step("Find repository")
    public WebSteps findRepo(String repoName) {
        headerSearch.click();
        headerSearch.setValue(repoName).pressEnter();
        selenideRepo.click();
        return this;
    }

    @Step("Select Tab: Issue")
    public WebSteps selectTab() {
        issuesTab.shouldBe(visible);
        issuesTab.click();
        return this;
    }

    @Step("Check issue number: {ISSUENUMBER}")
    public WebSteps checkIssueNumber(String issueNumber) {
        $(withText(issueNumber)).should(exist);
        return this;
    }
}
