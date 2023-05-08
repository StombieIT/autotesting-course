import org.ficko.models.CredentialProviders;
import org.ficko.models.Track;
import org.ficko.pages.AuthorizationPage;
import org.ficko.pages.MusicPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class MusicPageTest {

    private static Random random;
    private static MusicPage musicPage;

    @BeforeAll
    public static void globalSetUp() {
        musicPage = new AuthorizationPage()
                .signInWith(CredentialProviders.bot())
                .navigateToMusicPage();
        random = new Random();
    }

    @Test
    public void checkMyTracksAfterTrackAdding() {
        String addedTrackLink = musicPage.navigateToPopular()
                                         .addFirstPopularTrack();
        assertThat(
                musicPage.navigateToMyTracks()
                         .tracks()
                         .stream()
                         .map(Track::getLink)
                         .collect(Collectors.toList()),
                hasItem(addedTrackLink)
        );
    }

    @Test
    public void createPlaylistWithTrack() {
        final String playlistName = "awkfoawf";
        Track createdTrack = musicPage
                .navigateToPopular()
                .createPlaylistForFirstTrack(playlistName);
        assertThat(
                musicPage.navigateToMyTracksLazy()
                         .myPlaylists(),
                hasItem(playlistName)
        );
        assertThat(
            musicPage.navigateToPlaylistWithName(playlistName)
                     .tracks(),
            hasItem(createdTrack)
        );
    }
}
