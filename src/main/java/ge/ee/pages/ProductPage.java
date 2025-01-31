package ge.ee.pages;

import ge.ee.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPage extends BasePage {
    // კონსტრუქტორი
    public ProductPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "(//span[contains(text(),'მობილური ტელეფონი და აქსესუარები')])")
    WebElement mobilePhonesSection;

    @FindBy(xpath = "//div[contains(@class,'col-3 mt-3 mb-3')]//div//a[contains(text(),'მობილური ტელეფონები')]")
    WebElement mobilePhonesSelect;

    @FindBy(xpath = "(//select[@id='sort'])")
    WebElement sortDropdown;

    @FindBy(xpath = "(//select[@id='sort']/option[@value='2'])")
    WebElement selectPriceAsc;

    @FindBy(xpath = "//span[contains(@class, 'product-price')]")
    List<WebElement> mobilePhonePrices;

    @FindBy(xpath = "(//label[normalize-space()='GOOGLE'])[1]")
    WebElement googleSelection;

    @FindBy(xpath = "(//label[@class='custom-control-label'][contains(text(),'ცისფერი')])[2]")
    WebElement colorBlueSelection;

    @FindBy(xpath = "//button[contains(@class, 'btn-darkred') and contains(@class, 'add_to_cart')]")
    WebElement addToCartButton;


    @FindBy(xpath = "//div[@class='buttons']//span[@class='cart-count']")
    WebElement cartQuantity;

    @FindBy(xpath = "//div[@id='loading']")
    WebElement loadingElement;





    public void selectingMobilePhonesSection(){
     waitForElementToBeVisible(mobilePhonesSection);
     clickToElementWithWait(mobilePhonesSection);
     waitForElementToBeVisible(mobilePhonesSelect);
     clickToElementWithWait(mobilePhonesSelect);
    }

    public void sortingPhonesPriceAsc(){
        waitForElementToBeVisible(sortDropdown);
        clickToElementWithWait(sortDropdown);
        clickToElementWithWait(selectPriceAsc);
    }

    public List<Double> getSortedMobilePhonePrices() {
        return getSortedPrices(mobilePhonePrices); // Get extracted prices
    }

    public boolean validateSorting() {
        List<Double> extractedPrices = getSortedMobilePhonePrices();
        return isSortedAscending(extractedPrices);
    }

    public void selectingGoogleMobiles(){
        waitForElementToBeVisible(googleSelection);
        clickToElementWithWait(googleSelection);
    }

    public void selectingColorBlue(){
        scrollToElement(colorBlueSelection);
        waitForElementToBeVisible(colorBlueSelection);
        clickToElementWithWait(colorBlueSelection);
    }

    public void addToCart(){
        waitForLoadingToDisappear(loadingElement);
        scrollToElementAndClick(addToCartButton);
    }

    public int getCardCount(){
        scrollToElement(cartQuantity);
        return getElementIntegerValue(cartQuantity);
    }

}


