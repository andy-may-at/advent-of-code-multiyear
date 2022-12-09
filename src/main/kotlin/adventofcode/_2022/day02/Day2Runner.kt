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
        SCISSORS(3);



        companion object {
            fun fromString(s: String): Play = when(s) {
                "A","X" -> ROCK
                "B","Y" -> PAPER
                "C","Z" -> SCISSORS
                else -> {throw IllegalArgumentException()}
            }

            fun thatBeats(play: Play): Play {
                return values()[(play.ordinal + 1) % 3]
            }

            fun thatLosesTo(play: Play): Play {
                return values()[(play.ordinal + 2) % 3]
            }
        }
    }
    enum class Outcome(val value: Int) {
        WIN(6),
        DRAW(3),
        LOSS(0);

        companion object {
            fun fromString(s: String): Outcome = when(s) {
                "X" -> LOSS
                "Y" -> DRAW
                "Z" -> WIN
                else -> {throw IllegalArgumentException()}
            }
        }
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

        val result: Outcome =
            when (myPlay) {
                Play.thatBeats(theirPlay) -> {
                    Outcome.WIN
                }
                Play.thatLosesTo(theirPlay) -> {
                    Outcome.LOSS
                }
                else -> {
                    Outcome.DRAW
                }
            }

        return myPlay.value + result.value
    }

    private fun buildPart1LineProcessor(): LinesToIntProcessor = { rounds: Sequence<String> ->

        fun parseRound(round: String): List<Play> {
            return round.split(" ").map { Play.fromString(it) }.take(2)
        }

        val answer: Int = rounds
            .map { parseRound(it) }
            .map { scoreRound(it) }
            .sum()

        answer
    }
    private fun buildPart2LineProcessor(): LinesToIntProcessor = { rounds: Sequence<String> ->

        data class Round (val theirPlay: Play, val neededOutcome: Outcome)


        fun parseRound(round: String): Round {
            val parts = round.split(" ")
            return Round(Play.fromString(parts[0]), Outcome.fromString(parts[1]))
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
