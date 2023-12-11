import aoc2023.util.FileReader
import aoc2023.util.RegexPatterns.parseLongs

fun main() {
  val lines = FileReader().readLines("day6.txt")
  val races = parseRaces(lines)
  val numberPossibleRecordSolutions = races.map { it.numPossibleRecordSolutions() }
  val product = numberPossibleRecordSolutions.reduce { acc, i -> acc * i }
  println("Product of part 1 possible record solution counts: $product")

  val singleRace = parseRaces(lines.map { it.replace(" ", "") }).first()
  println("Part 2 possible record solutions: ${singleRace.numPossibleRecordSolutions()}")
}

fun parseRaces(lines: List<String>): List<BoatRace> {
  val times = lines.first().parseLongs()
  val records = lines.last().parseLongs()
  return times.indices.map { BoatRace(times[it], records[it]) }
}

data class BoatRace(
  val raceTime: Long,
  val recordDistance: Long,
) {

  fun solutions() = (0..raceTime).map { RaceSolution(it, (raceTime-it)*it) }
  fun numPossibleRecordSolutions() = solutions().filter { it.distance > recordDistance }.size
}

data class RaceSolution(
  val chargeTime: Long,
  val distance: Long,
)