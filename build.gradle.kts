plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.0.10"
}

group = "org.fliptile"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // Add your JavaFX dependencies here if needed
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("org.fliptile.Main")
}

javafx {
    version = "17.0.9" // Specify the JavaFX version
    modules = listOf("javafx.controls", "javafx.fxml") // List the JavaFX modules you're using
    // Add other modules as required
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
    // If you need to pass VM options for JavaFX, you can do so here, although the plugin should handle it
}
