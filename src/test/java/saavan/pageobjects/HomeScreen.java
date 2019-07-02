package saavan.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class HomeScreen extends StandardScreen {

    private By title = By.className("android.widget.ImageButton");
    private By closeAd = By.xpath("//android.widget.ImageButton[contains(@name,'Interstitial close button')]");
    private By editorialSection = By.xpath("//android.widget.TextView[contains(@text,'Editorial Picks')]");
    private By editorialImages = By.xpath("//android.widget.TextView[contains(@text,'Editorial Picks')]/following-sibling::android.support.v7.widget.RecyclerView/android.widget.LinearLayout");


    public HomeScreen(){
        PageFactory.initElements(driver,this);
        waitForElementToLoad(title);
    }

    public Boolean isHomeScreenDisplayed(){
        return isElementPresent(title);
    }

    public void closeAds(){
        if(isElementPresent(closeAd)){
            findElement(closeAd).click();
        }
    }

    public Boolean isEditorialSectionDisplayed(){
        return findElement(editorialSection).isDisplayed();
    }

    public void scrollToHomeScreenEnd(String section){
            int count = 0;
            boolean flag = false;
            while (count<=6){
                if(isElementPresent(By.xpath("//android.widget.TextView[contains(@text,'"+section+"')]/following-sibling::android.support.v7.widget.RecyclerView/android.widget.LinearLayout"))){
                    flag=true;
                    break;
                }else{
                    swipeUp();
                    count++;
                }
            }
            if(!flag){
                System.out.println(section+" is not displayed on the home screen");
            }
        }

        public void scrollImages(){
        int i=0;
        while (i<=3) {
            i += 1;
            swipeItemToLeft(driver.findElements(editorialImages).get(0));
        }
        }

        public PlaySongScreen selectLastSong(){
            int imagesSize = driver.findElements(editorialImages).size();
            driver.findElements(editorialImages).get(imagesSize-1).click();
            return new PlaySongScreen();
        }
}
