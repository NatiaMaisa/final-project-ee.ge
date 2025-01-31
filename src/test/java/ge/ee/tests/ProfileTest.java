package ge.ee.tests;

import ge.ee.BaseTest;
import ge.ee.pages.LandingPage;
import ge.ee.pages.LoginPage;
import ge.ee.pages.ProfilePage;
import ge.ee.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BaseTest {

    @Test
    public void testLogOut(){
        LandingPage landingPage = new LandingPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        landingPage.acceptCookies();
        landingPage.clickOnLogInButton(); // შესვლა

        String email = ConfigReader.read("email");
        String password = ConfigReader.read("password");
        loginPage.login(email, password);  // ავტორიზაცია ვალიდური მეილით და პაროლით

        profilePage.openProfileSection(); // პროფილზე გადასვლა
        profilePage.clickLogOutButton(); // პროფილიდან გამოსვლა

        // ვამოწმებთ მიმდინარე ბმული ემთხვევა თუ არა მოსალოდნელს
        String expectedUrl = "https://ee.ge/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "URL არ ემთხვევა, ანგარიშიდან ვერ გამოხვედი");

        // ვამოწმებთ რომ "ჩემი პროფილი" - ნაცვლას ღილაკზე ტექსტი არის "შესვლა"
        String expectedText = "შესვლა";
        String actualText = landingPage.getLoginButtonText();
        Assert.assertEquals(actualText, expectedText, "ღილაკზე ტექსტი არ ემთხვევა, ანგარიშიდან ვერ გამოხვედი");

    }
}
