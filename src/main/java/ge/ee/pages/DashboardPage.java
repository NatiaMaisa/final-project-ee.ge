package ge.ee.pages;

import ge.ee.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {

    // კონსტრუქტორი
    public DashboardPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//button[contains(text(),'ჩემი პროფილი')]")
    WebElement userProfileText;

    @FindBy(xpath = "(//div[@class='react-toast-notifications__toast__content css-1ad3zal'])")
    WebElement successLoginMessageText;


    public String getUserProfileText(){
        return getElementTextWithWait(userProfileText);
    }

    public String getSuccessLoginMassageText(){
        return getElementTextWithWait(successLoginMessageText);
    }


}