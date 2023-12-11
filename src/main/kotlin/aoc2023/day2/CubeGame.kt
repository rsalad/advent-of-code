package aoc2023.day2

import aoc2023.util.FileReader

/**
 * Day 2: Cube Conundrum
 */
fun main() {
  val cubeGame = CubeGame()
  val maxSet = CubeSet(red = 12, green = 13, blue = 14)
  val games = cubeGame.parseGames(FileReader().readLines("day2-cube-conundrum.txt"))

  val validGames = cubeGame.getValidGames(maxSet, games)
  println("Sum of part 1 game ids: ${validGames.sumOf { it.id }}")

  val minimumCubeSetPowersSum = games.sumOf { it.minimumCubeSet().power() }
  println("Sum of part 2 minimum cube set powers: $minimumCubeSetPowersSum")
}

class CubeGame {

  fun parseGames(gameStrings: List<String>) = gameStrings.map { parseGame(it) }

  fun getValidGames(maxSet: CubeSet, games: List<Game>): List<Game> {
    return games.filter {
        it.cubeSets.all { set ->
          set.isSubsetOf(maxSet)
        }
      }
  }

  private fun CubeSet.isSubsetOf(other: CubeSet) = red <= other.red && green <= other.green && blue <= other.blue

  private fun parseGame(input: String) = Game(
    id = gameId.valueFor(input)!!,
    cubeSets = input.substringAfter(":").split(";")
      .map {
        CubeSet(
          red = red.valueFor(it) ?: 0,
          green = green.valueFor(it) ?: 0,
          blue = blue.valueFor(it) ?: 0
        )
      }
  )

  private fun Regex.valueFor(input: String) = find(input)?.groupValues?.get(1)?.toInt()

  companion object {
    private val gameId = Regex("Game (\\d+):")
    private val red = Regex("(\\d+) red", RegexOption.IGNORE_CASE)
    private val green = Regex("(\\d+) green", RegexOption.IGNORE_CASE)
    private val blue = Regex("(\\d+) blue", RegexOption.IGNORE_CASE)
  }
}

data class Game(
  val id: Int,
  val cubeSets: List<CubeSet>
) {
  fun minimumCubeSet() = CubeSet(
    red = cubeSets.maxOf { it.red },
    green = cubeSets.maxOf { it.green },
    blue = cubeSets.maxOf { it.blue },
  )
}

data class CubeSet(
  val red: Int,
  val green: Int,
  val blue: Int,
) {
  fun power() = red * green * blue
}