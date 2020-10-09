import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
	kotlin("plugin.serialization") version "1.4.10"
}

version = "0.0.0a"
group = "io.github.romangraef"
val kotlinVersion = "1.4.10"
val ktorVersion = "1.4.0"
val kotlinxCoroutinesVersion = "1.3.9"
val kotlinxSerializationVersion = "1.0.0-RC2"
val kotestVersion = "4.2.5"
val slf4jVersion = "1.7.30"

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
    implementation("io.ktor:ktor-client-json:$ktorVersion")
    implementation("io.ktor:ktor-client-serialization:$ktorVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinxSerializationVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")

    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
    testRuntimeOnly("org.slf4j:slf4j-simple:$slf4jVersion")
    testImplementation("io.ktor:ktor-client-logging-jvm:$ktorVersion")
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
tasks.withType<Jar> {
    archiveFileName.set("discord-kt.jar")
}
tasks.withType<Test> {
    useJUnitPlatform {
    }
}
