package aoc2023.day1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CalibrationCorrectorUnitTest {

  @Test
  fun `correctCalibrations -- with valid input -- expected output`() {
    val input = listOf("1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet")

    val corrected = CalibrationCorrector().correctCalibrations(input)

    assertThat(corrected).containsExactly(12, 38, 15, 77)
  }

  @Test
  fun `correctCalibrations2 -- with valid input -- expected output`() {
    val input = listOf("two1nine", "eightwothree", "abcone2threexyz", "xtwone3four", "4nineeightseven2", "zoneight234", "7pqrstsixteen")

    val corrected = CalibrationCorrector().correctCalibrations2(input)

    assertThat(corrected).containsExactly(29, 83, 13, 24, 42, 14, 76)
  }
}