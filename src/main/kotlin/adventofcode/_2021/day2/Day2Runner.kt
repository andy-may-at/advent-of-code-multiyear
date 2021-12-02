package adventofcode._2021.day2

import adventofcode._2021.utils.FileInputReader
import java.lang.IllegalArgumentException


fun main() {
    val runner = Day2Runner()
    println("Part 1 Distance = ${runner.calcPart1Distance()}")
    println("Part 2 Distance = ${runner.calcPart2Distance()}")
}

class Day2Runner(readTestFile: Boolean = false) {
    private val inputReader = FileInputReader(dayNumber = 2, readTestFile)

    private val part1Rule = { state: State, m: Move, ->
        when(m.direction) {
            Direction.FORWARD -> state.copy(horiz = state.horiz + m.distance)
            Direction.DOWN -> state.copy(depth = state.depth + m.distance)
            Direction.UP -> state.copy(depth = state.depth - m.distance)
        }
    }

    private val part2Rule = { state: State, m: Move, ->
        when(m.direction) {
            Direction.FORWARD -> state.copy(horiz = state.horiz + m.distance, depth = state.depth + (state.aim * m.distance))
            Direction.DOWN -> state.copy(aim = state.aim + m.distance)
            Direction.UP -> state.copy(aim = state.aim - m.distance)
        }
    }

    fun calcPart1Distance(): Int {
        return applyMovesFromInput(part1Rule).product
    }

    fun calcPart2Distance(): Int {
        return applyMovesFromInput(part2Rule).product
    }

    private fun applyMovesFromInput(operation: (State, Move) -> State): State {
        return inputReader.useInputLines {lines ->
            lines
                .map{ Move.fromString(it)}
                .fold(State(), operation)
        }
    }

    data class State (val aim: Int = 0, val horiz: Int = 0, val depth: Int = 0) {
        val product: Int = horiz * depth
    }

    data class Move(val direction: Direction, val distance: Int) {
        companion object {
            fun fromString(str: String): Move {
                val parts = str.split(" ")
                val direction = Direction.fromString(parts[0])
                val distance = parts[1].toInt()

                if(direction == null) {
                    throw IllegalArgumentException("invalid direction in move: $this")
                }
                return Move(direction, distance)
            }
        }
    }

    enum class Direction(private val str: String) {
        FORWARD("forward"),
        DOWN("down"),
        UP("up");

        companion object {
            fun fromString(string: String): Direction? {
                return values().firstOrNull { it.str == string }
            }
        }
    }
}
