plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "org.fliptile"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("org.fliptile.view.Main")
}

javafx {
    version = "17.0.9"
    modules = listOf("javafx.controls", "javafx.fxml") // Current module list for JavaFX
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}
