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
