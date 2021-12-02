package adventofcode._2021.day01

import adventofcode._2021.testutils.DayRunnerTestWrapper

class Day1Test: DayRunnerTestWrapper(
    runnerProvider = {readTestFile -> Day1Runner(readTestFile)},
    expectedPart1 = 7,
    expectedPart2 = 5
)