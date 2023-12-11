package aoc2023.day1

import aoc2023.util.FileReader


/**
 * Day 1: Trebuchet?!
 */
fun main() {
  val corrector = CalibrationCorrector()
  val inputCalibrations = FileReader().readLines("day1-trebuchet-input.txt")

  val corrected1 = corrector.correctCalibrations(inputCalibrations)
  println("Sum of part 1 calibrations: ${corrected1.sum()}")

  val corrected2 = corrector.correctCalibrations2(inputCalibrations)
  println("Sum of part 2 calibrations: ${corrected2.sum()}")
}

class CalibrationCorrector {

  /**
   * Corrects a list of calibrations by combining the first digit and the last digit (in that order) to form a single two-digit number.
   * For example:
   *  - "1abc2"       -> 12
   *  - "pqr3stu8vwx" -> 38
   *  - "a1b2c3d4e5f" -> 15
   *  - "treb7uchet"  -> 77
   *
   * Assumes that each amended calibration can be successfully corrected (i.e. contains at least 1 digit)
   */
  fun correctCalibrations(amendedCalibrations: List<String>): List<Int> {
    return amendedCalibrations.stream()
      .map { it.filter { character -> character.isDigit() } }
      .map { "${it.first()}${it.last()}" }
      .map { it.toInt() }
      .toList()
  }

  /**
   * Same as before, but accounts for digits potentially being spelled out
   */
  fun correctCalibrations2(amendedCalibrations: List<String>): List<Int> {
    return amendedCalibrations.stream()
      .map { correctCalibration(it) }
      .toList()
  }

  private fun correctCalibration(amended: String): Int {
    val first = amended.findAnyOf(numbers.keys, ignoreCase = true)!!.second
    val last = amended.findLastAnyOf(numbers.keys, ignoreCase = true)!!.second
    return "${numbers[first]}${numbers[last]}".toInt()
  }

  companion object {
    private val numbers = (0..9).associate { "$it" to "$it" } + mapOf(
      "zero" to "0",
      "one" to "1",
      "two" to "2",
      "three" to "3",
      "four" to "4",
      "five" to "5",
      "six" to "6",
      "seven" to "7",
      "eight" to "8",
      "nine" to "9",
    )
  }
}