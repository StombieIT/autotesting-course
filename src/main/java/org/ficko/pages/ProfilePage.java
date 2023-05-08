package org.ficko.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ProfilePage extends BasePage {
    private static final By OPTIONS_DROPDOWN = By.className("toggle-dropdown");
    private static final By SEARCH_FRIENDS_LINK = By.cssSelector("[data-l='outlandertarget,find-friends,t,find-friends']");

    public ProfilePage() {
        super();
    }

    @Override
    protected void load() {
        $(PROFILE_LINK)
                .shouldBe(Condition.visible.because("Не найдена ссылка на профиль"))
                .click();
    }

    public ProfilePage openOptionsDropdown() {
        $(OPTIONS_DROPDOWN)
                .shouldBe(Condition.visible.because("Не найдено меню опций"))
                .hover()
                .click();
        return this;
    }

    public void searchFriendsClick() {
        $(SEARCH_FRIENDS_LINK)
                .shouldBe(Condition.visible.because("Не найдена ссылка на поиск друзей"))
                .click();
    }
}
