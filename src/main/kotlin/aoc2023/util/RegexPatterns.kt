package aoc2023.util

object RegexPatterns {
  private val numbersPattern = Regex("\\d+")
  fun String.parseInts() = numbersPattern.findAll(this).map { it.value.toInt() }.toList()
  fun String.parseLongs() = numbersPattern.findAll(this).map { it.value.toLong() }.toList()
}