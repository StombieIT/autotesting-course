import org.ficko.models.CredentialProviders;
import org.ficko.models.ICredentialsProvider;
import org.ficko.pages.AuthorizationPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorizationTest {
    private static ICredentialsProvider CREDENTIALS_PROVIDER;

    @BeforeAll
    public static void setUp() {
        CREDENTIALS_PROVIDER = CredentialProviders.bot();
    }

    @Test
    public void signInWithBotCredentials() {
        final String username = new AuthorizationPage()
                .signInWith(CREDENTIALS_PROVIDER)
                .profileUsername();
        final String credentialsLogin = CREDENTIALS_PROVIDER.getLogin();
        assertEquals(username, credentialsLogin + " " + credentialsLogin);
    }
}
