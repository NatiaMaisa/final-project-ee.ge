package ge.ee.pages;

import ge.ee.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;


public class SearchPage extends BasePage {

    // კონსტრუქტორი
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "(//input[@id='search_list'])")
    WebElement searchField;

    @FindBy(xpath = "(//i[@class='fa fa-search'])")
    WebElement searchButton;

    @FindBy(xpath = "(//span[@class='title d-xs-none'][normalize-space()='Apple iPhone 13 128GB Midnight'])")
    WebElement searchResultTitle;

    @FindBy(xpath = "//span[contains(@class, 'title d-xs-none')]")
    List<WebElement> allSearchResultTitles;

    @FindBy(xpath = "//div[@id='loading']")
    WebElement loadingElement;


    public void searchProduct(String query) {
        waitForLoadingToDisappear(loadingElement);
        clearFieldWithWait(searchField);
        enterText(searchField, query);
        clickToElement(searchButton);
    }

    public String getSearchedProductText(String query) {
        return getMatchingTextFromElement(searchResultTitle, query);
    }

    public List<String> getAllSearchedProductTexts() {
        return getAllElementTexts(allSearchResultTitles);
    }


}