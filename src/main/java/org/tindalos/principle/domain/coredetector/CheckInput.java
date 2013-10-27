package org.tindalos.principle.domain.coredetector;

import java.util.List;

import org.tindalos.principle.domain.core.Package;

import com.google.common.collect.Lists;

public class CheckInput {
	
    private final List<Package> packages;
    private final DesignQualityCheckParameters parameters;
    
    public CheckInput(List<Package> packages, DesignQualityCheckParameters parameters) {
        this.packages = Lists.newArrayList(packages);
        this.parameters = parameters;
    }
    
    public List<Package> getPackages() {
        return Lists.newArrayList(packages);
    }
    public DesignQualityCheckParameters getParameters() {
        return parameters;
    }
    

}