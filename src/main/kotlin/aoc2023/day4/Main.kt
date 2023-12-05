package aoc2023.day4

import aoc2023.util.FileReader
import kotlin.math.pow

/**
 * Day 4: Scratch Cards
 */
fun main() {
  val cards = FileReader().readLines("day4-scratch-cards.txt").map { ScratchCard(it) }
  println("Sum of part 1 card points: ${cards.sumOf { it.pointValue }}")
  println("Sum of part 2 number of cards: ${totalCards(cards)}")
}

class ScratchCard(input: String) {

  val winningNumbers: Set<Int>
  val cardNumbers: Set<Int>
  val matches: Int
  val pointValue: Int

  init {
    val split = input.split(":", "|")
    winningNumbers = split[1].parseNumbers()
    cardNumbers = split[2].parseNumbers()
    matches = winningNumbers.intersect(cardNumbers).size
    pointValue = if (matches == 0) 0 else (2.0).pow(matches - 1).toInt()
  }

  private fun String.parseNumbers() = numberPattern.findAll(this).map { it.value.toInt() }.toSet()

  companion object {
    private val numberPattern = Regex("\\d+")
  }
}

fun totalCards(cards: List<ScratchCard>) = List(cards.size) { index -> sumOfCards(cards, index) }.sum()

fun sumOfCards(cards: List<ScratchCard>, index: Int): Int {
  return 1 + (index + 1 .. index + cards[index].matches).sumOf { sumOfCards(cards, it) }
}