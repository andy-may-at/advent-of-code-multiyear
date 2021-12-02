package adventofcode._2021.utils

abstract class DayRunner(dayNumber: Int, readTestFile: Boolean): Runner {
    val inputReader = FileInputReader(dayNumber = dayNumber, readTestFile)

    fun printResults() {
        println("Part 1 = ${part1()}")
        println("Part 2 = ${part2()}")
    }
}


