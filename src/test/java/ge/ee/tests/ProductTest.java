package ge.ee.tests;

import ge.ee.BaseTest;
import ge.ee.pages.LandingPage;
import ge.ee.pages.LoginPage;
import ge.ee.pages.ProductPage;
import ge.ee.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {

    @Test
    public void testPriceSortingAsc(){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.acceptCookies();
        landingPage.clickOnLogInButton();

        LoginPage loginPage = new LoginPage(driver);
        // ვლოგინდები ვალიდური მონაცემებით
        String email = ConfigReader.read("email");
        String password = ConfigReader.read("password");
        loginPage.login(email, password);  // ავტორიზაცია ვალიდური მეილით და პაროლით


        ProductPage productPage = new ProductPage(driver);

        // ვირჩევ მობილური ტელეფონების სექციას
        productPage.selectingMobilePhonesSection();
        // ვაკეთებ სორტირებას ფასის ზრდადობით
        productPage.sortingPhonesPriceAsc();

        // ვამოწმებ რომ ფასი ზრდადობით არის დალაგებული
        Assert.assertTrue(productPage.validateSorting(), "პროდუქტის ფასი არ არის ზრდადობით დალაგებული");
    }

    @Test
    public void testProductSelection(){
        LandingPage landingPage = new LandingPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);

        landingPage.acceptCookies();
        landingPage.clickOnLogInButton();

        String email = ConfigReader.read("email");
        String password = ConfigReader.read("password");
        loginPage.login(email, password);  // ავტორიზაცია ვალიდური მეილით და პაროლით

        productPage.selectingMobilePhonesSection(); // ვირჩევ მობილური ტელეფონების სექციას
        productPage.selectingGoogleMobiles(); // ვირჩევ გუგლისის მობილურებს
        productPage.selectingColorBlue(); // ვირჩევ ცისფერს
        productPage.addToCart(); // ვამატებ კალათაში
        productPage.waitForCartToUpdate(); // ველოდები კალათის განახლებას

        // ვამოწმებ რომ კალათის აიქონზე გაჩნდა 1, რაც იმას ნიშნავს რომ კალათაში არის 1 პროდუქტი დამატებული
        int expectedQuantity = 1;
        int actualQuantity = productPage.getCardCount();
        Assert.assertEquals(actualQuantity, expectedQuantity, "კალათაში პროდუქტის რაოდენობა არ ემთხვევა");
    }
}
