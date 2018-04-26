package org.jbandit.ecofy.utils;

import org.jbandit.ecofy.model.EcofySettings;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EcofyUtils {

    private EcofyUtils(){ }

    public static File[] toFiles(EcofySettings settings, String[] fileNames){
        List<File> files = new ArrayList<>();
        if(fileNames==null || fileNames.length==0){
            return new File[0];
        }
        for(String fileName : fileNames){
            File tmp = new File(toPath(settings.getBaseDir(), fileName));
            if(tmp.exists()){
                files.add(tmp);
            }
        }
        return files.toArray(new File[files.size()]);
    }

    public static String toPath(String baseDir, String targetFile) {
        if(baseDir.endsWith("/") || baseDir.endsWith("\\")){
            baseDir = baseDir.substring(0,baseDir.length()-1);
        }
        if(targetFile.startsWith("/") || targetFile.startsWith("\\")){
            targetFile = targetFile.substring(1);
        }
        return ((!baseDir.isEmpty()) ?( baseDir + File.separator) : "") + targetFile;
    }
}
