package org.ficko.pages;

import java.util.List;
import java.util.stream.Collectors;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.ficko.base.LoadablePage;
import org.ficko.models.GroupTab;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class GroupsPage extends LoadablePage {
    private static final By SEARCH_GROUPS_FIELD = By.cssSelector("input[placeholder='Поиск по группам']");
    private static final By GROUP_CARD = By.xpath(".//*[contains(@data-l, 'groupCard')]");
    private static final By GROUPS_GRID = By.className("ugrid_cnt");
    private static final By MY_GROUPS_SLIDER = By.className("scroll-slider_list");
    private static final By JOIN_BUTTON = By.xpath(".//*[contains(@data-l, 't,join')]");
    private static final By GROUP_CONTAINER = By.xpath(".//*[@data-group-id]");

    public void goToGroupTab(GroupTab groupTab) {
        SelenideElement tab = $(By.xpath("//a[contains(@aria-label, '" + groupTab.getName() + "')]"));

        if (!tab.is(Condition.cssClass("__active"))) {
            tab.click();
        }
    }

    /**
     * @return id of group
     * */
    public String joinFirstGroupFromActual() {
        SelenideElement groupCard = $(GROUPS_GRID).$(GROUP_CARD);

        groupCard.$(JOIN_BUTTON).click();
        return groupCard.shouldHave(attribute("data-group-id").because("Не найден айди группы")).getAttribute("data-group-id");
    }

    public String joinFirstGroupFromNew() {
        goToGroupTab(GroupTab.NEWS);
        final SelenideElement groupContainer = $(GROUPS_GRID).$(GROUP_CONTAINER);

        groupContainer.$(JOIN_BUTTON).click();
        return groupContainer.shouldHave(attribute("data-group-id").because("Не найден айди группы")).getAttribute("data-group-id");
    }

    public List<String> myGroupsIds() {
        return $(MY_GROUPS_SLIDER).$$(GROUP_CONTAINER).stream()
                .map(element -> element.getAttribute("data-group-id"))
                .collect(Collectors.toList());
    }

    @Override
    protected void isLoaded() {
        $(SEARCH_GROUPS_FIELD).shouldBe(visible.because("Не видно поиска групп"));
    }
}
