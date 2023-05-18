package org.ficko.pages;

import org.ficko.base.LoadablePage;
import org.ficko.models.TestBot;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class AuthorizationPage extends LoadablePage {
    private static final By EMAIL_FIELD = By.id("field_email");
    private static final By PASSWORD_FIELD = By.id("field_password");
    private static final By ENTER_BUTTON = By.cssSelector("[data-l='t,sign_in']");

    @Override
    protected void isLoaded() throws Error {
        $(EMAIL_FIELD).shouldBe(visible.because("Поле почты не найдено"));
        $(PASSWORD_FIELD).shouldBe(visible.because("Поле пароля не найдено"));
        $(ENTER_BUTTON).shouldBe(visible.because("Нету кнопки входа"));
    }

    public MainPage signInWith(TestBot testBot) {
        $(EMAIL_FIELD).shouldBe(visible.because("Поле почты не найдено")).sendKeys(testBot.getLogin());
        $(PASSWORD_FIELD).shouldBe(visible.because("Поле пароля не найдено")).sendKeys(testBot.getPassword());
        $(ENTER_BUTTON).shouldBe(visible.because("Нету кнопки входа")).click();
        return new MainPage();
    }
}
