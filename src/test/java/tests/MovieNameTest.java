package tests;

import base.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.*;
import io.appium.java_client.android.AndroidDriver;
import screens.HomeScreen;
import screens.MovieScreen;

public class MovieNameTest {

    private AppiumDriver driver;
    private HomeScreen homeScreen;
    private MovieScreen movieScreen;

    @BeforeClass
    public void startDriver() {
        driver = DriverManager.getDriver();
        homeScreen = new HomeScreen((AndroidDriver) driver);
        movieScreen = new MovieScreen((AndroidDriver) driver);
    }

    // TODO: Optimize this dataProvider for large datasets by reading from json file
    @DataProvider(name = "movieNames")
    public Object[][] getMovieNames() {
        return new Object[][] {
                {"Wolf Man"},
                {"Kraven the Hunter"},
                {"Back in Action"},
                {"Avatar"}
        };
    }

    @Feature("Movie Verification")
    @Story("User should see the correct movie name")
    @Step("Click on movie and verify details")
    @Test(dataProvider = "movieNames",description = "Verify that movie name in home screen is the same in the movie screen")
    public void verifyMovieNameConsistency(String movieName) {
        System.out.println("Testing movie: " + movieName);

        homeScreen.searchForMovie(movieName);

        String homeMovieName = homeScreen.getMovieNameFromHomeScreen(movieName);

        homeScreen.clickOnMovieByName(movieName);

        String movieScreenName = movieScreen.getMovieNameFromMovieScreen();

        Assert.assertEquals(homeMovieName, movieScreenName, "Movie names do not match!");

        System.out.println("Movie name verified successfully: " + movieName);

        movieScreen.backToHomeScreen();
    }

    @AfterClass
    public void quitDriver() {
        DriverManager.quitDriver();
    }
}
