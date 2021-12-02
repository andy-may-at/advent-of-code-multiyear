package adventofcode._2021.day03

import adventofcode._2021.utils.DayRunner


fun main() {
    Day3Runner().printResults()
}

class Day3Runner(readTestFile: Boolean = false): DayRunner(3, readTestFile) {

    override fun part1(): Int {
        return inputReader.getParsedSequenceFromInput { it }.count()
    }

    override fun part2(): Int {
        return inputReader.getParsedSequenceFromInput { it }.count()
    }
}
