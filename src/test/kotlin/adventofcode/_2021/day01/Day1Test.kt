package adventofcode._2021.day01

import adventofcode._2021.testutils.DayRunnerTestWrapper
import adventofcode._2021.testutils.Expected

class Day1Test: DayRunnerTestWrapper(
    runnerProvider = {readTestFile -> Day1Runner(readTestFile)},
    Expected(
        part1Test = 7,
        part2Test = 5,
        part1Answer = 1167,
        part2Answer = 1130,
    ),
)