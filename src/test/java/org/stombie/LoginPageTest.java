package org.stombie;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageTest {
    private static final String LOGIN_URL = "https://ok.ru";
    private static final String BOT_LOGIN = "vladuser4";
    private static final String BOT_PASSWORD = "delta67322";
    private static final String USER_NAME = "Женя Литвинова";
    private static final LoginPage loginPage = new LoginPage();
    private static final FeedPage feedPage = new FeedPage();

    @BeforeEach
    public void setUp() {
        open(LOGIN_URL);
    }

    @AfterEach
    public void tearDown() {
        closeWindow();
    }

    @Test
    public void login() {
        $(loginPage.getEmailField()).val(BOT_LOGIN);
        $(loginPage.getPasswordField()).val(BOT_PASSWORD);
        $(loginPage.getLoginButton()).click();
    }

    @Test
    public void userNavigationLinkContainsCorrectUserName() {
        login();
        assertEquals($(feedPage.getUserNavigationLink()).attr("aria-label"), USER_NAME);
    }
}
