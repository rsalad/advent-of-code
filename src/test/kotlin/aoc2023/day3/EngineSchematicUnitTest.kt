package aoc2023.day3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EngineSchematicUnitTest {

  private val exampleSchematic = listOf(
    "467..114..",
    "...*......",
    "..35..633.",
    "......#...",
    "617*......",
    ".....+.58.",
    "..592.....",
    "......755.",
    "...$.*....",
    ".664.598..",
  )

  @Test
  fun `parts -- with sample input -- expected output`() {
    val partNumbers = EngineSchematic().parts(exampleSchematic)

    assertThat(partNumbers.sumOf { it.number }).isEqualTo(4361)
  }

  @Test
  fun `gearRatios -- with sample input -- expected output`() {
    val gearRatios = EngineSchematic().gearRatios(exampleSchematic)

    assertThat(gearRatios.sum()).isEqualTo(467835)
  }
}