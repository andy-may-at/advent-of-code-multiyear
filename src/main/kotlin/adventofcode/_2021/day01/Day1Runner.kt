package adventofcode._2021.day01

import adventofcode.utils.DayRunner
import adventofcode.utils.LinesToIntProcessor


fun main() {
    Day1Runner().printResults()
}

class Day1Runner(readTestFile: Boolean = false) : DayRunner(dayNumber = 1, readTestFile = readTestFile) {
    init {
        part1LineProcessor = buildLineProcessor(windowSize = 1)
        part2LineProcessor = buildLineProcessor(windowSize = 3)
    }

    private fun buildLineProcessor(windowSize: Int): LinesToIntProcessor = {
            depths: Sequence<String> ->
                depths
                    .map(String::toInt)
                    .windowed(size = windowSize, transform = List<Int>::sum)
                    .zipWithNext()
                    .count { (lastDepthSum, thisDepthSum) -> lastDepthSum < thisDepthSum }
    }
}
