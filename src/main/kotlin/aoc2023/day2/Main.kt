import aoc2023.day2.CubeGame
import aoc2023.day2.CubeSet
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