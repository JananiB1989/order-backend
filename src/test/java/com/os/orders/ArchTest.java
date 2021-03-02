package com.os.orders;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.os.orders");

        noClasses()
            .that()
                .resideInAnyPackage("com.os.orders.service..")
            .or()
                .resideInAnyPackage("com.os.orders.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.os.orders.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
