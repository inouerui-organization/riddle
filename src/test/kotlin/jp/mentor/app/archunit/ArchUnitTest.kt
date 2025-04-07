package jp.mentor.app.archunit

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses

@AnalyzeClasses(
    packages = ["jp.mentor.app"],
    importOptions = [
        ImportOption.DoNotIncludeTests::class, ImportOption.DoNotIncludeJars::class
    ]
)
class ArchUnitTest {
    companion object {
        private const val DOMAIN_PACKAGE = "..domain.."
        private const val APPLICATION_PACKAGE = "..application.."
        private const val API_PACKAGE = "..api.."
        private const val INFRA_PACKAGE = "..infra.."
    }

    @ArchTest
    fun `ドメイン層はapi層、インフラ層、アプリケーション層を参照しない`(importClasses: JavaClasses) {
        val rule: ArchRule = noClasses()
            .that()
            .resideInAPackage(DOMAIN_PACKAGE)
            .should()
            .accessClassesThat()
            .resideInAnyPackage(APPLICATION_PACKAGE, API_PACKAGE, INFRA_PACKAGE)

        rule.check(importClasses)
    }

    @ArchTest
    fun `api層はインフラ層とドメイン層を参照しない`(importClasses: JavaClasses) {
        val rule: ArchRule = noClasses()
            .that()
            .resideInAPackage(API_PACKAGE)
            .should()
            .accessClassesThat()
            .resideInAnyPackage(DOMAIN_PACKAGE, INFRA_PACKAGE)

        rule.check(importClasses)
    }

    @ArchTest
    fun `アプリケーション層はapi層を参照しない`(importClasses: JavaClasses) {
        val rule: ArchRule = noClasses()
            .that()
            .resideInAPackage(APPLICATION_PACKAGE)
            .should()
            .accessClassesThat()
            .resideInAnyPackage(API_PACKAGE)

        rule.check(importClasses)
    }
}
