package adventofcode._2022.day03


import adventofcode.testutils.DayRunnerTestWrapper
import adventofcode.testutils.Expected

class Day3RunnerTest: DayRunnerTestWrapper(
    runnerProvider = { readTestFile -> Day3Runner(readTestFile) },
    Expected(
        part1Test = 157,
        part2Test = 70,
        part1Answer = 7917,
        part2Answer = -11111,
    ),
)