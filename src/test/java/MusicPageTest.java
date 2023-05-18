import org.ficko.models.TestBot;
import org.ficko.models.Track;
import org.ficko.pages.AuthorizationPage;
import org.ficko.pages.MusicPage;
import org.ficko.pages.TopToolbarNavigator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class MusicPageTest {
    private static final TestBot TEST_BOT = new TestBot("botS23AT26", "autotests2023");
    private static final String BASE_URL = "https://ok.ru";
    private static MusicPage musicPage;

    @BeforeAll
    public static void globalSetUp() {
        open(BASE_URL);
        new AuthorizationPage().signInWith(TEST_BOT);
        musicPage = TopToolbarNavigator.goToMusicPage();
    }

    @Test
    public void checkMyTracksAfterTrackAdding() {
        String addedTrackLink = musicPage.navigateToPopular()
                                         .addFirstPopularTrack();
        assertThat("Трек не содержится в добавленных", musicPage.navigateToMyTracks().tracksLinks(),
                hasItem(addedTrackLink));
    }

    @Test
    public void createPlaylistWithTrack() {
        String playlistName = "awkfoawf";
        Track createdTrack = musicPage
                .navigateToPopular()
                .createPlaylistForFirstTrack(playlistName);
        assertThat("Список плейлистов не содержит созданный плейлист", musicPage.navigateToMyTracksLazy().myPlaylists(),
                hasItem(playlistName));
        assertThat("Список треков созданного плейлиста не содержит одного или несколько треков", musicPage.navigateToPlaylistWithName(playlistName).tracks(),
                hasItem(createdTrack));
    }
}
