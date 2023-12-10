package aoc2023.day6

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import parseRaces

class BoatRaceUnitTest {

  @Test
  fun `numPossibleRecordSolutions -- with example input -- expected output`() {
    val races = parseRaces(exampleInput)

    val availableRecordSolutions = races.map { it.numPossibleRecordSolutions() }

    assertThat(availableRecordSolutions).containsExactly(4, 8, 9)
  }

  @Test
  fun `numPossibleRecordSolutions -- with stripped example input -- expected output`() {
    val race = parseRaces(exampleInput.map { it.replace(" ", "") }).first()

    val availableRecordSolutions = race.numPossibleRecordSolutions()

    assertThat(availableRecordSolutions).isEqualTo(71503)
  }

  companion object {
    private val exampleInput = """
      Time:      7  15   30
      Distance:  9  40  200
    """.trimIndent().split("\n")
  }
}