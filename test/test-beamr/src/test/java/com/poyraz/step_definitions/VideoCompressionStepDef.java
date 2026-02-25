package com.poyraz.step_definitions;

import com.poyraz.pages.HomePage;
import com.poyraz.pages.VideoCompressionPage;
import com.poyraz.utilities.ConfigurationReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.File;

import static org.testng.Assert.*;

public class VideoCompressionStepDef {
    HomePage homePage = new HomePage();
    VideoCompressionPage videoCompressionPage = new VideoCompressionPage();
    double reductionAmount;
    File downloadedFile;

    private String inputVideo() {
        return ConfigurationReader.getProperty("input-video");
    }

    @Given("I navigate to the JPEGmini homepage {string}")
    public void i_navigate_to_the_jpe_gmini_homepage(String url) {
        homePage.navigateTo(url);
    }

    @When("I click on the Compress Videos button")
    public void i_click_on_the_compress_videos_button() {
        homePage.clickCompressVideos();
    }

    @Then("I should be redirected to the {string} page")
    public void i_should_be_redirected_to_the_page(String expectedURL) {
        String actualURL = videoCompressionPage.getCurrentURL();
        assertEquals(actualURL, expectedURL, "Expected URL does not match the actual URL.");
    }

    @Then("I see the video upload drop zone")
    public void i_see_the_video_upload_drop_zone() {
        assertTrue(videoCompressionPage.isDropAreaVisible(), "The video upload drop zone is not visible.");
    }

    @When("I upload a non-optimized video file using drag-and-drop")
    public void i_upload_a_non_optimized_video_file_using_drag_and_drop() {
        videoCompressionPage.uploadFile(inputVideo());
    }

    @When("I wait for the compression process to finish")
    public void i_wait_for_the_compression_process_to_finish() {
        videoCompressionPage.waitUntilCompressionFinishes();
    }

    @Then("the compressed file size is smaller than the original file")
    public void the_compressed_file_size_is_smaller_than_the_original_file() {
        double originalSize = videoCompressionPage.getOriginalSize();
        double compressedSize = videoCompressionPage.getCompressedSize();
        assertTrue(compressedSize < originalSize, "The compressed file size is not smaller than the original file size.");
    }

    @Then("I record the reduction amount")
    public void i_record_the_reduction_amount() {
        reductionAmount = videoCompressionPage.getGaugeValue();
        System.out.println("Reduction amount recorded as: " + reductionAmount);
    }

    @When("I click the download the video button for the compressed video")
    public void i_click_the_download_the_video_button_for_the_compressed_video() {
        videoCompressionPage.downloadVideo();
    }

    @Then("the video is downloaded to my local machine")
    public void the_video_is_downloaded_to_my_local_machine() {

        int timeout = Integer.parseInt(
                ConfigurationReader.getProperty("download-timeout-seconds"));

        File downloadedFile = videoCompressionPage.waitForDownloadedFile(timeout);

        assertNotNull(downloadedFile, "No new file was downloaded within timeout.");
        assertTrue(downloadedFile.exists(), "Downloaded file does not exist.");
        assertTrue(downloadedFile.length() > 0, "Downloaded file is empty.");

        this.downloadedFile = downloadedFile;

    }

    @Then("the downloaded compressed file's size is less than the original file")
    public void the_downloaded_compressed_file_s_size_is_less_than_the_original_file() {

        String originalPath = System.getProperty("user.dir") + "\\src\\test\\resources\\" + inputVideo();


        File originalFile = new File(originalPath);


        assertTrue(originalFile.exists(), "Original file not found: " + originalPath);
        assertNotNull(downloadedFile, "Downloaded file reference is null.");

        long originalSize = originalFile.length();
        long compressedSize = downloadedFile.length();

        assertTrue(compressedSize < originalSize,
                "Compressed file size is NOT smaller than original file size.");
    }

}
