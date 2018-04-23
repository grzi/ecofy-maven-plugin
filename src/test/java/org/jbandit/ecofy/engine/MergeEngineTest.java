package org.jbandit.ecofy.engine;

import org.apache.commons.io.FileUtils;
import org.jbandit.ecofy.model.MergeSettings;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class MergeEngineTest {
    MergeEngine engine;

    private static final String copyTargetPath = "target/copy-test-classes";

    @Before
    public void before(){
        engine = new MergeEngine();
        try {
            FileUtils.copyDirectory(new File("target/test-classes"),new File(copyTargetPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void after(){
        try {
            FileUtils.deleteDirectory(new File(copyTargetPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void mergeFiles_test() throws IOException {

        MergeSettings settings = new MergeSettings();

        File fileToMerge = new File(copyTargetPath + "/fileA.css");
        File baseFile = File.createTempFile("tempFile" , ".tmp");
        engine.mergeFiles(settings, baseFile,fileToMerge);
        assertEquals(fileToMerge.length(),baseFile.length());
    }

    @Test
    public void mergeFiles_withDelete_test() throws IOException {

        MergeSettings settings = new MergeSettings();
        settings.setDeleteFileAfterMerge(true);

        File fileToMerge = new File(copyTargetPath +"/fileA.css");
        File baseFile = File.createTempFile("tempFile" , ".tmp");
        engine.mergeFiles(settings, baseFile,fileToMerge);
        assertEquals(false,fileToMerge.exists());
    }

    @Test
    public void mergeFolder_test() throws IOException {

        MergeSettings settings = new MergeSettings();
        settings.setDeleteFileAfterMerge(false);
        settings.setBrowseSubFolder(false);
        settings.setExtensions(new String[]{"css"});

        File folderToMerge = new File(copyTargetPath +"/folderA");
        File baseFile = File.createTempFile("tempFile" , ".tmp");
        engine.mergeFolder(settings, baseFile,folderToMerge);

        assertEquals(new File(copyTargetPath + "/folderA/fileB.css").length(),baseFile.length());
    }

    @Test
    public void mergeFolder_with_subfolder_test() throws IOException {
        MergeSettings settings = new MergeSettings();
        settings.setDeleteFileAfterMerge(false);
        settings.setBrowseSubFolder(true);
        settings.setExtensions(new String[]{"css"});

        File folderToMerge = new File(copyTargetPath +"/folderA");
        File baseFile = File.createTempFile("tempFile" , ".tmp");
        engine.mergeFolder(settings, baseFile,folderToMerge);
        long length =
                new File(copyTargetPath + "/folderA/fileB.css").length()
                        + new File(copyTargetPath + "/folderA/folderB/fileC.css").length();
        assertEquals(length,baseFile.length());
    }

    @Test
    public void merge_folder_with_destFile_test() throws IOException {
        MergeSettings settings = new MergeSettings();
        settings.setDeleteFileAfterMerge(false);
        settings.setBrowseSubFolder(false);
        settings.setExtensions(new String[]{"css"});
        settings.setTargetFile(copyTargetPath + "/folderA.css");
        settings.setFolder(copyTargetPath + "/folderA");

        engine.merge(settings);

        File toTest = new File(copyTargetPath + "/folderA.css");
        assertEquals(true,toTest.exists());
        assertEquals(new File(copyTargetPath + "/folderA/fileB.css").length(),toTest.length());
    }

}
