package org.jbandit.ecofy.utils;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class EcofyUtilsTest {

    private final static String classPath = "target/test-classes";

    @Test
    public void toFiles_test(){
        String[] fileNames = new String[]{classPath + "/fileA.css",classPath + "/folderA/fileB.css" };
        File[] files = EcofyUtils.toFiles(fileNames);
        assertEquals(2, files.length);
    }
}
