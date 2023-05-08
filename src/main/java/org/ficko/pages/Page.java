package org.ficko.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class Page extends LoadableComponent<Page> implements AutoCloseable {
    protected static final By TOOLBAR_LOGO = By.className("toolbar_logo_img");
    private static final String BASE_URL = "https://ok.ru/";
    /** seconds */
    protected static final int DEFAULT_WAITING_TIME = 10;

    protected final SelenideElement waitFor(By by, String reason, int seconds) {
        return $(by).shouldBe(Condition.visible.because(reason), Duration.ofSeconds(seconds));
    }

    protected final SelenideElement waitFor(By by, String reason) {
        return waitFor(by, reason, DEFAULT_WAITING_TIME);
    }

    public Page() {
        isLoaded();
    }

    @Override
    protected void load() {
       open(BASE_URL);
    }

    @Override
    protected void isLoaded() throws Error {
        waitFor(TOOLBAR_LOGO, "Страница не загружена");
    }

    @Override
    public final void close() throws Exception {
        closeWindow();
    }
}
