package org.jbandit.ecofy.model;

/**
 * This class represents all the common settings between the different
 * mojo.
 *
 * Please see org.jbandit.ecofy.model.MergeSettings or MinifySettings to have more explicit
 * explanations
 *
 */
public abstract class EcofySettings {
    private String[] extensions;

    public String[] getExtensions() {
        return extensions;
    }

    public void setExtensions(String[] extensions) {
        this.extensions = extensions;
    }
}
