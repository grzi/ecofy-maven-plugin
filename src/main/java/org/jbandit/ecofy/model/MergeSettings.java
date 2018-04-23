package org.jbandit.ecofy.model;

import org.apache.maven.plugins.annotations.Parameter;

public class MergeSettings extends EcofySettings{

    @Parameter
    private String folder;

    @Parameter
    private String[] files;

    @Parameter(defaultValue = "false")
    private boolean deleteFileAfterMerge;

    @Parameter(defaultValue = "false")
    private boolean browseSubFolder;

    @Parameter
    private String targetFile;

    public boolean deleteFileAfterMerge() {
        return deleteFileAfterMerge;
    }

    public boolean browseSubFolder() {
        return browseSubFolder;
    }

    public void setDeleteFileAfterMerge(boolean deleteFileAfterMerge) {
        this.deleteFileAfterMerge = deleteFileAfterMerge;
    }

    public void setBrowseSubFolder(boolean browseSubFolder) {
        this.browseSubFolder = browseSubFolder;
    }

    public String getTargetFile() {
        return targetFile;
    }

    public void setTargetFile(String targetFile) {
        this.targetFile = targetFile;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String[] getFiles() {
        return files;
    }

    public void setFiles(String[] files) {
        this.files = files;
    }
}
