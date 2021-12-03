package adventofcode._2021.day01

import adventofcode.testutils.DayRunnerTestWrapper
import adventofcode.testutils.Expected

class Day1RunnerTest: DayRunnerTestWrapper(
    runnerProvider = { readTestFile -> Day1Runner(readTestFile) },
    Expected(
        part1Test = 7,
        part2Test = 5,
        part1Answer = 1167,
        part2Answer = 1130,
    ),
)