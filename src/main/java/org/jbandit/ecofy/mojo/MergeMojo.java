package org.jbandit.ecofy.mojo;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.jbandit.ecofy.engine.MergeEngine;
import org.jbandit.ecofy.exception.EcofyException;
import org.jbandit.ecofy.model.MergeSettings;

import java.io.IOException;

@Mojo(name = "merge")
public class MergeMojo extends AbstractMojo {

    @Parameter
    MergeSettings mergeConfiguration;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        MergeEngine engine = new MergeEngine();
        try{
            engine.merge(mergeConfiguration);
        }catch (EcofyException e){
            getLog().error("Error while merging", e);
        }
    }
}
