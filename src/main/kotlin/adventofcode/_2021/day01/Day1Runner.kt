package adventofcode._2021.day01

import adventofcode._2021.utils.DayRunner
import adventofcode._2021.utils.LinesToIntProcessor


fun main() {
    Day1Runner().printResults()
}

class Day1Runner(readTestFile: Boolean = false) : DayRunner(1, readTestFile) {
    init {
        part1LineProcessor = processLines(windowSize = 1)
        part2LineProcessor = processLines(windowSize = 3)
    }

    private fun processLines(windowSize: Int): LinesToIntProcessor = {
            depths: Sequence<String> ->
                depths
                    .map(String::toInt)
                    .windowed(size = windowSize, transform = List<Int>::sum)
                    .zipWithNext()
                    .count { (lastDepthSum, thisDepthSum) -> lastDepthSum < thisDepthSum }
    }
}
