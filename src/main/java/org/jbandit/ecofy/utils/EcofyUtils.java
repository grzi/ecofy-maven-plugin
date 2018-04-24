package org.jbandit.ecofy.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EcofyUtils {
    public static File[] toFiles(String[] fileNames){
        List<File> files = new ArrayList<>();
        if(fileNames==null || fileNames.length==0){
            return new File[0];
        }
        for(String fileName : fileNames){
            File tmp = new File(fileName);
            if(tmp.exists()){
                files.add(tmp);
            }
        }
        return files.toArray(new File[files.size()]);
    }
}
