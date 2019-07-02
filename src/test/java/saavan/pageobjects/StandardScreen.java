package saavan.pageobjects;

import io.appium.java_client.MobileBy;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import saavan.common.BaseDriver;
import io.appium.java_client.TouchAction;
import java.util.concurrent.TimeUnit;

public class StandardScreen extends BaseDriver {

    protected WebDriverWait wait = new WebDriverWait(driver, 30);
    private TouchAction action = new TouchAction(driver);

    public StandardScreen(){
        waitForPageLoad();
    }

    private void waitForPageLoad() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    protected void waitForElementToLoad(By locatorKey) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locatorKey));
    }

    protected Boolean isElementPresent(By locatorKey) {
        try {
            driver.findElement(locatorKey);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    protected WebElement findElement(By element){
        return driver.findElement(element);
    }

    protected void swipeUp(){
        Dimension dimensions = driver.manage().window().getSize();
        double screenHeightStart = dimensions.getHeight() * 0.7;
        double screenHeightEnd = dimensions.getHeight() * 0.2;
        int scrollStart = (int)screenHeightStart;
        int scrollEnd = (int)screenHeightEnd;
        int screenWidth=(int)(dimensions.getWidth()*0.5);
        action.press(PointOption.point(screenWidth,scrollStart)).waitAction().moveTo(PointOption.point(screenWidth,scrollEnd)).release().perform();
    }

    protected void scrollTillEnd(){
        Dimension dimensions = driver.manage().window().getSize();
        double screenHeightStart = dimensions.getHeight() * 0.8;
        int scrollStart = (int)screenHeightStart;
        double screenHeightEnd = dimensions.getHeight()*0.1;
        int scrollEnd = (int)screenHeightEnd;
        int screenWidth=(int)(dimensions.getWidth()*0.5);
        action.press(PointOption.point(screenWidth,scrollStart)).waitAction().moveTo(PointOption.point(screenWidth,scrollEnd)).release().perform();
    }

    protected void scrollIntoView(String text){
        try {
            driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\""+text+"\"))"));
        }catch (org.openqa.selenium.NoSuchElementException e){
            System.out.println("Section $text is not present");
        }
    }

    protected void swipeItemToLeft(WebElement element){
        Dimension size = driver.manage().window().getSize();
        int startY = element.getLocation().getY() + element.getSize().getHeight() / 2;
        int startX = (int)(size.width * 0.9);
        int endX =  (int)(size.width * 0.1);
        action.press(PointOption.point(startX,startY)).waitAction().moveTo(PointOption.point(endX,startY)).release().perform();
    }
}
