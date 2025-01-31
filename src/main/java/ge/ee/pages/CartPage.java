package ge.ee.pages;

import ge.ee.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {
    // კონსტრუქტორი
    public CartPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//div[@class='buttons']//button[@class='btn-cart']")
    WebElement cartButton;

    @FindBy(xpath = "//h3[@class='cart-item-title']//span[@class='cursor_pointer']")
    WebElement googlePhoneInCart;

    @FindBy(xpath = "//button[@class='btn btn-lightgrey remove-item']")
    WebElement removeFromCartButton;

    @FindBy(xpath = "//h2[contains(text(),'კალათა ცარიელია')]")
    WebElement emptyCartText;

    public void clickToCartButton(){
        clickToElementWithWait(cartButton);
    }

    public String getCartProductText(){
        return getElementTextWithWait(googlePhoneInCart);
    }

    public void setRemoveFromCartButton(){
        clickToElementWithWait(removeFromCartButton);
    }

    public String getEmptyCartText(){
        return getElementTextWithWait(emptyCartText);
    }

}
