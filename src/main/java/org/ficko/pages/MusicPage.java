package org.ficko.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.ficko.base.LoadablePage;
import org.ficko.models.Track;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MusicPage extends LoadablePage {
    private static final By PLAYER_CONTROLS = By.cssSelector("wm-player-controls");
    private static final By TRACK = By.cssSelector("wm-track");
    private static final By TRACKS_LIST = By.cssSelector("wm-tracks-list");
    private static final By TRACK_TITLE = By.cssSelector("a[data-tsid='track_name']");
    private static final By TRACK_ADD_BUTTON = By.cssSelector("wm-track-add-button");
    private static final By POPULAR_TAB = By.xpath("//a[contains(@data-tsid, 'showcase')]");
    private static final By MY_TRACKS_TAB = By.xpath("//a[contains(@data-tsid, 'library')]");
    private static final By MY_TRACKS_COUNTER = By.cssSelector("wm-notification-counter");
    private static final By TRACK_ACTIONS = By.cssSelector("wm-track-actions");
    private static final By TRACK_COLLECTIONS_ACTIONS = By.xpath(".//wm-tico[contains(@data-tsid, 'collections_actions')]");
    private static final By PLAYLISTS_NAVIGATION_BAR = By.cssSelector("wm-nav-playlists");
    private static final By TEXT_CONTAINER = By.cssSelector(".text");
    private static final By CREATE_PLAYLIST = By.xpath(".//wm-tico[contains(@data-tsid, 'create_playlist')]");
    private static final By CREATE_PLAYLIST_BUTTON = By.xpath(".//button[contains(@data-tsid, 'create_playlist_button')]");
    private static final By CREATE_PLAYLIST_FIELD = By.xpath(".//input[contains(@data-tsid, 'input_name_playlist')]");

    public MusicPage navigateToMyTracksLazy() {
        final SelenideElement myTracksTab = $(MY_TRACKS_TAB).shouldBe(visible.because("Не найдена вкладка моих треков"));

        if (!myTracksTab.has(Condition.cssClass("__active"))) {
            myTracksTab.click();
        }
        return this;
    }

    public MusicPage navigateToMyTracks() {
        $(MY_TRACKS_TAB).shouldBe(visible.because("Не найдена вкладка моих треков")).click();
        return this;
    }

    public MusicPage navigateToPopular() {
        $(POPULAR_TAB).shouldBe(visible.because("Не найдена вкладка популярных треков`")).click();
        return this;
    }

    public String addFirstPopularTrack() {
        final SelenideElement firstTrack = $(TRACK).should(visible.because("Не найден первый популярный трек"));
        firstTrack.hover()
                .$(TRACK_ADD_BUTTON)
                .shouldBe(visible.because("Не найдена кнопка добавления трека"))
                .click();
        return firstTrack.$(TRACK_TITLE)
                .shouldHave(attribute("href").because("Не найден заголовок трека"))
                .getAttribute("href");
    }

    public MusicPage navigateToPlaylistWithName(String playlistName) {
        $(PLAYLISTS_NAVIGATION_BAR)
            .$(By.xpath(".//*[contains(@class, 'text') and contains(text(), '" + playlistName + "')]"))
            .shouldBe(visible.because("Не найден навигационная панель плейлистов"))
            .click();
        return this;
    }

    public List<Track> tracks()  {
        return $(TRACKS_LIST).shouldBe(visible.because("Не найден список треков на странице")).$$(TRACK_TITLE).stream()
                .map(element -> new Track(element.getAttribute("href"), element.text()))
                .collect(Collectors.toList());
    }

    public List<String> tracksLinks()  {
        return tracks().stream()
                .map(Track::getLink)
                .collect(Collectors.toList());
    }

    public List<String> myPlaylists() {
        return $(PLAYLISTS_NAVIGATION_BAR).shouldBe(visible.because("Не найдена навигационная панель")).$$(TEXT_CONTAINER).stream()
                .map(SelenideElement::text)
                .collect(Collectors.toList());
    }


    public Track createPlaylistForFirstTrack(String playlistName) {
        SelenideElement track = $(TRACKS_LIST).shouldBe(visible.because("Не найден список треков")).$(TRACK).shouldBe(visible.because("Не найден трек"));

        SelenideElement trackTitle = track.$(TRACK_TITLE).shouldBe(visible.because("Не найден заголовок трека"));

        Track trackModel = new Track(trackTitle.shouldHave(attribute("href").because("Не найдена ссылка на трек")).getAttribute("href"), trackTitle.text());

        SelenideElement trackActions  = track.hover().$(TRACK_ACTIONS).shouldBe(visible.because("Не найдена кнопка действий трека")).hover();

        trackActions.$(TRACK_COLLECTIONS_ACTIONS).shouldBe(visible.because("Не найдено меню действий для трека")).click();
        trackActions.$(CREATE_PLAYLIST).shouldBe(visible.because("Не найдена кнопка создания плейлиста")).click();
        trackActions.$(CREATE_PLAYLIST_FIELD).shouldBe(visible.because("Не найдено поле для создания создания плейлиста")).sendKeys(playlistName);
        trackActions.$(CREATE_PLAYLIST_BUTTON).shouldBe(visible.because("Не найдена кнопка для подтверждения создания плейлиста")).click();

        return trackModel;
    }

    public int myTracksCounter() {
        return Integer.parseInt(
                $(MY_TRACKS_COUNTER)
                        .shouldBe(visible.because("Не найден счётчик добавленных треков"))
                        .text()
        );
    }

    @Override
    protected void isLoaded() throws Error {
        $(PLAYER_CONTROLS).shouldBe(visible.because("Плеер не загрузился"));
    }
}
