package org.ficko.pages;

import com.codeborne.selenide.Condition;
import org.ficko.base.LoadablePage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage extends LoadablePage {
    private static final By OPTIONS_DROPDOWN = By.className("toggle-dropdown");
    private static final By SEARCH_FRIENDS_LINK = By.cssSelector("[data-l='outlandertarget,find-friends,t,find-friends']");

    @Override
    protected void isLoaded() {
        $(OPTIONS_DROPDOWN).shouldBe(visible.because("Не найден меню настроек"));
    }

    public ProfilePage openOptionsDropdown() {
        $(OPTIONS_DROPDOWN).shouldBe(visible.because("Не найдено меню опций")).hover().click();
        return this;
    }

    public void searchFriendsClick() {
        $(SEARCH_FRIENDS_LINK).shouldBe(visible.because("Не найдена ссылка на поиск друзей")).click();
    }
}
