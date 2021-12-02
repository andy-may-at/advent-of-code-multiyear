package adventofcode._2021.day03

import adventofcode._2021.utils.DayRunner


fun main() {
    Day3Runner().printResults()
}

class Day3Runner(readTestFile: Boolean = false): DayRunner(3, readTestFile) {

    init {
        part1LineProcessor = Sequence<String>::count
        part2LineProcessor = Sequence<String>::count
    }

}
