package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class DriverManager {
    private static AppiumDriver driver;
    private static Properties properties;

    private static void loadProperties() {
        if (properties == null) {
            properties = new Properties();
            try (FileInputStream fileInput = new FileInputStream("src/main/resources/config.properties")) {
                properties.load(fileInput);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load config.properties file.");
            }
        }
    }

    public static AppiumDriver getDriver() {
        if (driver == null) {
            loadProperties();  // Load properties once
            AppiumServerManager.startServer();  // Ensure Appium server is running

            try {

                String appiumServerUrl = AppiumServerManager.getServerUrl().toString();
                System.out.println(appiumServerUrl);

                // Set Desired Options
                UiAutomator2Options options = new UiAutomator2Options()
                        .setPlatformName(properties.getProperty("platformName"))
                        .setDeviceName(properties.getProperty("deviceName"))
                        .setApp(properties.getProperty("app"))
                        .setAutomationName(properties.getProperty("automationName"))
                        .setAppActivity (properties.getProperty("appActivity"))
                        .setAppPackage(properties.getProperty("appPackage"));
                // Start AndroidDriver
                driver = new AndroidDriver(new URL(appiumServerUrl), options);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                System.out.println("Driver initialized successfully!");

            } catch (MalformedURLException e) {
                throw new RuntimeException("Invalid Appium server URL !!!!!!");
            } catch (Exception e) {
                throw new RuntimeException("Failed to initialize driver: " + e.getMessage());
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            AppiumServerManager.stopServer(); // Stop Appium server after tests
        }
    }
}