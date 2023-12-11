import IslandAlmanac.Companion.fromFileLines
import aoc2023.util.FileReader

/**
 * Day 5: If You Give A Seed A Fertilizer
 */
fun main() {
  val almanacLines = FileReader().readLines("day5.txt")
  val almanac = fromFileLines(almanacLines)

  println("Closest location for part 1: ${almanac.locationsForNeededSeeds().min()}")
  println("Closest location for part 2: ${almanac.minLocationForSeedRanges()}")
}

data class IslandAlmanac(
  private val neededSeeds: List<Long>,
  private val maps: Map<String, List<AlmanacMap>>
) {

  fun locationsForNeededSeeds() = neededSeeds.map { locationForSeed(it) }
  fun minLocationForSeedRanges(): Long {
    return (neededSeeds.indices step 2).map {
      println("Starting index $it")
      val start = neededSeeds[it]
      val range = neededSeeds[it + 1]
      (start ..< start + range).map { seed -> locationForSeed(seed) }.min()
    }.min()
  }

  fun locationForSeed(seed: Long): Long {
    val soil = maps["seed-to-soil"]!!.destinationForSource(seed)
    val fertilizer = maps["soil-to-fertilizer"]!!.destinationForSource(soil)
    val water = maps["fertilizer-to-water"]!!.destinationForSource(fertilizer)
    val light = maps["water-to-light"]!!.destinationForSource(water)
    val temperature = maps["light-to-temperature"]!!.destinationForSource(light)
    val humidity = maps["temperature-to-humidity"]!!.destinationForSource(temperature)
    val location = maps["humidity-to-location"]!!.destinationForSource(humidity)
    return location
  }

  private fun List<AlmanacMap>.destinationForSource(sourceNum: Long) = firstOrNull { it.containsSource(sourceNum) }
    ?.destinationNum(sourceNum)
    ?: sourceNum

  companion object {
    private val numberPattern = Regex("\\d+")
    fun fromFileLines(lines: List<String>): IslandAlmanac {
      val seeds = lines[0].parseNumbers()
      val almanacMaps = mutableMapOf<String, MutableList<AlmanacMap>>()
      var mapKey = ""

      lines.drop(1).filter { it.isNotBlank() }.forEach {
        if (it.endsWith("map:")) {
          mapKey = it.substringBefore(" ")
          almanacMaps[mapKey] = mutableListOf()
        } else {
          val lineNumbers = it.parseNumbers()
          almanacMaps[mapKey]!!.add(AlmanacMap(lineNumbers[1], lineNumbers[0], lineNumbers[2]))
        }
      }
      return IslandAlmanac(seeds, almanacMaps.toMap())
    }

    private fun String.parseNumbers() = numberPattern.findAll(this).map { it.value.toLong() }.toList()
  }
}

data class AlmanacMap(
  val sourceStart: Long,
  val destinationStart: Long,
  val range: Long,
) {
  fun containsSource(sourceNum: Long) = sourceNum in sourceStart..<sourceStart+range
  fun destinationNum(sourceNum: Long): Long {
    if (!containsSource(sourceNum)) throw Exception()
    return destinationStart + (sourceNum - sourceStart)
  }
}
