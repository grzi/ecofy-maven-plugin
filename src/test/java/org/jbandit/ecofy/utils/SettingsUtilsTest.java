package org.jbandit.ecofy.utils;

import org.jbandit.ecofy.exception.EcofyException;
import org.jbandit.ecofy.model.MergeSettings;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SettingsUtilsTest {

    @Test
    public void verifyMergeSettings_ok_test() throws EcofyException{
        MergeSettings settings = new MergeSettings();
        settings.setBaseDir("target/");
        settings.setDeleteFileAfterMerge(true);
        settings.setBrowseSubFolder(true);
        settings.setFiles(null);
        settings.setFolder("randomFolder");
        settings.setExtensions(new String[]{"CSS","JS"});
        settings.setTargetFile("targetFile");

        SettingsUtils.verifyMergeSettings(settings);
    }

    @Test(expected = EcofyException.class)
    public void verifyMergeSettings_missing_targetFile_test() throws EcofyException{
        MergeSettings settings = new MergeSettings();
        settings.setBaseDir("target/");
        settings.setDeleteFileAfterMerge(true);
        settings.setBrowseSubFolder(true);
        settings.setFiles(null);
        settings.setFolder("randomFolder");
        settings.setExtensions(new String[]{"CSS","JS"});
        SettingsUtils.verifyMergeSettings(settings);
    }

    @Test(expected = EcofyException.class)
    public void verifyMergeSettings_missing_files_and_folder_test() throws EcofyException{
        MergeSettings settings = new MergeSettings();
        settings.setBaseDir("target/");
        settings.setDeleteFileAfterMerge(true);
        settings.setBrowseSubFolder(true);
        settings.setExtensions(new String[]{"CSS","JS"});
        settings.setTargetFile("targetFile");
        SettingsUtils.verifyMergeSettings(settings);
    }
}
