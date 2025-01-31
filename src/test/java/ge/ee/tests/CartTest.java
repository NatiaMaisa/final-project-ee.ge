package ge.ee.tests;

import ge.ee.BaseTest;
import ge.ee.pages.CartPage;
import ge.ee.pages.LandingPage;
import ge.ee.pages.LoginPage;
import ge.ee.pages.ProductPage;
import ge.ee.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test
    public void testCartContainsAddedProduct(){
        LandingPage landingPage = new LandingPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        landingPage.acceptCookies();
        landingPage.clickOnLogInButton(); // შესვლის ღილაკზე კლილი

        String email = ConfigReader.read("email");
        String password = ConfigReader.read("password");
        loginPage.login(email, password);  // ავტორიზაცია ვალიდური მეილით და პაროლით

        productPage.selectingMobilePhonesSection(); // ვირჩევ მობილური ტელეფონების სექციას
        productPage.selectingGoogleMobiles(); // ვირჩევ გუგლისის მობილურებს
        productPage.selectingColorBlue(); // ვირჩევ ცისფერს
        productPage.addToCart(); // ვამატებ კალათაში
        cartPage.clickToCartButton(); //კალათაში გადასვლა

        String expectedCartProduct = "Google Pixel 8A 5G 8/128GB Bay";
        String actualCartProduct = cartPage.getCartProductText();
        // ვამოწმებ არსებულ და მოსალოდნელ პროდუქტს
        Assert.assertEquals(actualCartProduct, expectedCartProduct, "კალათაში არსებული პროდუქტი არ ემთხვევა მოსალოდნელს");
    }

    @Test
    public void testRemoveFromCardFunctionality(){
        LandingPage landingPage = new LandingPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        landingPage.acceptCookies();
        landingPage.clickOnLogInButton(); // შესვლის ღილაკზე კლილი

        String email = ConfigReader.read("email");
        String password = ConfigReader.read("password");
        loginPage.login(email, password);  // ავტორიზაცია ვალიდური მეილით და პაროლით

        productPage.selectingMobilePhonesSection(); // ვირჩევ მობილური ტელეფონების სექციას
        productPage.selectingGoogleMobiles(); // ვირჩევ გუგლისის მობილურებს
        productPage.selectingColorBlue(); // ვირჩევ ცისფერს
        productPage.addToCart(); // ვამატებ კალათაში

        cartPage.clickToCartButton(); // გადავდივარ კალათაში
        cartPage.setRemoveFromCartButton(); // ვშლი კალათიდან პროდუქტს

        String expectedTextInCart = "კალათა ცარიელია";
        String actualTextInCart = cartPage.getEmptyCartText();
        // ვამოწმებ კალათის ტექტს რომ ნამდვილად ცარიელია
        Assert.assertEquals(actualTextInCart, expectedTextInCart, "კალათა არ არის ცარიელი");
    }
}
