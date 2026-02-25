package com.poyraz.step_definitions;

import com.poyraz.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.io.File;
import java.nio.file.Files;

public class Hooks {

    @Before
    public void cleanDownloadsFolder() {
        String downloadDir = System.getProperty("user.dir") + "\\downloads";
        File dir = new File(downloadDir);
        if (!dir.exists()) return;

        File[] files = dir.listFiles();
        if (files == null) return;

        for (File f : files) {
            try {
                Files.deleteIfExists(f.toPath());
            } catch (Exception ignored) {
            }
        }
    }

    @After
    public void tearDown() {
        Driver.closeDriver();
    }
}
