package adventofcode._2021.utils

typealias LinesToIntProcessor = (Sequence<String>) -> Int

abstract class DayRunner(dayNumber: Int, readTestFile: Boolean) {

    private val inputReader = FileInputReader(dayNumber = dayNumber, readTestFile)

    fun printResults() {
        println("Part 1 = ${part1()}")
        println("Part 2 = ${part2()}")
    }

    open fun part1(): Int {
        return inputReader.useInputLines(part1LineProcessor)
    }

    open fun part2(): Int {
        return inputReader.useInputLines(part2LineProcessor)
    }

    var part1LineProcessor: LinesToIntProcessor = { -9999999 }
    var part2LineProcessor: LinesToIntProcessor = { -9999999 }
}

