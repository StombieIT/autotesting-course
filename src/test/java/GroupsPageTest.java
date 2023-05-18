import org.ficko.models.GroupTab;
import org.ficko.models.TestBot;
import org.ficko.pages.AuthorizationPage;
import org.ficko.pages.TopToolbarNavigator;
import org.ficko.pages.GroupsPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GroupsPageTest {
    private static final TestBot testBot = new TestBot("botS23AT26", "autotests2023");
    private static final String BASE_URL = "https://ok.ru";

    private GroupsPage groupsPage;

    @BeforeAll
    public static void globalSetUp() {
        open(BASE_URL);
        new AuthorizationPage().signInWith(testBot);
    }

    @BeforeEach
    public void setUp() {
        groupsPage = TopToolbarNavigator.goToGroupsPage();
    }

    @Test
    public void joinRandomGroupFromRecommendations() {
        String groupId = groupsPage.joinFirstGroupFromActual();
        groupsPage.goToGroupTab(GroupTab.ACTUAL);
        refresh();
        groupsPage = new GroupsPage();
        List<String> myGroupsIds = groupsPage.myGroupsIds();
        assertTrue(myGroupsIds.contains(groupId), "Список групп не содержит добавленную группу из раздела 'Актуальное'");
    }

    @Test
    public void joinRandomGroupFromNew() {
        String groupId = groupsPage.joinFirstGroupFromNew();
        groupsPage.goToGroupTab(GroupTab.NEWS);
        refresh();
        groupsPage = new GroupsPage();
        List<String> myGroupsIds = groupsPage.myGroupsIds();
        assertTrue(myGroupsIds.contains(groupId), "Список групп не содержит добавленную группу из раздела 'Новые'");
    }
}
