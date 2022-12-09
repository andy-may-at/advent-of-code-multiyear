package adventofcode._2022.day02

import adventofcode.utils.DayRunner
import adventofcode.utils.LinesToIntProcessor
import java.lang.IllegalArgumentException


fun main() {
    Day2Runner().printResults()
}


class Day2Runner(readTestFile: Boolean = false) : DayRunner(year = 2022, dayNumber = 2, readTestFile = readTestFile) {
    init {
        part1LineProcessor = buildLineProcessor()
        part2LineProcessor = buildLineProcessor()
    }

    enum class Play(val value: Int ) {
        ROCK(1),
        PAPER(2),
        SCISSORS(3)
    }

    private val victors = mapOf(
        Play.ROCK to Play.SCISSORS,
        Play.PAPER to Play.ROCK,
        Play.SCISSORS to Play.PAPER,
    )

    private fun parsePlay(play: String): Play {
        return when(play) {
            "A","X" -> Play.ROCK
            "B","Y" -> Play.PAPER
            "C","Z" -> Play.SCISSORS
            else -> {throw IllegalArgumentException()}
        }
    }

    private fun parseRound(round: String): List<Play> {
        return round.split(" ").map { parsePlay(it) }.take(2)
    }

    fun scoreRound(plays: List<Play>): Int {

        val WIN = 6
        val DRAW = 3

        val baseScore = plays[1].value

        val resultScore = if (victors[plays[1]] == plays[0]) {
            WIN
        } else if (victors[plays[0]] == plays[1]) {
            0
        } else {
            DRAW
        }


        return baseScore + resultScore
    }

    private fun buildLineProcessor(): LinesToIntProcessor = { rounds: Sequence<String> ->

        val answer: Int = rounds
            .map { parseRound(it) }
            .map { scoreRound(it) }
            .sum()

        answer
    }





}
