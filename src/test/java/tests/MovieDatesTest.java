package tests;

import base.DriverManager;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import screens.HomeScreen;

public class MovieDatesTest {

    private AndroidDriver driver;
    private HomeScreen homeScreen;

    @BeforeClass
    public void startDriver() {
        driver = (AndroidDriver) DriverManager.getDriver();
        homeScreen = new HomeScreen(driver);
    }

    @Test(description = "Verify that the movies date are in the future as filtered")
    @Feature("Movie Date in Future Verification")
    @Story("User should see that filtered movies by date are in the future")
    @Step("Click on movie and verify details")
    public void verifyReleaseDateFilterShowsFutureMoviesOnly() {
        System.out.println("Applying release date filter...");

        // open filter menu
        homeScreen.openFilterMenu();

        // Select release date filter
        homeScreen.clickOnReleaseDateFilterButton();

        // close filter menu
        homeScreen.closeFilterMenu();

        // Validate all dates are in the future
        boolean allDatesAreFuture = homeScreen.areAllDatesInFuture();

        // Assert
        Assert.assertTrue(allDatesAreFuture, "Some movies have past release dates!");
        System.out.println("All release dates are in the future.");
    }

    @AfterClass
    public void quitDriver() {
        DriverManager.quitDriver();
    }

}
