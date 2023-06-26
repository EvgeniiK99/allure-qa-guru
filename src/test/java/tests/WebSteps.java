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
    @Step("Открыть главную страницу")
    public WebSteps openMainPage() {
        open(baseUrl);
        return this;
    }

    @Step("Найти репозиторий через поиск")
    public WebSteps findRepo(String repoName) {
        headerSearch.click();
        headerSearch.setValue(repoName).pressEnter();
        selenideRepo.click();
        return this;
    }

    @Step("Переход на вкладку Issue")
    public WebSteps selectTab() {
        issuesTab.shouldBe(visible);
        issuesTab.click();
        return this;
    }

    @Step("Проверка, что существует issue {ISSUENUMBER}")
    public WebSteps checkIssueNumber(String issueNumber) {
        $(withText(issueNumber)).should(exist);
        return this;
    }
}
