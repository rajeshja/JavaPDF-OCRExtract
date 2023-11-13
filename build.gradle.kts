plugins {
    id("java")
}

group = "rja.ocr"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.pdfbox:pdfbox:2.0.30")
    implementation("com.github.jai-imageio:jai-imageio-core:1.4.0")
    implementation("com.github.jai-imageio:jai-imageio-jpeg2000:1.4.0")

    implementation("net.sourceforge.tess4j:tess4j:5.8.0")
    implementation("org.bytedeco:javacpp:1.5.9")
    implementation("org.bytedeco:tesseract:5.3.1-1.5.9")
    implementation("org.bytedeco:opencv-platform:4.7.0-1.5.9")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}