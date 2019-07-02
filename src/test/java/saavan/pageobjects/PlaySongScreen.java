package saavan.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PlaySongScreen extends StandardScreen {

    private By title = By.xpath("//android.widget.TextView[contains(@text,'Download')]");
    private By play = By.xpath("//android.widget.TextView[starts-with(@text,'Playlist')]/../following-sibling::android.widget.LinearLayout/android.widget.RelativeLayout");
    private By songName = By.xpath("//android.view.ViewGroup[contains(@index,'1')]/android.widget.TextView");

    public PlaySongScreen(){
        PageFactory.initElements(driver,this);
        waitForElementToLoad(title);
    }

    public Boolean isPlaySongScreenDisplayed(){
        return isElementPresent(title);
    }

    public void clickPlayButton(){
        wait.until(ExpectedConditions.presenceOfElementLocated(play));
        findElement(play).click();
    }

    public Boolean isSongPlaying(){
        String song = findElement(songName).getText();
        System.out.println(song);
        //Verifying that first song displayed in the list is now playing in the bottom
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.LinearLayout/android.widget.TextView[contains(@text,'"+song+"')]")));
        return isElementPresent(By.xpath("//android.widget.LinearLayout/android.widget.TextView[contains(@text,'"+song+"')]"));
    }
}
