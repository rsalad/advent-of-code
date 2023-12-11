package aoc2023.day3

import aoc2023.util.FileReader

/**
 * Day 3: Gear Ratios
 */
fun main() {
  val schematic = FileReader().readLines("day3-gear-ratios.txt")

  val partNumbers = EngineSchematic().parts(schematic)
  println("Sum of part 1 part numbers: ${partNumbers.sumOf { it.number }}")

  val gearRatios = EngineSchematic().gearRatios(schematic)
  println("Sum of part 2 gear ratios: ${gearRatios.sum()}")
}

class EngineSchematic {

  companion object {
    private val numberPattern = Regex("\\d+")
    private val symbolPattern = Regex("[^\\d.]")
    private val gearPattern = Regex("\\*")
  }

  fun parts(schematic: List<String>): List<Part> {
    val symbols = schematic.flatMapIndexed { index, line ->
      symbolPattern.findAll(line).map { index to it.range.first }
    }.toSet()

    return schematic.flatMapIndexed { index, line ->
      numberPattern.findAll(line)
        .filter { it.bordersSymbol(index, symbols) }
        .map { Part(it.value.toInt(), index, it.range.first, it.range.last) }
    }
  }

  fun gearRatios(schematic: List<String>): List<Int> {
    val parts = parts(schematic)

    return schematic.flatMapIndexed { index, line ->
      gearPattern.findAll(line)
        .map { it.adjacentParts(index, parts) }
        .filter { it.size == 2 }
        .map { it[0].number * it[1].number }
    }
  }

  private fun MatchResult.bordersSymbol(lineNumber: Int, symbols: Set<Pair<Int, Int>>): Boolean {
    return (lineNumber - 1 .. lineNumber + 1).any { rowNum ->
      (range.first - 1 .. range.last + 1).any { colNum ->
        symbols.contains(rowNum to colNum)
      }
    }
  }

  private fun MatchResult.adjacentParts(lineNumber: Int, parts: List<Part>): List<Part> {
    return parts.filter { part ->
      (part.schematicLineNumber - 1 .. part.schematicLineNumber + 1).any { rowNum ->
        (part.schematicLineStart - 1 .. part.schematicLineEnd + 1).any { colNum ->
          rowNum == lineNumber && colNum == this.range.first
        }
      }
    }
  }
}

data class Part(
  val number: Int,
  val schematicLineNumber: Int,
  val schematicLineStart: Int,
  val schematicLineEnd: Int,
)
