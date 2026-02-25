package com.poyraz.pages;

import com.poyraz.utilities.ConfigurationReader;
import com.poyraz.utilities.Helper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.time.Duration;

public class VideoCompressionPage extends BasePage {

    public VideoCompressionPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//label[@class='dropzone ']")
    private WebElement dropZone;

    @FindBy(xpath = "//input[@type='file']")
    private WebElement fileInput;

    @FindBy(xpath = "//div[@class='optimizer-download-btn']")//appears when the video is compressed
    private WebElement downloadTheVideoButton;

    @FindBy(xpath = "//div[text()='Original size']/following-sibling::div[contains(@class,'size-value')]")
    private WebElement originalSizeElement;

    @FindBy(xpath = "//div[text()='Output size']/following-sibling::div[contains(@class,'size-value')]")
    private WebElement compressedSizeElement;

    @FindBy(xpath = "//div[@class='gauge-value']")
    private WebElement gaugeValueElement;//this element holds the difference between original and compressed video


    public boolean isDropAreaVisible() {
        return isElementVisible(dropZone);
    }

    public void uploadFile(String fileName) {
        String relativePath = "src/test/resources/" + fileName;
        File file = new File(relativePath);
        fileInput.sendKeys(file.getAbsolutePath());
    }

    public void waitUntilCompressionFinishes() {
        int seconds = Integer.parseInt(ConfigurationReader.getProperty("compression-timeout-seconds"));
        waitForElementToBeClickable(downloadTheVideoButton, Duration.ofSeconds(seconds));
    }

    public double getOriginalSize() {
        String originalSizeText = getText(originalSizeElement);
        return Helper.extractNumber(originalSizeText);
    }

    public double getCompressedSize() {
        String compressedSizeText = getText(compressedSizeElement);
        return Helper.extractNumber(compressedSizeText);
    }

    public double getGaugeValue() {
        String gaugeText = getText(gaugeValueElement);
        return Helper.extractNumber(gaugeText);
    }

    public void downloadVideo() {
        clickToElement(downloadTheVideoButton);
    }

    public File waitForDownloadedFile(int timeoutSeconds) {

        String downloadPath = System.getProperty("user.dir") + "\\downloads";
        File dir = new File(downloadPath);

        int waited = 0;

        while (waited < timeoutSeconds) {

            File[] files = dir.listFiles();

            if (files != null) {
                for (File f : files) {

                    String name = f.getName().toLowerCase();

                    if (name.endsWith(".crdownload") || name.endsWith(".tmp")) {
                        continue;
                    }
                    if (name.endsWith(".mp4") || name.endsWith(".mov")
                            || name.endsWith(".avi") || name.endsWith(".m4v")) {
                        return f;
                    }
                }
            }

            try {
                Thread.sleep(1000);
                waited++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


}
