package adventofcode._2022.day02


import adventofcode.testutils.DayRunnerTestWrapper
import adventofcode.testutils.Expected

class Day2RunnerTest: DayRunnerTestWrapper(
    runnerProvider = { readTestFile -> Day2Runner(readTestFile) },
    Expected(
        part1Test = 15,
        part2Test = 12,
        part1Answer = 13221,
        part2Answer = 13131,
    ),
)