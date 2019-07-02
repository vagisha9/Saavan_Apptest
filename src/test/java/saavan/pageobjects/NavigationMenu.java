package saavan.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NavigationMenu extends StandardScreen {
    private By menu = By.xpath("//android.view.ViewGroup[contains(@index,'1')]/android.widget.ImageButton");

    public void clickMenuBar(){
        findElement(menu).click();
    }

    public HomeScreen clickMenuItem(String menuItem){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@text,'"+menuItem+"')]")));
        findElement(By.xpath("//android.widget.TextView[contains(@text,'"+menuItem+"')]")).click();
          // Function to be extended to include all menu items , then use switch case
        return new HomeScreen();
    }
}
