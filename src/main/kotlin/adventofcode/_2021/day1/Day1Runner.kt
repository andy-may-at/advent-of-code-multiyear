package adventofcode._2021.day1

import adventofcode._2021.utils.FileInputReader


fun main() {
    val runner = Day1Runner()
    println("Num increases in depth = ${runner.countNumIncreases(windowSize = 1)}")
    println("Num increases in depth based on window size 3 = ${runner.countNumIncreases(windowSize = 3)}")
}

class Day1Runner(readTestFile: Boolean = false) {
    private val inputReader = FileInputReader(dayNumber = 1, readTestFile)

    fun countNumIncreases(windowSize: Int): Int {
        return inputReader
            .useInputInts {depths: Sequence<Int> ->
                depths
                    .windowed(size = windowSize, transform = List<Int>::sum)
                    .zipWithNext()
                    .count { (lastDepthSum, thisDepthSum) -> lastDepthSum < thisDepthSum }
            }
    }
}