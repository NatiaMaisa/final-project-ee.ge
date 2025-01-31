package ge.ee.tests;

import ge.ee.BaseTest;
import ge.ee.pages.LandingPage;
import ge.ee.pages.LoginPage;
import ge.ee.pages.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BaseTest {

    @Test
    public void testLogOut(){

        LandingPage landingPage = new LandingPage(driver);
        landingPage.acceptCookies();
        landingPage.clickOnLogInButton();

        LoginPage loginPage = new LoginPage(driver);
        // ვლოგინდები ვალიდური მონაცემებით
        loginPage.login("natiimaisuradze@gmail.com", "Natia123");

        ProfilePage profilePage = new ProfilePage(driver);

        profilePage.openProfileSection();

        profilePage.clickLogOutButton();

        String expectedUrl = "https://ee.ge/";
        String actualUrl = driver.getCurrentUrl();
        // ვამოწმებთ მიმდინარე ბმული ემთხვევა თუ არა მოსალოდნელს
        Assert.assertEquals(actualUrl, expectedUrl, "URL არ ემთხვევა, ანგარიშიდან ვერ გამოხვედი");

        // ვამოწმებთ რომ "ჩემი პროფილი" - ნაცვლას ღილაკზე ტექსტი არის "შესვლა"
        String expectedText = "შესვლა";
        String actualText = landingPage.getLoginButtonText();
        Assert.assertEquals(actualText, expectedText, "ღილაკზე ტექსტი არ ემთხვევა, ანგარიშიდან ვერ გამოხვედი");

    }
}
