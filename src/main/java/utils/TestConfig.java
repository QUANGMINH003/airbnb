package utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;

public class TestConfig {
    private  static Properties properties;
    private static final String CONFIG_FILE = "src/test/resources/config.properties";

    static {
        loadProperties();
    }

    private static void loadProperties() {
        properties = new Properties();
        try {
            FileInputStream input = new FileInputStream(CONFIG_FILE);
            properties.load(input);
            input.close();
        } catch (IOException e) {
            System.out.println("Error loading properties file: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static BrowserType getBrowserType(Playwright playwright) {
        String browserType = getProperty("browser");
        if(browserType.equals("chrome")) {
            return playwright.chromium();
        }
        return playwright.chromium();
    }

    public static Browser.NewContextOptions getNewContextOptions() {
        try{
            Files.createDirectories(Paths.get("videos"));
        }catch (IOException ignored) {

        }
        return new Browser.NewContextOptions()
                .setViewportSize(null)
                .setIgnoreHTTPSErrors(true)
                .setRecordVideoDir(Paths.get("videos"))
                .setRecordVideoSize(1280, 720);
    }

    public static BrowserType.LaunchOptions getBrowserLaunchOptions() {
        return new BrowserType.LaunchOptions()
                .setChannel("chrome")
                .setHeadless(false)
                .setArgs(Arrays.asList("--start-maximized"));
    }

    public static String getBaseUrl() {
        return getProperty("base.url");
    }

    public static String getValidName() {
        return getProperty("test.valid.name");
    }

    public static String getValidEmail() {
        return getProperty("test.valid.email");
    }

    public static String getValidPassword() {
        return getProperty("test.valid.password");
    }

    public static String getValidPhone() {
        return getProperty("test.valid.phone");
    }

    public static String getValidBirthDate() {
        return getProperty("test.valid.birtday");
    }

    public static String getInvaliFormatEmail() {
        return getProperty("test.invalidFormat.email");
    }

    public static String getInvalidEmail() {
        return getProperty("test.invalid.email");
    }

    public static String getInvalidPassword() {
        return getProperty("test.invalid.password");
    }

    public static String getWeakPassword() {
        return getProperty("test.weak.password");
    }
}
