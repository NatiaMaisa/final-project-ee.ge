package ge.ee.tests;

import ge.ee.BaseTest;
import ge.ee.pages.CartPage;
import ge.ee.pages.LandingPage;
import ge.ee.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test
    public void testCartContainsAddedProduct(){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.acceptCookies();
        landingPage.clickOnLogInButton();

        LoginPage loginPage = new LoginPage(driver);
        // ვალიდური ავტორიზაცია
        loginPage.login("natiimaisuradze@gmail.com", "Natia123");

        CartPage cartPage = new CartPage(driver);
        //კალათაში გადასვლა
        cartPage.clickToCartButton();

        String expectedCartProduct = "Google Pixel 8A 5G 8/128GB Bay";
        String actualCartProduct = cartPage.getCartProductText();
        // ვამოწმებ არსებულ და მოსალოდნელ პროდუქტს
        Assert.assertEquals(actualCartProduct, expectedCartProduct, "კალათაში არსებული პროდუქტი არ ემთხვევა მოსალოდნელს");
    }

    @Test
    public void testRemoveFromCardFunctionality(){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.acceptCookies();
        landingPage.clickOnLogInButton();

        LoginPage loginPage = new LoginPage(driver);
        // ვალიდური ავტორიზაცია
        loginPage.login("natiimaisuradze@gmail.com", "Natia123");

        CartPage cartPage = new CartPage(driver);

        // გადავდივარ კალათაში
        cartPage.clickToCartButton();
        // ვშლი კალათიდან პროდუქტს
        cartPage.setRemoveFromCartButton();

        String expectedTextInCart = "კალათა ცარიელია";
        String actualTextInCart = cartPage.getEmptyCartText();

        Assert.assertEquals(actualTextInCart, expectedTextInCart, "კალათა არ არის ცარიელი");
    }
}
