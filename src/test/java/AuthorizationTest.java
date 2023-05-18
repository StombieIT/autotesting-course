import org.ficko.models.TestBot;
import org.ficko.pages.AuthorizationPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorizationTest {
    private static final TestBot TEST_BOT = new TestBot("botS23AT26", "autotests2023");
    private static final String BASE_URL = "https://ok.ru";

    @BeforeEach
    public void setUp() {
        open(BASE_URL);
    }

    @Test
    public void signInWithBotCredentials() {
        String username = new AuthorizationPage()
                .signInWith(TEST_BOT)
                .profileUsername();
        String credentialsLogin = TEST_BOT.getLogin();
        assertEquals(username, credentialsLogin + " " + credentialsLogin, "Не совпадают регистрационные данные");
    }
}
