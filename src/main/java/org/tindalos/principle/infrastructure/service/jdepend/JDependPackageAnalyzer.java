package org.tindalos.principle.infrastructure.service.jdepend;

import java.util.Collection;
import java.util.List;

import jdepend.framework.JavaPackage;

import org.tindalos.principle.domain.checker.PackageAnalyzer;
import org.tindalos.principle.domain.core.DesignCheckerParameters;
import org.tindalos.principle.domain.core.Package;

public class JDependPackageAnalyzer implements PackageAnalyzer {
    
    private final JDependRunner jDependRunner;
    private final PackageBuilder packageBuilder;

    public JDependPackageAnalyzer(JDependRunner jDependRunner, PackageBuilder packageBuilder) {
        this.jDependRunner = jDependRunner;
        this.packageBuilder = packageBuilder;
    }

    public List<Package> analyze(DesignCheckerParameters parameters) {
        Collection<JavaPackage> analyzedPackages = jDependRunner.getAnalyzedPackages(parameters.getBasePackage());
        return packageBuilder.build(analyzedPackages, parameters.getBasePackage());
    }

}
