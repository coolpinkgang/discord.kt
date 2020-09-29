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
val kotestVersion= "4.2.5"

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinxSerializationVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")

    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
}

tasks.withType<Jar> {
    archiveFileName.set("discord-kt.jar")
}
tasks.withType<Test> {
    useJUnitPlatform {
    }
}
