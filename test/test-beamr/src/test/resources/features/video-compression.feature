Feature: JPEGmini Video Compression Functionality

  As a user, I want to compress my videos using the JPEGmini web application
  So that I can reduce file size

  Background:
    Given I navigate to the JPEGmini homepage "https://jpegmini.com/"

  Scenario: Navigate to compression page and perform video optimization
    When I click on the Compress Videos button
    Then I should be redirected to the "https://jpegmini.com/compress-videos" page
    And I see the video upload drop zone

    When I upload a non-optimized video file using drag-and-drop
    And I wait for the compression process to finish
    Then the compressed file size is smaller than the original file
    And I record the reduction amount

    When I click the download the video button for the compressed video
    Then the video is downloaded to my local machine
    And the downloaded compressed file's size is less than the original file