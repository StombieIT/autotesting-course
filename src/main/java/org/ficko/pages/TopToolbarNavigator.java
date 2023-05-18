package org.ficko.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TopToolbarNavigator {
    private static final By GROUPS_LINK = By.xpath("//a[contains(@data-l, 't,userAltGroup')]");
    private static final By MUSIC_TOOLBAR_BUTTON = By.cssSelector(".toolbar_nav_a[aria-label='Музыка']");

    public static GroupsPage goToGroupsPage() {
        $(GROUPS_LINK).should(visible.because("Не найдена ссылка на группы")).click();
        return new GroupsPage();
    }

    public static MusicPage goToMusicPage() {
        $(MUSIC_TOOLBAR_BUTTON).should(visible.because("Не найдена кнопка музыки")).click();
        return new MusicPage();
    }
}
