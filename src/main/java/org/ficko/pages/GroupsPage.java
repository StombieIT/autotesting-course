package org.ficko.pages;

import java.util.List;
import java.util.stream.Collectors;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;

public class GroupsPage extends Page {
    private static final By SEARCH_GROUPS_FIELD = By.cssSelector("input[placeholder='Поиск по группам']");
    private static final By GROUP_CARD = By.xpath(".//*[contains(@data-l, 'groupCard')]");
    private static final By GROUPS_GRID = By.className("ugrid_cnt");
    private static final By MY_GROUPS_SLIDER = By.className("scroll-slider_list");
    private static final By JOIN_BUTTON = By.xpath(".//*[contains(@data-l, 't,join')]");
    private static final By GROUP_CONTAINER = By.xpath(".//*[@data-group-id]");

    @Override
    protected void isLoaded() throws Error {
        waitFor(SEARCH_GROUPS_FIELD, "Нет строки поиска групп");
    }

    public void navigateSafely(String tabName) {
        final SelenideElement tab = $(By.xpath("//a[contains(@aria-label, '" + tabName + "')]"));

        if (!tab.is(Condition.cssClass("__active"))) {
            tab.click();
        }
    }

    public void navigateToActual() {
        navigateSafely("Актуально");
    }

    public void navigateToNew() {
        navigateSafely("Новые");
    }

    /**
     * @return id of group
     * */
    public String joinFirstGroupFromActual() {
        final SelenideElement groupCard = $(GROUPS_GRID).$(GROUP_CARD);

        groupCard.$(JOIN_BUTTON).click();
        return groupCard.getAttribute("data-group-id");
    }

    public String joinFirstGroupFromNew() {
        navigateToNew();
        final SelenideElement groupContainer = $(GROUPS_GRID).$(GROUP_CONTAINER);

        groupContainer.$(JOIN_BUTTON).click();
        return groupContainer.getAttribute("data-group-id");
    }

    public List<String> myGroupsIds() {
        return $(MY_GROUPS_SLIDER)
                .findAll(GROUP_CONTAINER)
                .stream()
                .map(element -> element.getAttribute("data-group-id"))
                .collect(Collectors.toList());
    }

    public List<String> myGroupsIdsRefreshed() {
        navigateToActual();
        refresh();
        return myGroupsIds();
    }
}
