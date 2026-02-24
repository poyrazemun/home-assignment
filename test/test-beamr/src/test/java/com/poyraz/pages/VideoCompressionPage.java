package com.poyraz.pages;

import com.poyraz.utilities.Helper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VideoCompressionPage extends BasePage {

    public VideoCompressionPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@type='file']")
    private WebElement dropArea;

    @FindBy(xpath = "//div[@class='optimizer-download-btn']")//appears when the video is compressed
    private WebElement downloadTheVideoButton;

    @FindBy(xpath = "//div[text()='Original size']/following-sibling::div[contains(@class,'size-value')]")
    private WebElement originalSizeElement;

    @FindBy(xpath = "//div[text()='Output size']/following-sibling::div[contains(@class,'size-value')]")
    private WebElement compressedSizeElement;

    @FindBy(xpath = "//div[@class='gauge-value']")
    private WebElement gaugeValueElement;//this element holds the difference between original and compressed video


    public void uploadFile(String filePath) {
        dropArea.sendKeys(filePath);
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


}
