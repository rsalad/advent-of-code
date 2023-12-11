package aoc2023.day7

import aoc2023.day7.CamelCardHand.Companion.handComparator
import aoc2023.day7.CamelCardHand.Companion.parseString
import aoc2023.util.FileReader
import aoc2023.util.RegexPatterns.parseStrings

/**
 * Day 7: Camel Cards
 */
fun main() {
  val hands = FileReader().readLines("day7.txt").map { parseString(it) }

  println("Part 1 winnings: ${hands.calculateWinnings(useWildcards = false)}")
  println("Part 2 winnings: ${hands.calculateWinnings(useWildcards = true)}")
}

fun List<CamelCardHand>.calculateWinnings(useWildcards: Boolean = false) = sortedWith(handComparator(useWildcards))
  .mapIndexed { i, hand -> (i + 1) * hand.bid }.sum()

data class CamelCardHand(
  val cards: String,
  val bid: Int,
) {

  private val groupedCards = cards.groupBy { it }
  private val numJokers = groupedCards['J']?.size ?: 0

  fun handType(countJokers: Boolean = false): HandType {
    val wildCount = if (countJokers) numJokers else 0
    return when {
      hasNumOfAKind(5, wildCount) -> HandType.FIVE_OF_A_KIND
      hasNumOfAKind(4, wildCount) -> HandType.FOUR_OF_A_KIND
      isFullHouse(wildCount) -> HandType.FULL_HOUSE
      hasNumOfAKind(3, wildCount) -> HandType.THREE_OF_A_KIND
      isTwoPair(wildCount) -> HandType.TWO_PAIR
      hasNumOfAKind(2, wildCount) -> HandType.ONE_PAIR
      else -> HandType.HIGH_CARD
    }
  }

  private fun hasNumOfAKind(numOfAKind: Int, wildCount: Int) = groupedCards.filterNot { it.key == 'J' }
    .any { (it.value.size + wildCount == numOfAKind) } || groupedCards['J']?.size == numOfAKind

  private fun isFullHouse(wildCount: Int) = when (wildCount) {
    2, 1 -> hasNumOfAKind(3, wildCount) && isTwoPair(0)
    0 -> hasNumOfAKind(3, wildCount) && hasNumOfAKind(2, wildCount)
    else -> throw Exception()
  }

  private fun isTwoPair(wildCount: Int) = when (wildCount) {
    1 -> hasNumOfAKind(2, wildCount) && hasNumOfAKind(2, 0)
    0 -> groupedCards.filter { it.value.size == 2 }.size == 2
    else -> throw Exception()
  }

  companion object {
    fun parseString(line: String): CamelCardHand {
      val values = line.parseStrings()
      return CamelCardHand(values.first(), values.last().toInt())
    }

    fun handComparator(useWildcards: Boolean = false) = Comparator { left: CamelCardHand, right: CamelCardHand ->
      val cardValues = if (useWildcards) wildCardValues else defaultCardValues
      if (left.handType(useWildcards).ordinal != right.handType(useWildcards).ordinal) {
        return@Comparator left.handType(useWildcards).ordinal - right.handType(useWildcards).ordinal
      }
      (0..4).forEach {
        if (left.cards[it] != right.cards[it]) {
          return@Comparator cardValues[left.cards[it]]!! - cardValues[right.cards[it]]!!
        }
      }
      return@Comparator 0
    }

    private val defaultCardValues = (2..9).associateBy { "$it"[0] } + mapOf('T' to 10, 'J' to 11, 'Q' to 12, 'K' to 13, 'A' to 14)
    private val wildCardValues = (2..9).associateBy { "$it"[0] } + mapOf('T' to 10, 'J' to 1, 'Q' to 12, 'K' to 13, 'A' to 14)
  }
}

enum class HandType {
  HIGH_CARD,
  ONE_PAIR,
  TWO_PAIR,
  THREE_OF_A_KIND,
  FULL_HOUSE,
  FOUR_OF_A_KIND,
  FIVE_OF_A_KIND,
}