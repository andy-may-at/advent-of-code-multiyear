package adventofcode._2021.day2

import adventofcode._2021.utils.FileInputReader
import java.lang.IllegalArgumentException


fun main() {
    val runner = Day2Runner()
    println("Distance = ${runner.calcDistance()}")
}

class Day2Runner(readTestFile: Boolean = false) {
    private val inputReader = FileInputReader(dayNumber = 2, readTestFile)

    fun calcDistance(): Int {
        val (x, y) = inputReader
            .useInputLines { moves: Sequence<String> ->
                moves
                    .map { it.parseMove() }
                    .reduce { acc: Pair<Int, Int>, el: Pair<Int, Int> ->
                        Pair(
                            acc.first + el.first,
                            acc.second + el.second
                        )
                    }
            }
        return x * y
    }

    companion object {

        fun String.parseMove(): Pair<Int, Int> {
            val parts = split(" ")
            val direction = parts[0]
            val distance = parts[1].toInt()

            return when (direction) {
                "forward" -> Pair(distance, 0)
                "down" -> Pair(0, distance)
                "up" -> Pair(0, -1 * distance)
                else -> {
                    throw IllegalArgumentException("invalid direction: $direction")
                }
            }
        }
    }
}
