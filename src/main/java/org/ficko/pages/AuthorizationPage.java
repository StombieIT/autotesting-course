package org.ficko.pages;

import com.codeborne.selenide.Condition;
import org.ficko.models.ICredentialsProvider;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class AuthorizationPage extends Page {
    private static final By EMAIL_FIELD = By.id("field_email");
    private static final By PASSWORD_FIELD = By.id("field_password");
    private static final By ENTER_BUTTON = By.cssSelector("[data-l='t,sign_in']");

    @Override
    protected void isLoaded() throws Error {
        load();
        super.isLoaded();
    }

    public BasePage signInWith(ICredentialsProvider credentialsProvider) {
        $(EMAIL_FIELD)
                .shouldBe(Condition.visible.because("Поле почты не найдено"))
                .val(credentialsProvider.getLogin());
        $(PASSWORD_FIELD)
                .shouldBe(Condition.visible.because("Поле пароля не найдено"))
                .val(credentialsProvider.getPassword());
        /** Не проверяем, т. к. метод родителя об этом уже позаботился */
        $(ENTER_BUTTON).click();
        return new BasePage();
    }
}
