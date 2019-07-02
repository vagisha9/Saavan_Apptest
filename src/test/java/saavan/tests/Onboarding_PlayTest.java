package saavan.tests;

import org.testng.annotations.*;
import saavan.common.BaseDriver;
import saavan.pageobjects.HomeScreen;
import saavan.pageobjects.LandingScreen;
import saavan.pageobjects.NavigationMenu;
import saavan.pageobjects.PlaySongScreen;
import java.net.MalformedURLException;
import static org.testng.Assert.*;

public class Onboarding_PlayTest {
    @BeforeTest
    public void setUp(){
        try {
            BaseDriver.setup();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void onboardingTest(){
        LandingScreen landing = new LandingScreen();
        assertTrue(landing.isLandingScreenDisplayed(),"Home screen is not displayed");
        landing.selectLanguage("Hindi");
        landing.selectOtherLanguage("Assamese");
        assertTrue(landing.isSelectedLanguageCountDisplayed(1),"Selected languages count is not correct");
        HomeScreen home = landing.clickDone();
        home.closeAds();
        assertTrue(home.isHomeScreenDisplayed(),"Home screen is not displayed");
    }

    @Test
    public void playSongTest(){
        HomeScreen home = new HomeScreen();
        home.scrollToHomeScreenEnd("Editorial Picks");
        assertTrue(home.isEditorialSectionDisplayed(),"Editorial Section is not displayed");
        home.scrollImages();
        PlaySongScreen playSong = home.selectLastSong();
        assertTrue(playSong.isPlaySongScreenDisplayed(),"Play Song screen is not displayed");
        playSong.clickPlayButton();
        assertTrue(playSong.isSongPlaying(),"Selected song is not playing");
        NavigationMenu menu = new NavigationMenu();
        menu.clickMenuBar();
        home = menu.clickMenuItem("Home");
        assertTrue(home.isHomeScreenDisplayed(),"Home screen is not displayed");
    }

    @AfterTest
    public void quit(){
        BaseDriver.quitBrowser();
    }
}
