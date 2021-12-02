package adventofcode._2021.day01

import adventofcode._2021.utils.DayRunner


fun main() {
    Day1Runner().printResults()
}

class Day1Runner(readTestFile: Boolean = false) : DayRunner(1, readTestFile) {

    override fun part1(): Int = countNumIncreases(windowSize = 1)
    override fun part2(): Int = countNumIncreases(windowSize = 3)

    private fun countNumIncreases(windowSize: Int): Int {
        return inputReader
            .useInputInts {depths: Sequence<Int> ->
                depths
                    .windowed(size = windowSize, transform = List<Int>::sum)
                    .zipWithNext()
                    .count { (lastDepthSum, thisDepthSum) -> lastDepthSum < thisDepthSum }
            }
    }
}