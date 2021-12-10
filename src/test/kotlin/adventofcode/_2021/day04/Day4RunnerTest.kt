package adventofcode._2021.day04

import adventofcode.testutils.DayRunnerTestWrapper
import adventofcode.testutils.Expected

class Day4RunnerTest: DayRunnerTestWrapper(
    runnerProvider = { readTestFile -> Day4Runner(readTestFile) },
    Expected(
        part1Test = 4512,
        part1Answer = 16716,
        part2Test = 1924,
        part2Answer = 4880,
    ),
) {

}