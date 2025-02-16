package screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HomeScreen {
    private AndroidDriver driver;

    private final By filterMenuButton = AppiumBy.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]");
    private final By releaseDateFilterButton = AppiumBy.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView[2]/android.view.View[4]/android.widget.RadioButton");
    private final By closeFilterMenuButton = AppiumBy.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[1]/android.view.View[1]/android.widget.Button");
    private final By movieDate = AppiumBy.xpath("//android.widget.TextView[contains(@text, '-')]");
    private final String movieNameXpath = "//android.widget.TextView[@text='";
    private final By searchInput = AppiumBy.xpath("//android.widget.EditText");

    public HomeScreen(AndroidDriver driver) {
        this.driver = driver;
    }

    //search for movie
    public void searchForMovie(String movieName) throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(searchInput).sendKeys(movieName);
    }

    //Opens Movie
    public void clickOnMovieByName(String movieName)  {
        String movieXpath = movieNameXpath + movieName + "']";
        driver.findElement(By.xpath(movieXpath)).click();
    }

    //Retrieve Movie By name
    public String getMovieNameFromHomeScreen(String movieName) {
        String xpath = movieNameXpath + movieName + "']";
        return driver.findElement(By.xpath(xpath)).getText();
    }

    //Opens Filter Menu from Home Screen
    public void openFilterMenu() {
        driver.findElement(filterMenuButton).click();

    }

    // Select Release Date option from Filter Menu
    public void clickOnReleaseDateFilterButton() {
        WebElement radioButton = driver.findElement(releaseDateFilterButton);
        if (!radioButton.isSelected()) {
            radioButton.click();
        }
    }

    public void closeFilterMenu() {
        driver.findElement(closeFilterMenuButton).click();
    }

    // Retrieve all displayed dates dynamically
    public List<WebElement> getDisplayedMovieReleaseDates() {
        return driver.findElements(movieDate);
    }

    //compare the displayed date with today's system date
    public boolean areAllDatesInFuture() {
        List<WebElement> dateElements = getDisplayedMovieReleaseDates();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();

        for (WebElement element : dateElements) {
            String dateText = element.getText();
            System.out.println("Found movie release date: " + dateText);

            try {
                Date movieDate = sdf.parse(dateText);
                if (!movieDate.after(currentDate)) {
                    System.out.println("Past date found: " + dateText);
                    return false; // Found a past date
                }
            } catch (Exception e) {
                System.out.println("Error parsing date: " + dateText + " - " + e.getMessage());
                return false;
            }
        }
        return true; // All dates are in the future
    }

}