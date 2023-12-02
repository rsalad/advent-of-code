package aoc2023.day2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CubeGameUnitTest {

  private val cubeGame = CubeGame()
  private val exampleGames = cubeGame.parseGames(
    listOf(
      "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
      "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
      "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
      "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
      "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green",
    )
  )

  @Test
  fun `getValidGames -- with example input -- expected game ids output`() {
    val maxSet = CubeSet(red = 12, green = 13, blue = 14)

    val validGames = CubeGame().getValidGames(maxSet, exampleGames)

    assertThat(validGames.map { it.id }).containsExactly(1, 2, 5)
  }

  @Test
  fun `minimumCubeSet powers -- with example input -- expected sum`() {
    val sumOfMinimumCubeSetPowers = exampleGames.sumOf { it.minimumCubeSet().power() }

    assertThat(sumOfMinimumCubeSetPowers).isEqualTo(2286)
  }
}