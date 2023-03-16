package org.stombie;

import org.openqa.selenium.By;

public class LoginPage {
    private static final String EMAIL_FIELD_ID = "field_email";
    private static final String PASSWORD_FIELD_ID = "field_password";
    private static final String LOGIN_BUTTON_SELECTOR = "input[value=\"Войти в Одноклассники\"]";

    public By getEmailField() {
        return By.id(EMAIL_FIELD_ID);
    }

    public By getPasswordField() {
        return By.id(PASSWORD_FIELD_ID);
    }

    public By getLoginButton() {
        return By.cssSelector(LOGIN_BUTTON_SELECTOR);
    }
}
