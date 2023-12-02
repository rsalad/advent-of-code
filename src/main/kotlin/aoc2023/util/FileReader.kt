package aoc2023.util

import java.io.File

/**
 * Reads a newline delimited text file from project resources into a List of Strings
 */
class FileReader {

  fun readLines(fileName: String): List<String> {
    val filePath = ClassLoader.getSystemResource(fileName)?.file ?: throw FileNotFound(fileName)
    return File(filePath).readLines()
  }
}

class FileNotFound(fileName: String): RuntimeException("Could not find file $fileName")