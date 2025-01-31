package ge.ee.tests;

import ge.ee.BaseTest;
import ge.ee.pages.DashboardPage;
import ge.ee.pages.LandingPage;
import ge.ee.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testValidLogin(){

        LandingPage landingPage = new LandingPage(driver);
        landingPage.acceptCookies();
        landingPage.clickOnLogInButton();

        LoginPage loginPage = new LoginPage(driver);

        // ავტორიზაცია ვალიდური მეილით და პაროლით
        loginPage.login("natiimaisuradze@gmail.com", "Natia123");

        // ვამოწმებ პროფილის ტექსტს
        DashboardPage dashboardPage = new DashboardPage(driver);
        String expectedProfileText = "ჩემი პროფილი";
        String actualProfileText = dashboardPage.getUserProfileText();
        Assert.assertEquals(actualProfileText, expectedProfileText, "პროფილის ტექსტი არ ემთხვევა");


        // ვალიდაციის მესიჯის შემოწმება
        String expectedSuccessNotification = "წარმატებული ავტორიზაცია";
        String actualNotification = dashboardPage.getSuccessLoginMassageText();
        Assert.assertEquals(actualNotification, expectedSuccessNotification, "ვალიდაციის მესიჯი არ ემთხვევა");

    }

    @Test
    public void testInvalidLogin(){

        LandingPage landingPage = new LandingPage(driver);
        landingPage.acceptCookies();
        landingPage.clickOnLogInButton();

        LoginPage loginPage = new LoginPage(driver);

        // ავტორიზაცია ვალიდური მეილით და არავალიდური პაროლით
        loginPage.login("natiimaisuradze@gmail.com", "Nati123");

        // ვამოწმებ ვალიდაციის შეტყობინებას
        String expectedErrorMessage ="მომხმარებლის სახელი ან პაროლი არასწორია";
        String actualNotification = loginPage.getErrorLoginMassageText();
        Assert.assertEquals(actualNotification, expectedErrorMessage, "ვალიდაციის მესიჯი არ ემთხვევა");

        // ვამოწმებ რომ ბმული არ იცვლება და მომხმარებელი რჩება იგივე გვერძე
        String expectedUrl = "https://ee.ge/signin";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "URL არ ემთხვევა");

        // ვამოწმებ ვალიდაციის მესიჯის ფერს
       String expectedColor = "rgba(191, 38, 0, 1)";
       String actualColor = loginPage.getErrorNotificationColor();
       Assert.assertEquals(actualColor, expectedColor, "ფერი არ ემთხვევა");
    }

}
