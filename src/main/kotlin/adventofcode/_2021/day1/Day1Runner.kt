package adventofcode._2021.day1

import adventofcode._2021.utils.FileReader


fun main() {
    val runner = Day1Runner("day-1.txt")
    println("Num increases in depth = ${runner.countNumIncreases(windowSize = 1)}")
    println("Num increases in depth based on window size 3 = ${runner.countNumIncreases(windowSize = 3)}")
}

class Day1Runner(private val filename: String) {
    private val depths: Iterable<Int> = FileReader.iterateOverNumbersInFile(filename)

    fun countNumIncreases(windowSize: Int): Int {
        val windowSums = depths.windowed(size = windowSize).map { elements -> elements.sum() }
        return windowSums.windowed(size = 2).count { pair -> pair[0] < pair[1] }
    }
}