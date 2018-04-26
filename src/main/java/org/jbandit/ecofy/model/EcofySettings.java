package org.jbandit.ecofy.model;

import org.apache.maven.plugins.annotations.Parameter;

/**
 * This class represents all the common settings between the different
 * mojo.
 *
 * Please see org.jbandit.ecofy.model.MergeSettings or MinifySettings to have more explicit
 * explanations
 *
 */
public abstract class EcofySettings {
    @Parameter
    private String[] extensions;

    @Parameter(defaultValue = "")
    private String baseDir;

    public String[] getExtensions() {
        return extensions;
    }

    public void setExtensions(String[] extensions) {
        this.extensions = extensions;
    }

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }
}
