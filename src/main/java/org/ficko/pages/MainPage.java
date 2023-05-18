package org.ficko.pages;

import org.ficko.base.LoadablePage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPage extends LoadablePage {
    protected static final By PROFILE_LINK = By.xpath("//a[contains(@data-l, 't,userPage')]");

    @Override
    protected void isLoaded() {
        $(PROFILE_LINK).shouldBe(visible.because("Ссылка на профиль не найдена"));
    }

    public String profileUsername() {
        return $(PROFILE_LINK).shouldHave(attribute("aria-label").because("Ссылка на профиль не найдена")).getAttribute("aria-label");
    }
}
