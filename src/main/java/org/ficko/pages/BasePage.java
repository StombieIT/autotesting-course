package org.ficko.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class BasePage extends Page {
    protected static final By PROFILE_LINK = By.xpath("//a[contains(@data-l, 't,userPage')]");
    private static final By GROUPS_LINK = By.xpath("//a[contains(@data-l, 't,userAltGroup')]");
    private static final By MUSIC_TOOLBAR_BUTTON = By.cssSelector(".toolbar_nav_a[aria-label='Музыка']");

    public String profileUsername() {
        return $(PROFILE_LINK)
                .should(Condition.visible.because("Ссылка на профиль не найдена"), Duration.ofSeconds(DEFAULT_WAITING_TIME))
                .attr("aria-label");
    }

    public GroupsPage navigateToGroupsPage() {
        $(GROUPS_LINK).should(Condition.visible.because("Не найдена ссылка на группы")).click();
        return new GroupsPage();
    }

    public MusicPage navigateToMusicPage() {
        $(MUSIC_TOOLBAR_BUTTON).should(Condition.visible.because("Не найдена кнопка музыки")).click();
        return new MusicPage();
    }
}
