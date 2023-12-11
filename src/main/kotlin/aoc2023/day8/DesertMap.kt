import DesertMap.Companion.parseMap
import aoc2023.util.FileReader

fun main() {
  val map = parseMap(FileReader().readLines("day8.txt"))

  println("Part 1 number steps: ${map.numberStepsToFinish()}")
  println("Part 2 number steps: ${map.ghostStepsToFinish()}")
}

data class DesertMap(
  val instructions: String,
  val locationDirections: Map<String, LeftRight>
) {

  fun numberStepsToFinish(): Long {
    var instructionCount = 0L
    var instructionIndex = 0
    var currentLocation = "AAA"
    while (currentLocation != "ZZZ") {
      currentLocation = when (instructions[instructionIndex]) {
        'L' -> locationDirections[currentLocation]!!.left
        'R' -> locationDirections[currentLocation]!!.right
        else -> throw Exception()
      }
      if (++instructionIndex == instructions.length) instructionIndex = 0
      instructionCount++
    }
    return instructionCount
  }

  fun ghostStepsToFinish(): Long {
    val lengths = locationDirections.map { it.key }
      .filter { it.endsWith("A") }
      .map {
        var currentLocation = it
        var instructionCount = 0L
        var instructionIndex = 0
        while (!currentLocation.endsWith("Z")) {
          currentLocation = when (instructions[instructionIndex]) {
            'L' -> locationDirections[currentLocation]!!.left
            'R' -> locationDirections[currentLocation]!!.right
            else -> throw Exception()
          }
          if (++instructionIndex == instructions.length) instructionIndex = 0
          instructionCount++
        }
        instructionCount
      }
    return lengths.lcm()
  }

  private fun List<Long>.lcm(): Long {
    if (isEmpty()) return 1
    val head = first()
    val tail = drop(1)
    return tail.lcm().lcm(head)
  }

  private fun Long.lcm(other: Long) = (this * other) / gcd(this, other)

  private fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)


  companion object {
    fun parseMap(lines: List<String>): DesertMap {
      val instructions = lines[0]
      val directions = lines.drop(1)
        .filterNot { it.isBlank() }
        .associate {
          val split = it.split(" = (", ", ", ")")
          split[0] to LeftRight(split[1], split[2])
        }
      return DesertMap(instructions, directions)
    }
  }
}

data class LeftRight(val left: String, val right: String)