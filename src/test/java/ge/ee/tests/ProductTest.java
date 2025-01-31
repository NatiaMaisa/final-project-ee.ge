package ge.ee.tests;

import ge.ee.BaseTest;
import ge.ee.pages.LandingPage;
import ge.ee.pages.LoginPage;
import ge.ee.pages.ProductPage;
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
        loginPage.login("natiimaisuradze@gmail.com", "Natia123");

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
        landingPage.acceptCookies();
        landingPage.clickOnLogInButton();

        LoginPage loginPage = new LoginPage(driver);
        // ვლოგინდები ვალიდური მონაცემებით
        loginPage.login("natiimaisuradze@gmail.com", "Natia123");

        ProductPage productPage = new ProductPage(driver);

        // ვირჩევ მობილური ტელეფონების სექციას
        productPage.selectingMobilePhonesSection();
        // ვირჩევ გუგლისის მობილურებს
        productPage.selectingGoogleMobiles();
        // ვირჩევ ცისფერს
        productPage.selectingColorBlue();
        // ვამატებ კალათაში
        productPage.addToCart();

         // ვამოწმებ რომ კალათის აიქონზე გაჩნდა 1, რაც იმას ნიშნავს რომ კალათაში არის 1 პროდუქტი დამატებული
        int expectedQuantity = 1;
        int actualQuantity = productPage.getCardCount();
        Assert.assertEquals(actualQuantity, expectedQuantity, "კალათაში პროდუქტის რაოდენობა არ ემთხვევა");

    }
}
