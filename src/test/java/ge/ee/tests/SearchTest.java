package ge.ee.tests;

import ge.ee.BaseTest;
import ge.ee.pages.LandingPage;
import ge.ee.pages.LoginPage;
import ge.ee.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class SearchTest extends BaseTest {



    // აქ მხოლოდ პირველ პროდუქტს ვამოწმებ, ვიცი რომ ყველა უნდა შემოწმდეს უბრალოდ პირველად ეს ტესტი დავწერე და წაშლა დამენანა
    @Test
    public void testSearchFunctionalityForFirstResult() {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.acceptCookies();
        landingPage.clickOnLogInButton();

        LoginPage loginPage = new LoginPage(driver);
        //ვლოგინდები ვალიდური მონაცემებით
        loginPage.login("natiimaisuradze@gmail.com", "Natia123");

        SearchPage searchPage = new SearchPage(driver);
        String queryItem = "iPhone"; // საძიებო პროდუქტის გამოცხადება

        // მოვძებნოთ საძიებო პროდუქტი
        searchPage.searchProduct(queryItem);
        //ვიღებ ძიების შემდეგ მიღებულ პირველი შედეგის ტექსტს
        String actualSearchProductText = searchPage.getSearchedProductText(queryItem);

        // შევამოწმოთ რომ მოსალოდნელი და მიღებული შედეგი ემთხვევა
        Assert.assertEquals(actualSearchProductText, queryItem, "პროდუქტის დასახელება არ ემთხვევა");
    }


    @Test
    public void testSearchFunctionalityForAllResults(){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.acceptCookies();
        landingPage.clickOnLogInButton();

        LoginPage loginPage = new LoginPage(driver);
        //ვლოგინდები ვალიდური მონაცემებით
        loginPage.login("natiimaisuradze@gmail.com", "Natia123");

        SearchPage searchPage = new SearchPage(driver);
        // ვეძებთ სიტყვით "iPhone"
        searchPage.searchProduct("iPhone");

        String expectedSearchProductText = "iPhone";
        // წამოვიღოთ ყველა ძებნის შედეგის ტექსტი
        List<String> allSearchResultText = searchPage.getAllSearchedProductTexts();

        // ვამოწმებთ ყველა შედეგს
        for (String resultText : allSearchResultText){
            Assert.assertTrue(resultText.contains(expectedSearchProductText), "პროდუქტის დასახელება არ შეიცავს საძიებო ტექსტს: " + resultText);
        }
    }
}
