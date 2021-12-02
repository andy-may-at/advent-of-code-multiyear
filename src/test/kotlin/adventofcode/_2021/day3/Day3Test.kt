package adventofcode._2021.day3

import adventofcode._2021.testutils.DayRunnerTestWrapper

class Day3Test: DayRunnerTestWrapper(
    runnerProvider = {readTestFile -> Day3Runner(readTestFile) },
    expectedPart1 = -1,
    expectedPart2 = -1
)