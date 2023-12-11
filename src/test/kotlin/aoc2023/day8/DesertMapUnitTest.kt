package aoc2023.day8

import DesertMap.Companion.parseMap
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DesertMapUnitTest {

  @Test
  fun `instructionCount -- with example input -- expected number steps`() {
    val map = parseMap(exampleInput)

    val numberSteps = map.numberStepsToFinish()

    assertThat(numberSteps).isEqualTo(2)
  }

  @Test
  fun `instructionCount -- with short input -- expected number steps`() {
    val example2 = """
      LLR

      AAA = (BBB, BBB)
      BBB = (AAA, ZZZ)
      ZZZ = (ZZZ, ZZZ)
    """.trimIndent().split("\n")
    val map = parseMap(example2)

    val numberSteps = map.numberStepsToFinish()

    assertThat(numberSteps).isEqualTo(6)
  }

  @Test
  fun `ghostSteps -- with ghost input -- expected output`() {
    val ghostExample = """
      LR

      11A = (11B, XXX)
      11B = (XXX, 11Z)
      11Z = (11B, XXX)
      22A = (22B, XXX)
      22B = (22C, 22C)
      22C = (22Z, 22Z)
      22Z = (22B, 22B)
      XXX = (XXX, XXX)
    """.trimIndent().split("\n")
    val map = parseMap(ghostExample)

    val numberSteps = map.ghostStepsToFinish()

    assertThat(numberSteps).isEqualTo(6)
  }

  companion object {
    private val exampleInput = """
      RL

      AAA = (BBB, CCC)
      BBB = (DDD, EEE)
      CCC = (ZZZ, GGG)
      DDD = (DDD, DDD)
      EEE = (EEE, EEE)
      GGG = (GGG, GGG)
      ZZZ = (ZZZ, ZZZ)
    """.trimIndent().split("\n")
  }
}