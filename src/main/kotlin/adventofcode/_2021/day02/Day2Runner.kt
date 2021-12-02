package adventofcode._2021.day02

import adventofcode._2021.day02.Direction.DOWN
import adventofcode._2021.day02.Direction.FORWARD
import adventofcode._2021.day02.Direction.UP
import adventofcode._2021.utils.DayRunner
import adventofcode._2021.utils.LinesToIntProcessor


fun main() {
    Day2Runner().printResults()
}

typealias MoveApplier = (State, Move) -> State

class Day2Runner(readTestFile: Boolean = false): DayRunner(2, readTestFile) {

    private val part1MoveApplier: MoveApplier = { state: State, m: Move, ->
        when(m.direction) {
            FORWARD -> state.copy(horiz = state.horiz + m.distance)
            DOWN -> state.copy(depth = state.depth + m.distance)
            UP -> state.copy(depth = state.depth - m.distance)
        }
    }

    private val part2MoveApplier: MoveApplier = { state: State, m: Move, ->
        when(m.direction) {
            FORWARD -> state.copy(
                horiz = state.horiz + m.distance,
                depth = state.depth + (state.aim * m.distance))
            DOWN -> state.copy(aim = state.aim + m.distance)
            UP -> state.copy(aim = state.aim - m.distance)
        }
    }

    init {
        part1LineProcessor = buildLineProcessor(part1MoveApplier)
        part2LineProcessor = buildLineProcessor(part2MoveApplier)
    }

    private fun buildLineProcessor(moveApplier: MoveApplier): LinesToIntProcessor {
        return { lines:Sequence<String> ->
            lines
                .map { Move.fromString(it) }
                .fold(State(), moveApplier)
                .product
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

data class Move(val direction: Direction, val distance: Int) {
    companion object {
        fun fromString(str: String): Move {
            val parts = str.split(" ")
            val direction = Direction.fromString(parts[0])
            val distance = parts[1].toInt()

            if(direction == null) {
                throw IllegalArgumentException("invalid direction in move: $this")
            }
            return Move(direction = direction, distance = distance)
        }
    }
}

data class State (val aim: Int = 0, val horiz: Int = 0, val depth: Int = 0) {
    val product: Int = horiz * depth
}