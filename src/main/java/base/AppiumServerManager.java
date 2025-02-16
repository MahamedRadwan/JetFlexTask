package base;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import java.net.URL;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppiumServerManager {
    private static AppiumDriverLocalService service;
    private static Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    private static String getAppiumJSPath() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("win") ? properties.getProperty("appiumJSPath_Windows") : properties.getProperty("appiumJSPath_Mac");
    }

    public static void startServer() {
        if (service == null) {
            service = new AppiumServiceBuilder()
                    .withAppiumJS(new File(getAppiumJSPath())) // Dynamically setting the path
                    .withIPAddress("127.0.0.1")
                    .usingAnyFreePort()
                    .build();
            service.start();
            System.out.println("Appium Server Started on :)" + service.getUrl());
        }
    }

    public static void stopServer() {
        if (service != null) {
            service.stop();
            System.out.println("Appium Server Stopped! :(");
            service = null;
        }
    }

    public static URL getServerUrl() {
        return service.getUrl();
    }
}
