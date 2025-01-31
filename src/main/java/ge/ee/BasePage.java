package ge.ee;

import ge.ee.utils.ConfigReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ge.ee.utils.Utils;
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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ConfigReader.read("wait"))));
        PageFactory.initElements(driver, this);
    }

    public void enterText(WebElement locator, String text) {
        waitForElementToBeClickable(locator); // ამ მეთოდის ლოგი აწყობილია
        locator.sendKeys(text);
        Utils.log("შევიყვანეთ ინფორმაცია [ " + text + " ]  ელემენტში: " + locator);
    }

    public void waitForElementToBeClickable(WebElement locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        Utils.log("ველოდები რომ ელემენტი [ " + locator + " ] გახდეს დაკლიკებადი");
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
        Utils.log("ვიღებ ელემენტიდან [ " + locator + " ] ტექსტს");
        return locator.getText().trim();

    }

    public void waitForElementToBeVisible(WebElement locator) {
        wait.until(ExpectedConditions.visibilityOf(locator));
        Utils.log("ველოდები რომ ელემენტი [ " + locator + " ] გამოჩნდეს");
    }

    public void clearFieldWithWait(WebElement locator) {
        waitForElementToBeVisible(locator); //ამ მეთოდის ლოგი აწყობილია
        locator.clear();
        Utils.log("ელემენტი [ " + locator + " ] გავასუფთავეთ");


    }

    public String getCssValue(WebElement locator, String propertyName) {
        waitForElementToBeVisible(locator); //ამ მეთოდის ლოგი აწყობილია
        Utils.log("ვიღებ CSS მნიშვნელობას [ " + propertyName + " ]  ელემენტიდან: " + locator);
        return locator.getCssValue(propertyName);
    }

    public String getMatchingTextFromElement(WebElement locator, String query) {
        Utils.log("ელემენტიდან [ " + locator + " ] ამოვიღე ტექსტი");
        // ვიღებ ელემენტის სრულ ტექტს
        String fullText = getElementTextWithWait(locator);

        // ვამოწმებ სრული ტექსტი თუ შეიცავს ჩემს საძიებო სიტყვას და ვაბრუნებ საძიებო სიტყვას
        if (fullText != null && fullText.contains(query)) {
            Utils.log("საძიებო სიტყვა [ " + query + " ]  არსებობს ელემენტში: " + locator);
            return query;
        }
        return ""; // ვაბრუნებ სიცარიელეს თუ არ შეიცავს საძიებო სიტყვას
    }


    public List<String> getAllElementTexts(List<WebElement> locators) {
        Utils.log("ვიღებთ ყველა ელემენტიდან [ " + locators + " ] ტექსტებს");
        return locators.stream()
                .map(WebElement::getText) // ვიღებ ტექსტს თითოეული ელემენტიდან
                .collect(Collectors.toList()); // ვაგროვებ შედეგებს ლისტში
    }

    // პროდუქტის ფასების ამოღება და სორტირება
    public List<Double> getSortedPrices(List<WebElement> priceElements) {
        Utils.log("ვიღებთ სორტირებულ ფასებს [ " + priceElements + " ] -დან");
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
        Utils.log("ზრდადობით დალაგებული ფასები [ " + prices + " ] -დან");
        return prices.equals(sortedPrices); // შევადაროთ
    }

    // ამოვიღოთ integer მნიშვნელობა ელემეტიდან
    public int getElementIntegerValue(WebElement locator) {
        try {
            String text = getElementTextWithWait(locator);
            Utils.log("ამოვიღე Integer მნიშვნელობა [ " + locator + " ] -დან");
            return Integer.parseInt(text);
        } catch (Exception e) {
            return 0;
        }
    }

    // ელემეტამდე ჩამოსქროლვა
    public void scrollToElement(WebElement locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", locator);
        Utils.log("ჩავსქროლე [ " + locator + " ] -მდე");
    }

    // დალოდება სანამ loading ელემეტი გაქრება
    public void waitForLoadingToDisappear(WebElement locator) {
            wait.until(ExpectedConditions.invisibilityOf(locator));
        Utils.log("ველოდები რომ Loading [ " + locator + " ] გაქრეს");
    }

    // javascript კლიკი
    protected void javascriptClick(WebElement locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", locator);
    }

    // javascript სქროლი და კლიკი
    protected void scrollToElementAndClick(WebElement locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", locator);
        jsExecutor.executeScript("arguments[0].click();", locator);
        Utils.log("ჩამოსქროლე და დავაკლიკე [ " + locator + " ] -ზე");
    }

    // დალოდება კონკრეტული დროით
    public void sleep(long milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
        Utils.log("ველოდებით [ " + milliseconds + " ] მილიწამით");
    }
}




