package aoc2023.day7

import aoc2023.day7.CamelCardHand.Companion.parseString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CamelCardHandUnitTest {

  @Test
  fun `calculateWinnings -- default card values  -- expected ordering`() {
    val hands = exampleInput.map { parseString(it) }

    val winnings = hands.calculateWinnings(useWildcards = false)

    assertThat(winnings).isEqualTo(6440)
  }

  @Test
  fun `calculateWinnings -- wildcard values -- expected ordering`() {
    val hands = exampleInput.map { parseString(it) }

    val winnings = hands.calculateWinnings(useWildcards = true)

    assertThat(winnings).isEqualTo(5905)
  }

  companion object {
    private val exampleInput = """
      32T3K 765
      T55J5 684
      KK677 28
      KTJJT 220
      QQQJA 483
    """.trimIndent().split("\n")
  }
}