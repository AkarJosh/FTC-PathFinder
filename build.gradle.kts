import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.20-M1"
}

group = "com.cheeseBoy"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://maven.brott.dev/")}
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(files("lib/MeepMeep.jar"))
    implementation("com.acmerobotics.roadrunner:core:0.5.5")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}