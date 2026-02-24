package com.poyraz.step_definitions;

import com.poyraz.pages.HomePage;
import com.poyraz.pages.VideoCompressionPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class VideoCompressionStepDef {
    HomePage homePage = new HomePage();
    VideoCompressionPage videoCompressionPage = new VideoCompressionPage();

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
        assertEquals(expectedURL, actualURL, "Expected URL does not match the actual URL.");
    }

    @Then("I see the video upload drop zone")
    public void i_see_the_video_upload_drop_zone() {
        assertTrue(videoCompressionPage.isDropAreaVisible(), "The video upload drop zone is not visible.");
    }

    @When("I upload a non-optimized video file using drag-and-drop")
    public void i_upload_a_non_optimized_video_file_using_drag_and_drop() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("I wait for the compression process to finish")
    public void i_wait_for_the_compression_process_to_finish() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the compressed file size is smaller than the original file")
    public void the_compressed_file_size_is_smaller_than_the_original_file() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("I record the reduction ratio")
    public void i_record_the_reduction_ratio() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("I click the {string} button for the compressed video")
    public void i_click_the_button_for_the_compressed_video(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the video is downloaded to my local machine")
    public void the_video_is_downloaded_to_my_local_machine() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the downloaded compressed file's size is less than the original file")
    public void the_downloaded_compressed_file_s_size_is_less_than_the_original_file() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
