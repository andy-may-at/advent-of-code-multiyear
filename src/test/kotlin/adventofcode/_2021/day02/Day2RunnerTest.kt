package adventofcode._2021.day02

import adventofcode.testutils.DayRunnerTestWrapper
import adventofcode.testutils.Expected

class Day2RunnerTest: DayRunnerTestWrapper(
    runnerProvider = { readTestFile -> Day2Runner(readTestFile) },
    Expected(
        part1Test = 150,
        part2Test = 900,
        part1Answer = 1660158,
        part2Answer = 1604592846,
    ),
)