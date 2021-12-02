package adventofcode._2021.day03

import adventofcode._2021.testutils.DayRunnerTestWrapper
import adventofcode._2021.testutils.Expected

class Day3RunnerTest: DayRunnerTestWrapper(
    runnerProvider = { readTestFile -> Day3Runner(readTestFile) },
    Expected(
        part1Test = -1,
        part2Test = -1,
        part1Answer = -1,
        part2Answer = -1,
    ),
)