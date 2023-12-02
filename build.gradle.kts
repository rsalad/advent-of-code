plugins {
  alias(libs.plugins.jvm)
}

kotlin { jvmToolchain(17) }

tasks {
  val run by registering(JavaExec::class) {
    val dayNum = findProperty("day")?.toString() ?: "1"
    mainClass = "aoc2023.day$dayNum.MainKt"
    classpath = sourceSets.main.get().runtimeClasspath
    doFirst { println("Executing Advent of Code Day $dayNum") }
  }
}

@Suppress("UnstableApiUsage")
testing {
  suites {
    val test by getting(JvmTestSuite::class) {
      useJUnitJupiter()
      dependencies {
        implementation.bundle(libs.bundles.unit.testing)
      }
    }
  }
}
