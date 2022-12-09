package adventofcode._2022.day01

import adventofcode.utils.DayRunner
import adventofcode.utils.LinesToIntProcessor


fun main() {
    Day1Runner().printResults()
}

class Day1Runner(readTestFile: Boolean = false) : DayRunner(year = 2022, dayNumber = 1, readTestFile = readTestFile) {
    init {
        part1LineProcessor = buildLineProcessor()
        part2LineProcessor = { 1 }
    }

    private fun buildLineProcessor(): LinesToIntProcessor = { calories: Sequence<String> ->

        var maxCals = -1
        var currentCals = 0


        calories.forEach {c ->
            if (c.isNullOrBlank()) {
                maxCals = maxOf(maxCals, currentCals)
                currentCals = 0
            } else {
                currentCals += c.toInt()
            }
        }
        maxCals
    }
}
