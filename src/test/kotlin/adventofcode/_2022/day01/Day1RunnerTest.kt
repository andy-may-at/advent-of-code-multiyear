package adventofcode._2022.day01

import adventofcode.testutils.DayRunnerTestWrapper
import adventofcode.testutils.Expected

class Day1RunnerTest: DayRunnerTestWrapper(
    runnerProvider = { readTestFile -> Day1Runner(readTestFile) },
    Expected(
        part1Test = 24000,
        part2Test = -9999,
        part1Answer = 99999,
        part2Answer = -99999,
    ),
)