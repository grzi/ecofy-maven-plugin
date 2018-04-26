package org.jbandit.ecofy.utils;

import org.jbandit.ecofy.exception.EcofyException;
import org.jbandit.ecofy.model.MergeSettings;

public class SettingsUtils {
    public static void verifyMergeSettings(MergeSettings mergeSettings) throws EcofyException {
        if(mergeSettings==null){
            throw new EcofyException("Merge settings are null.");
        }

        if(mergeSettings.getTargetFile() == null || mergeSettings.getTargetFile().isEmpty()) {
            throw new EcofyException("No target file found in merge settings.");
        }

        if((mergeSettings.getFolder() == null || mergeSettings.getFolder().isEmpty())
                && (mergeSettings.getFiles()==null || mergeSettings.getFiles().length==0)){
            throw new EcofyException("No folder and no files in merge settings. At least one should exist !");
        }

        if(mergeSettings.getBaseDir()==null){
            mergeSettings.setBaseDir("");
        }
    }
}
