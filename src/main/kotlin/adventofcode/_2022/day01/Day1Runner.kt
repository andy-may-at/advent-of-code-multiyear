package adventofcode._2022.day01

import adventofcode.utils.DayRunner
import adventofcode.utils.LinesToIntProcessor


fun main() {
    Day1Runner().printResults()
}

class Day1Runner(readTestFile: Boolean = false) : DayRunner(year = 2022, dayNumber = 1, readTestFile = readTestFile) {
    init {
        part1LineProcessor = buildLineProcessor(1)
        part2LineProcessor = buildLineProcessor(3)
    }

    private fun buildLineProcessor(numElves: Int): LinesToIntProcessor = { calories: Sequence<String> ->

        val elves: MutableList<Elf> = mutableListOf()
        var currentElf: Elf = Elf(0)

        elves.add(currentElf)
        calories.forEach {c ->
            if (c.isNullOrBlank()) {
                currentElf = Elf(0)
                elves.add(currentElf)
            } else {
                currentElf.calories += c.toInt()
            }
        }
        elves.sortByDescending { it.calories }
        elves.take(numElves).sumOf { it.calories }

    }

    data class Elf(var calories: Int)
}
