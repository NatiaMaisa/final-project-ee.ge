package ge.ee;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.internal.Utils;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        PageFactory.initElements(driver, this);
    }

    public void enterText(WebElement locator, String text) {
        waitForElementToBeClickable(locator); // ამ მეთოდის ლოგი აწყობილია
        locator.sendKeys(text);
        Utils.log("მოვძებნე ელემენტი: [ " + locator + " ] გადავეცი ტექსტი: " + text);
    }

    public void waitForElementToBeClickable(WebElement locator) {
        Utils.log("ველოდები რომ ელემენტი [ " + locator + " ] გახდეს დაკლიკებადი");
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        Utils.log("ვიპოვე ელემენტი [ " + locator + " ]");
    }

    public void clickToElement(WebElement locator) {
        locator.click();
        Utils.log("დავაკლიკე [ " + locator + " ] -ზე");
    }

    public void clickToElementWithWait(WebElement locator) {
        waitForElementToBeClickable(locator); //ამ მეთოდის ლოგი ასწობილია
        clickToElement(locator); // ამ მეთოდის ლოგი აწყობილია
    }

    public String getElementTextWithWait(WebElement locator) {
        waitForElementToBeVisible(locator); //ამ მეთოდის ლოგი აწყობილია
        return locator.getText().trim();
    }

    public void waitForElementToBeVisible(WebElement locator) {
        Utils.log("ველოდები რომ ელემენტი [ " + locator + " ] გამოჩნდეს");
        wait.until(ExpectedConditions.visibilityOf(locator));
        Utils.log("ელემენტი [ " + locator + " ] გამოჩნდა");
    }

    public void clearFieldWithWait(WebElement locator) {
        waitForElementToBeVisible(locator); //ამ მეთოდის ლოგი აწყობილია
        locator.clear();
        Utils.log("ელემენტი [ " + locator + " ] გავასუფთავეთ");

    }

    public String getCssValue(WebElement locator, String propertyName) {
        waitForElementToBeVisible(locator);
        return locator.getCssValue(propertyName);
    }

    public String getMatchingTextFromElement(WebElement locator, String query) {
        // ვიღებ ელემენტის სრულ ტექტს
        String fullText = getElementTextWithWait(locator);
        Utils.log("ელემენტიდან [ " + locator + " ] ამოვიღე ტექსტი");

        // ვამოწმებ სრული ტექსტი თუ შეიცავს ჩემს საძიებო სიტყვას და ვაბრუნებ საძიებო სიტყვას
        if (fullText != null && fullText.contains(query)) {
            return query;
        }
        return ""; // ვაბრუნებ სიცარიელეს თუ არ შეიცავს საძიებო სიტყვას
    }

    public List<String> getAllElementTexts(List<WebElement> locators) {
        return locators.stream()
                .map(WebElement::getText) // ვიღებ ტექსტს თითოეული ელემენტიდან
                .collect(Collectors.toList()); // ვაგროვებ შედეგებს ლისტში
    }

    // პროდუქტის ფასების ამოღება და სორტირება
    public List<Double> getSortedPrices(List<WebElement> priceElements) {
        // გადავიყვანოთ ელემენტის ტექსტი დაბლებში და შევაგროვოთ ლისტად
        return priceElements.stream()
                .map(price -> price.getText().replaceAll("[^0-9,]", ""))
                .map(Double::parseDouble)// გადავიყვანოთ დაბლებში
                .sorted() // დავალაგოთ ზრდადობით
                .collect(Collectors.toList()); // სორტირებული სია

    }

    // შევამოწმოთ რომ სია სწორადაა დალაგებული
    public boolean isSortedAscending(List<Double> prices) {
        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices); // დავალაგოთ ზრდადობით

        return prices.equals(sortedPrices); // შევადაროთ
    }

    // Generic method to get integer value from an element (for cart quantity)
    public int getElementIntegerValue(WebElement locator) {
        try {
            String text = getElementTextWithWait(locator);
            return Integer.parseInt(text);
        } catch (Exception e) {
            return 0; // Default to 0 if the element is not found or empty
        }
    }

    // ელემეტამდე ჩამოსქროლვა
    public void scrollToElement(WebElement locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", locator);

    }

    // დალოდება სანამ loading ელემეტი გაქრება
    public void waitForLoadingToDisappear(WebElement locator) {
            wait.until(ExpectedConditions.invisibilityOf(locator));

    }

    // JavaScript Click for cases where normal click is intercepted
    protected void javascriptClick(WebElement locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", locator);
    }
    protected void scrollToElementAndClick(WebElement locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", locator);
        jsExecutor.executeScript("arguments[0].click();", locator);
    }
}




