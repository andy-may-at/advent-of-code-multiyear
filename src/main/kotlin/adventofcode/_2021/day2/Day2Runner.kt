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

    fun calcPart1Distance(): Int {
        val action = { m: Move,s: State -> m.applyPart1RuleToState(s)}
        return applyMovesFromInput(action).product
    }

    fun calcPart2Distance(): Int {
        val action = { m: Move,s: State -> m.applyPart2RuleToState(s)}
        return applyMovesFromInput(action).product
    }

    private fun applyMovesFromInput(action: (Move, State) -> State): State {
        var state = State(0, 0, 0)
        inputReader
            .getParsedSequenceFromInput { Move.fromString(it) }
            .forEach { state = action.invoke(it, state) }
        return state
    }


    data class State (val aim: Int = 0, val horiz: Int, val depth: Int) {
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

        fun applyPart1RuleToState(state:State): State {
            return when(direction) {
                Direction.FORWARD -> state.copy(horiz = state.horiz + distance)
                Direction.DOWN -> state.copy(depth = state.depth + distance)
                Direction.UP -> state.copy(depth = state.depth - distance)
            }
        }

        fun applyPart2RuleToState(state:State): State {
            return when (direction) {
                Direction.FORWARD -> state.copy(horiz = state.horiz + distance, depth = state.depth + (state.aim * distance))
                Direction.DOWN -> state.copy(aim = state.aim + distance)
                Direction.UP -> state.copy(aim = state.aim - distance)
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
