package adventofcode._2021.day03

import adventofcode._2021.utils.DayRunner
import adventofcode._2021.utils.Runner


fun main() {
    Day3Runner().printResults()
}

class Day3Runner(readTestFile: Boolean = false): DayRunner(3, readTestFile), Runner {

    override fun part1(): Int {
        return inputReader.getParsedSequenceFromInput { it }.count()
    }

    override fun part2(): Int {
        return inputReader.getParsedSequenceFromInput { it }.count()
    }
}
