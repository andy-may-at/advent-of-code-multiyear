package adventofcode._2021.utils

abstract class DayRunner(dayNumber: Int, readTestFile: Boolean) {
    val inputReader = FileInputReader(dayNumber = dayNumber, readTestFile)

    fun printResults() {
        println("Part 1 = ${part1()}")
        println("Part 2 = ${part2()}")
    }

    abstract fun part1(): Int
    abstract fun part2(): Int
}
