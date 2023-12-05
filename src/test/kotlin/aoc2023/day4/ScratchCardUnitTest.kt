package aoc2023.day4

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScratchCardUnitTest {

  private val exampleInput = """
    Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
    Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
    Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
    Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
    Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
    Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
  """.trimIndent().split("\n").filter { it.isNotBlank() }

  @Test
  fun `scratch card -- from example -- expected point values`() {
    val games = exampleInput.map { ScratchCard(it) }

    assertThat(games.sumOf { it.pointValue }).isEqualTo(13)
  }

  @Test
  fun `sumOfCards -- from example -- expected number of cards`() {
    val games = exampleInput.map { ScratchCard(it) }

    val totalCards = totalCards(games)

    assertThat(totalCards).isEqualTo(30)
  }
}