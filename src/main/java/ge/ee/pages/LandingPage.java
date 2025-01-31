package ge.ee.pages;

import ge.ee.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends BasePage {

    // კონსტრუქტორი
    public LandingPage(WebDriver driver){
        super(driver);
    }
    @FindBy(xpath = "(//button[contains(text(),'დათანხმება')])")
    WebElement acceptCookieButton;

    @FindBy(xpath = "//button[contains(text(),'შესვლა')]")
    WebElement logInButton;


    public void acceptCookies(){
        waitForElementToBeVisible(acceptCookieButton);
        clickToElementWithWait(acceptCookieButton);
    }

    public void clickOnLogInButton(){
        waitForElementToBeVisible(logInButton);
        clickToElement(logInButton);
    }

    public String getLoginButtonText(){
        return getElementTextWithWait(logInButton);
    }


}
