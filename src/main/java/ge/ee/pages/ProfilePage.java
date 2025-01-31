package ge.ee.pages;

import ge.ee.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends BasePage {
    // კონსტრუქტორი
    public ProfilePage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//button[contains(text(),'ჩემი პროფილი')]")
    WebElement profileButton;

    @FindBy(xpath = "//button[contains(text(),'გამოსვლა')]")
    WebElement logOutButton;

    public void openProfileSection(){
        waitForElementToBeVisible(profileButton);
        clickToElementWithWait(profileButton);
    }

    public void clickLogOutButton(){
        waitForElementToBeVisible(logOutButton);
        clickToElementWithWait(logOutButton);
    }



}
