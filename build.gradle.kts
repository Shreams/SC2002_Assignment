plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.apache.poi/poi
    implementation("org.apache.poi:poi:5.2.4")

    // https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml
    implementation("org.apache.poi:poi-ooxml:5.2.4")

    implementation("org.apache.logging.log4j:log4j-core:3.0.0-alpha1")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}