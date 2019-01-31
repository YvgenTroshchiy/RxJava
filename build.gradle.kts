import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "RxJava"
version = "1.0-SNAPSHOT"

buildscript {
    var kotlin_version: String by extra
    kotlin_version = "1.1.60"

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath(kotlinModule("gradle-plugin", kotlin_version))
    }

}

apply {
    plugin("java")
    plugin("kotlin")
}

val kotlin_version: String by extra

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlinModule("stdlib-jre8", kotlin_version))
    testCompile("junit", "junit", "4.12")

    compile("io.reactivex.rxjava2:rxjava:2.2.6")
    compile("io.reactivex.rxjava2:rxkotlin:2.3.0")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}