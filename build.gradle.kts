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

repositories {
    jcenter()
}

dependencies {
	implementation(kotlin("stdlib-jdk8"))
	implementation("io.ktor:ktor-server-netty:$ktorVersion")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinxSerializationVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")
}

tasks.withType<Jar> {
    archiveFileName.set("discord-kt.jar")
}

