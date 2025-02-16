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

    public void searchForMovie(String movieName) {
        driver.findElement(searchInput).sendKeys(movieName);
    }

    public void clickOnMovieByName(String movieName)  {
        String movieXpath = movieNameXpath + movieName + "']";
        driver.findElement(By.xpath(movieXpath)).click();
    }

    public String getMovieNameFromHomeScreen(String movieName) {
        String xpath = movieNameXpath + movieName + "']";
        return driver.findElement(By.xpath(xpath)).getText();
    }

    public void openFilterMenu() {
        driver.findElement(filterMenuButton).click();

    }

    public void clickOnReleaseDateFilterButton() {
        WebElement radioButton = driver.findElement(releaseDateFilterButton);
        if (!radioButton.isSelected()) {
            radioButton.click();
        }
    }

    public void closeFilterMenu() {
        driver.findElement(closeFilterMenuButton).click();
    }

    public List<WebElement> getDisplayedMovieReleaseDates() {
        return driver.findElements(movieDate);
    }

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
        return true;
    }

}