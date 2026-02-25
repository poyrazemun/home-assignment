package com.poyraz.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Set;

public class HomePage extends BasePage {

    public HomePage() {
        super();
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "(//a[text()='Compress Videos'])[1]")
    private WebElement compressVideosLink;

    @FindBy(xpath = "(//a[text()='Pricing'])[1]")
    private WebElement pricingLink;

    @FindBy(xpath = "(//a//div//p[contains(text(),\"Download Free\")])[1]")
    private WebElement downloadFreeButton;


    public void clickCompressVideos() {
        String originalWindow = driver.getWindowHandle();

        clickToElement(compressVideosLink);

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> allWindows = driver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe("about:blank")));
    }


}
