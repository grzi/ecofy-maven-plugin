package org.jbandit.ecofy.engine;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jbandit.ecofy.model.MergeSettings;
import org.jbandit.ecofy.utils.EcofyUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MergeEngine {

    /**
     * Main method to call
     * Will check all the settings, and call the right methods.
     *
     * @param settings
     */
    public void merge(MergeSettings settings) throws IOException {
        File targetFile = new File(settings.getTargetFile());
        targetFile.createNewFile();

        if(settings.getFolder() != null && !StringUtils.isEmpty(settings.getFolder())){
            File folder = new File(settings.getFolder());
            mergeFolder(settings, targetFile, new File[]{folder});
        }else if(settings.getFiles() != null && settings.getFiles().length >0){
            File[] toMergeFiles = EcofyUtils.toFiles(settings.getFiles());
            mergeFiles(settings,targetFile,toMergeFiles);
        }
    }

    /**
     * Will append the content of all the files in sources into dest.
     *
     **@param settings
     * @param dest the file in which will be merge all the sources
     * @param sources the files that will be merge into the dest
     */
    protected void mergeFiles(MergeSettings settings, File dest, File... sources) throws IOException {
        if(sources != null && sources.length>0){
            for(File f : sources){
                mergeFiles(dest,f);
                if(settings.deleteFileAfterMerge()){
                    FileUtils.deleteQuietly(f);
                }
            }
        }
    }

    /**
     * Will browse the folders in 'folder' and detect all the files
     * After that all the eligible files are regrouped, it will merge theim into dest
     *
     * @param settings
     * @param dest
     * @param folders
     */
    protected void mergeFolder(MergeSettings settings, File dest, File... folders) throws IOException {
        if(folders != null && folders.length >0){
            for(File f : folders){
                if(f.exists() && f.isDirectory()){
                   List<File> toMergeFiles = mergeFolder(settings,dest,f);
                   mergeFiles(settings,dest,toMergeFiles.toArray(new File[toMergeFiles.size()]));
                }
            }
        }
    }

    private void mergeFiles(File dest, File source) throws IOException {
        try(OutputStream output = new BufferedOutputStream(new FileOutputStream(dest,true))){
            try(InputStream input = new BufferedInputStream(new FileInputStream(source))){
                IOUtils.copy(input,output);
            }
        }
    }

    private List<File> mergeFolder(MergeSettings settings, File dest, File folder){
        List<File> eligibleFiles = new ArrayList<>();
        File[] folderFiles = folder.listFiles();
        for (File f : folderFiles) {
            if(f.isDirectory()){
                if(settings.browseSubFolder()){
                    eligibleFiles.addAll(mergeFolder(settings,dest,f));
                }
            }else if(FilenameUtils.isExtension(f.getName(), settings.getExtensions())){
                eligibleFiles.add(f);
            }
        }
        return eligibleFiles;
    }
}
