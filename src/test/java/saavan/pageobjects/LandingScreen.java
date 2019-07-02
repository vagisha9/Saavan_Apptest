package saavan.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class LandingScreen extends StandardScreen {

    private By title = By.xpath("//android.widget.TextView[contains(@text,'What kinds of music do you like?')]");
    private By done = By.xpath("//android.widget.TextView[contains(@text,'Done')]");


    public LandingScreen(){
        PageFactory.initElements(driver,this);
        waitForElementToLoad(title);
    }

    public Boolean isLandingScreenDisplayed(){
        return isElementPresent(title);
    }

    private By getLanguageElement(String language){
        return By.xpath("//android.widget.TextView[contains(@text,'"+language+"')]");
    }

    public void selectLanguage(String language){
        //System.out.println(getLanguageElement(language).isSelected());
        findElement(getLanguageElement(language)).click();
    }

    public Boolean isSelectedLanguageCountDisplayed(int noOfLanguages){
       return isElementPresent(By.xpath("//android.widget.TextView[contains(@text,'"+noOfLanguages+" Selected')]"));
    }

    public HomeScreen clickDone(){
        findElement(done).click();
        return new HomeScreen();
    }

    private Boolean isLanguageOptionDisplayed(String language){
       return isElementPresent(By.xpath("//android.widget.TextView[contains(@text,'"+language+"')]"));
    }

    public void selectOtherLanguage(String language){
            int count = 0;
            boolean flag = false;
            while (count<=2){
                if(isLanguageOptionDisplayed(language)){
                    findElement(getLanguageElement(language)).click();
                    flag=true;
                    break;
                }else{
                    swipeUp();
                    count++;
                }
            }
            if(!flag){
                System.out.println(language+" option is not displayed on the landing screen");
            }
    }
}
