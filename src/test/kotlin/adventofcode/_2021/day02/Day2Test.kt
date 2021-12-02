package adventofcode._2021.day02

import adventofcode._2021.testutils.DayRunnerTestWrapper

class Day2Test: DayRunnerTestWrapper(
    runnerProvider = {readTestFile -> Day2Runner(readTestFile) },
    expectedPart1 = 150,
    expectedPart2 = 900
)