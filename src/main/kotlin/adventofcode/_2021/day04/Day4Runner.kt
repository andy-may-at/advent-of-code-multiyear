package adventofcode._2021.day04

import adventofcode.utils.DayRunner

fun main() {
    Day4Runner().printResults()
}

class Day4Runner(readTestFile: Boolean = false): DayRunner(year = 2021, dayNumber = 4, readTestFile = readTestFile) {

    override fun part1(): Int {
        val (numbersToCall: List<Int>, cards: MutableList<Card>) = parseInput()

        for (number in numbersToCall) {
            val winningScore: Int? = cards.firstNotNullOfOrNull { it.markNumberAndCheckForWinningScore(number) }
            if (winningScore != null) {
                return winningScore
            }
        }
        return -99999999
    }

    override fun part2(): Int {
        val (numbersToCall: List<Int>, cards: MutableList<Card>) = parseInput()

        var lastWinningScore: Int? = null
        for (number in numbersToCall) {
            cards.removeIf {
                val winningScore = it.markNumberAndCheckForWinningScore(number)
                if (winningScore != null) {
                    lastWinningScore = winningScore
                    println("WINNNER !! -> $it ")
                }
                winningScore != null
            }
        }
        return lastWinningScore!!
    }

    fun printAllCards(cards: MutableList<Card>) {
        println(cards.joinToString("\n"))
    }


    private fun parseInput(): Pair<List<Int>, MutableList<Card>> {
        val lines = inputReader.getParsedSequenceFromInput { it }.toList()

        val numbersToCall: List<Int> = lines[0].split(",").map(String::toInt)
        val cardLines = lines.subList(1, lines.size)

        val cards: MutableList<Card> = mutableListOf()

        for (cardNum in 0 until (cardLines.size / 6)) {
            cards.add(
                Card(
                    cardLines.subList((6 * cardNum) + 1, (6 * cardNum) + 6)
                        .map { line -> line.split(" ").filter { it.isNotBlank() }.map { it.toInt() } }
                )
            )
        }
        return Pair(numbersToCall, cards)
    }

    class LineOrColumn (private val numbers: Set<Int>) {
        val unmatchedNumbers: MutableSet<Int> = numbers.toMutableSet()
            get() = field

        fun markNumberAndCheckForWin(number: Int): Boolean {
            unmatchedNumbers.remove(number)
            return unmatchedNumbers.isEmpty()
        }

        override fun toString(): String {
            return "LineOrColumn(numbers=$numbers, unmatchedNumbers=$unmatchedNumbers)"
        }
    }

    class Card(rawLines: List<List<Int>>) {
        private val lines: List<LineOrColumn> = rawLines.map { LineOrColumn( it.toSet()) }
        private val columns: List<LineOrColumn>

        init {
            val rawColumns: List<MutableList<Int>> = listOf(mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf())
            for (line in rawLines) {
                for (i in 0 .. 4) {
                    rawColumns[i].add(line[i])
                }
            }
            columns = rawColumns.map { LineOrColumn( it.toSet()) }
        }


        fun markNumberAndCheckForWinningScore(number: Int): Int? {

            val haveWinningLine = lines.map { it.markNumberAndCheckForWin(number) }.any { it }
            val haveWinningColumn = columns.map { it.markNumberAndCheckForWin(number) }.any { it }

            return if (haveWinningLine || haveWinningColumn) {
                number * lines.map { it.unmatchedNumbers }.flatten().sum()
            } else {
                null
            }
        }

        override fun toString(): String {
            return "Card: \n" + lines.joinToString ("\n\t")
        }
    }
}


