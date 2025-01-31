package ge.ee.pages;

import ge.ee.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    // კონსტრუქტორი
    public LoginPage(WebDriver driver){
        super(driver);
    }


    @FindBy(xpath = "(//input[@placeholder='ელ. ფოსტის მისამართი'])")
    WebElement emailField;

    @FindBy(xpath = "(//input[@placeholder='პაროლი'])")
    WebElement passwordField;

    @FindBy(xpath = "(//button[contains(text(),'ავტორიზაცია')])")
    WebElement authButton;

    @FindBy(xpath = "(//div[@class='react-toast-notifications__toast__content css-1ad3zal'])")
    WebElement errorMessage;



    public void login (String email, String password){
        enterText(emailField, email);
        enterText(passwordField, password);
        clickToElementWithWait(authButton);
    }

    public String getErrorLoginMassageText(){
        return getElementTextWithWait(errorMessage);
    }

    public String getErrorNotificationColor() {
        return getCssValue(errorMessage, "color");
    }

}
