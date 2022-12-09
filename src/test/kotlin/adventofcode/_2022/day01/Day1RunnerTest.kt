package adventofcode._2022.day01

import adventofcode.testutils.DayRunnerTestWrapper
import adventofcode.testutils.Expected

class Day1RunnerTest: DayRunnerTestWrapper(
    runnerProvider = { readTestFile -> Day1Runner(readTestFile) },
    Expected(
        part1Test = 24000,
        part2Test = 45000,
        part1Answer = 72070,
        part2Answer = 211805,
    ),
)