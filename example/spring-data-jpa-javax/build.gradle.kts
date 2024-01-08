import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    alias(exampleLibs.plugins.spring.boot2)
    alias(exampleLibs.plugins.kotlin.noarg)
    alias(exampleLibs.plugins.kotlin.allopen)
    alias(exampleLibs.plugins.kotlin.spring)
    alias(exampleLibs.plugins.kotlin.jpa)
}

dependencies {
    @Suppress("VulnerableLibrariesLocal", "RedundantSuppression")
    implementation(exampleLibs.spring.boot2.jpa)
    implementation(exampleLibs.spring.boot2.p6spy)
    implementation(projects.example)
    implementation(projects.jpqlDsl)
    implementation(projects.jpqlRender)
    implementation(projects.springDataJpaJavaxSupport)

    runtimeOnly(exampleLibs.h2)

    testImplementation(exampleLibs.spring.boot2.test)
}

kotlin {
    jvmToolchain(8)

    compilerOptions {
        apiVersion = KotlinVersion.KOTLIN_1_9
        languageVersion = KotlinVersion.KOTLIN_1_9
    }
}

noArg {
    annotation("com.linecorp.kotlinjdsl.example.spring.data.jpa.javax.jpql.annotation.CompositeId")
}

allOpen {
    annotation("com.linecorp.kotlinjdsl.example.spring.data.jpa.javax.jpql.annotation.CompositeId")
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
}

tasks.withType<PublishToMavenRepository>().configureEach { enabled = false }
tasks.withType<PublishToMavenLocal>().configureEach { enabled = false }
