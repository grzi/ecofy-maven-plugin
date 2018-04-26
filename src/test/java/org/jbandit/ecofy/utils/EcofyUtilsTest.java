package org.jbandit.ecofy.utils;

import org.jbandit.ecofy.model.MergeSettings;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class EcofyUtilsTest {

    private final static String classPath = "target/test-classes";

    @Test
    public void toFiles_test(){
        MergeSettings settings = new MergeSettings();
        settings.setBaseDir("");
        String[] fileNames = new String[]{classPath + "/fileA.css",classPath + "/folderA/fileB.css" };
        File[] files = EcofyUtils.toFiles(settings, fileNames);
        assertEquals(2, files.length);
    }
    @Test
    public void toFiles_null_test(){
        MergeSettings settings = new MergeSettings();
        settings.setBaseDir("test");
        File[] files = EcofyUtils.toFiles(settings,null);
        assertEquals(0,files.length);
    }

    @Test
    public void toPath_empty_basedir_test(){
        assertEquals("testA", EcofyUtils.toPath("","testA"));
    }

    @Test
    public void toPath_basedir_and_target_without_separator_test(){
        assertEquals("baseDir" +File.separator + "testA", EcofyUtils.toPath("baseDir","testA"));
    }

    @Test
    public void toPath_basedir_with_path_separator_and_target_without_separator_test(){
        assertEquals("baseDir" +File.separator + "testA", EcofyUtils.toPath("baseDir/","testA"));
    }

    @Test
    public void toPath_basedir_without_path_separator_and_target_with_separator_test(){
        assertEquals("baseDir" +File.separator + "testA", EcofyUtils.toPath("baseDir","/testA"));
    }
}
