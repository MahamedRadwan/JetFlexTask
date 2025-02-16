package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class MovieScreen {
    private final By movieName = AppiumBy.xpath("//android.widget.TextView[contains(@text, '')]");
    private final By backToHomeButton = AppiumBy.xpath("//android.widget.ScrollView/android.view.View[2]/android.widget.Button");
    private AndroidDriver driver;

    public MovieScreen(AndroidDriver driver) {
        this.driver = driver;
    }

    // Retrieve movie name from movie screen
    public String getMovieNameFromMovieScreen() {
        return driver.findElement(movieName).getText();
    }

    //Back to Home screen
    public void backToHomeScreen() {
        driver.findElement(backToHomeButton).click();
    }
}