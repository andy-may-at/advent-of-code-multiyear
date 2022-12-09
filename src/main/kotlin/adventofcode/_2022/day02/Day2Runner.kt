package adventofcode._2022.day02

import adventofcode.utils.DayRunner
import adventofcode.utils.LinesToIntProcessor
import java.lang.IllegalArgumentException


fun main() {
    Day2Runner().printResults()
}


class Day2Runner(readTestFile: Boolean = false) : DayRunner(year = 2022, dayNumber = 2, readTestFile = readTestFile) {
    init {
        part1LineProcessor = buildPart1LineProcessor()
        part2LineProcessor = buildPart2LineProcessor()
    }

    enum class Play(val value: Int ) {
        ROCK(1),
        PAPER(2),
        SCISSORS(3)
    }
    enum class Outcome(val value: Int) {
        WIN(6),
        DRAW(3),
        LOSS(0)
    }

    private val winnersToLosers = mapOf(
        Play.ROCK     to Play.SCISSORS,
        Play.PAPER    to Play.ROCK,
        Play.SCISSORS to Play.PAPER,
    )
    private val losersToWinners = mapOf(
         Play.SCISSORS to Play.ROCK,
         Play.ROCK     to Play.PAPER,
         Play.PAPER    to Play.SCISSORS,
    )

    private fun scoreRound(plays: List<Play>): Int {
        val theirPlay = plays[0]
        val myPlay = plays[1]

        val resultScore = when (theirPlay) {
            winnersToLosers[myPlay] -> {
                Outcome.WIN.value
            }
            losersToWinners[myPlay] -> {
                Outcome.LOSS.value
            }
            else -> {
                Outcome.DRAW.value
            }
        }

        return myPlay.value + resultScore
    }

    private fun buildPart1LineProcessor(): LinesToIntProcessor = { rounds: Sequence<String> ->

        fun parsePlay(play: String): Play {
            return when(play) {
                "A","X" -> Play.ROCK
                "B","Y" -> Play.PAPER
                "C","Z" -> Play.SCISSORS
                else -> {throw IllegalArgumentException()}
            }
        }

        fun parseRound(round: String): List<Play> {
            return round.split(" ").map { parsePlay(it) }.take(2)
        }

        val answer: Int = rounds
            .map { parseRound(it) }
            .map { scoreRound(it) }
            .sum()

        answer
    }
    private fun buildPart2LineProcessor(): LinesToIntProcessor = { rounds: Sequence<String> ->

        data class Round (val theirPlay: Play, val neededOutcome: Outcome)

        fun parsePlay(play: String): Play {
            return when(play) {
                "A" -> Play.ROCK
                "B" -> Play.PAPER
                "C" -> Play.SCISSORS
                else -> {throw IllegalArgumentException()}
            }
        }

        fun parseNeededOutcome(play: String): Outcome {
            return when(play) {
                "X" -> Outcome.LOSS
                "Y" -> Outcome.DRAW
                "Z" -> Outcome.WIN
                else -> {throw IllegalArgumentException()}
            }
        }

        fun parseRound(round: String): Round {
            val parts = round.split(" ")
            return Round(parsePlay(parts[0]), parseNeededOutcome(parts[1]))
        }



        fun determineMyPlay(round: Round): Play {
            val myPlay = when (round.neededOutcome) {
                Outcome.DRAW -> {
                    round.theirPlay
                }
                Outcome.WIN -> {
                    losersToWinners[round.theirPlay]
                }
                else -> {
                    winnersToLosers[round.theirPlay]
                }
            }

            return myPlay!!
        }

        fun scoreRound(round: Round): Int {
            return round.neededOutcome.value + determineMyPlay(round).value
        }

        val answer: Int = rounds
            .map { parseRound(it) }
            .map { scoreRound(it) }
            .sum()

        answer
    }

}
