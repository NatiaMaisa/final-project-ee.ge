package ge.ee.tests;

import ge.ee.BaseTest;
import ge.ee.pages.LandingPage;
import ge.ee.pages.LoginPage;
import ge.ee.pages.SearchPage;
import ge.ee.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class SearchTest extends BaseTest {

    // აქ მხოლოდ პირველ პროდუქტს ვამოწმებ, ვიცი რომ ყველა უნდა შემოწმდეს უბრალოდ პირველად ეს ტესტი დავწერე და წაშლა დამენანა
    @Test
    public void testSearchFunctionalityForFirstResult() {
        LandingPage landingPage = new LandingPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        SearchPage searchPage = new SearchPage(driver);

        landingPage.acceptCookies();
        landingPage.clickOnLogInButton();
        String email = ConfigReader.read("email");
        String password = ConfigReader.read("password");
        loginPage.login(email, password);  // ავტორიზაცია ვალიდური მეილით და პაროლით


        String queryItem = "iPhone"; // საძიებო პროდუქტის გამოცხადება
        searchPage.searchProduct(queryItem);  // მოვძებნოთ საძიებო პროდუქტი
        String actualSearchProductText = searchPage.getSearchedProductText(queryItem); // ვიღებ ძიების შემდეგ მიღებულ პირველი შედეგის ტექსტს

        // შევამოწმოთ რომ მოსალოდნელი და მიღებული შედეგი ემთხვევა
        Assert.assertEquals(actualSearchProductText, queryItem, "პროდუქტის დასახელება არ ემთხვევა");
    }


    @Test
    public void testSearchFunctionalityForAllResults(){
        LandingPage landingPage = new LandingPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        SearchPage searchPage = new SearchPage(driver);

        landingPage.acceptCookies();
        landingPage.clickOnLogInButton();

        String email = ConfigReader.read("email");
        String password = ConfigReader.read("password");
        loginPage.login(email, password);  // ავტორიზაცია ვალიდური მეილით და პაროლით

        searchPage.searchProduct("iPhone"); // ვეძებთ სიტყვით "iPhone"

        String expectedSearchProductText = "iPhone";
        // წამოვიღოთ ყველა ძებნის შედეგის ტექსტი
        List<String> allSearchResultText = searchPage.getAllSearchedProductTexts();
        // ვამოწმებთ ყველა შედეგს
        for (String resultText : allSearchResultText){
            Assert.assertTrue(resultText.contains(expectedSearchProductText), "პროდუქტის დასახელება არ შეიცავს საძიებო ტექსტს: " + resultText);
        }
    }
}
