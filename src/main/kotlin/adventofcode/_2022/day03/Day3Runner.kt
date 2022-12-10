package adventofcode._2022.day03

import adventofcode.utils.DayRunner
import adventofcode.utils.LinesToIntProcessor
import java.lang.IllegalArgumentException
import java.util.*

fun main() {
    Day3Runner().printResults()
}

class Day3Runner(readTestFile: Boolean = false) : DayRunner(year = 2022, dayNumber = 3, readTestFile = readTestFile) {
    init {
        part1LineProcessor = buildPart1LineProcessor()
        part2LineProcessor = buildPart2LineProcessor()
    }

    private fun buildPart1LineProcessor(): LinesToIntProcessor = { rucksacks: Sequence<String> ->
        rucksacks
            .map { it.asSequence().map { char -> toPriority(char) }.toList() }
            .map { it.chunked(it.count() / 2) }
            .map { it[0].intersect(it[1].toSet()).sum() }
            .sum()
    }

    private fun buildPart2LineProcessor(): LinesToIntProcessor = { rucksacks: Sequence<String> ->

        12345
    }

    companion object {
        fun toPriority(c: Char): Int {
            return if (c.isUpperCase()) {
                c.code - 'A'.code + 27
            } else {
                c.code - 'a'.code + 1
            }
        }
    }
}
