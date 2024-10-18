plugins {
    kotlin("jvm") version "1.8.0" // Use uma versão estável do Kotlin
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("org.jsoup:jsoup:1.14.3") // Adicionando Jsoup para manipulação de HTML
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
}

// Configuração do Java Toolchain para garantir que a versão correta do JDK seja usada
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17)) // Definindo a versão do JDK para 17
    }
}

// Configuração do jvmTarget para Kotlin
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17" // Definindo jvmTarget para 17
    }
}

tasks.test {
    useJUnitPlatform()
}
