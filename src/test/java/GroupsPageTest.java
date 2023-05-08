import org.ficko.models.CredentialProviders;
import org.ficko.pages.AuthorizationPage;
import org.ficko.pages.BasePage;
import org.ficko.pages.GroupsPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GroupsPageTest {
    private static BasePage basePage;

    private GroupsPage groupsPage;

    @BeforeAll
    public static void globalSetUp() {
        basePage = new AuthorizationPage()
                .signInWith(CredentialProviders.bot());
    }

    @BeforeEach
    public void setUp() {
        groupsPage = basePage.navigateToGroupsPage();
    }

    @Test
    public void joinRandomGroupFromRecommendations() {
        String groupId = groupsPage.joinFirstGroupFromActual();
        List<String> myGroupsIds = groupsPage.myGroupsIdsRefreshed();
        assertTrue(myGroupsIds.contains(groupId));
    }

    @Test
    public void joinRandomGroupFromNew() {
        String groupId = groupsPage.joinFirstGroupFromNew();
        List<String> myGroupsIds = groupsPage.myGroupsIdsRefreshed();
        assertTrue(myGroupsIds.contains(groupId));
    }
}
