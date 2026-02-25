package com.poyraz.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Driver {

    private Driver() {
    }

    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();

    public static WebDriver getDriver() {

        if (driverPool.get() == null) {
            String browserType = ConfigurationReader.getProperty("browser");

            switch (browserType.toLowerCase()) {
                case "chrome":
                    String downloadPath = System.getProperty("user.dir") + "\\downloads";

                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("download.default_directory", downloadPath);
                    prefs.put("download.prompt_for_download", false);
                    prefs.put("safebrowsing.enabled", true);

                    ChromeOptions options = new ChromeOptions();
                    options.setExperimentalOption("prefs", prefs);

                    driverPool.set(new ChromeDriver(options));
                    break;
                case "firefox":
                    String firefoxDownloadPath = System.getProperty("user.dir") + "\\downloads";

                    FirefoxProfile profile = new FirefoxProfile();
                    profile.setPreference("browser.download.dir", firefoxDownloadPath);
                    profile.setPreference("browser.download.folderList", 2);
                    profile.setPreference("browser.download.useDownloadDir", true);
                    profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "video/mp4,video/avi,video/mov,video/mkv,application/octet-stream");

                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setProfile(profile);

                    driverPool.set(new FirefoxDriver(firefoxOptions));
                    break;
            }

            driverPool.get().manage().window().maximize();
            driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }

        return driverPool.get();
    }

    public static void closeDriver() {
        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }

}
