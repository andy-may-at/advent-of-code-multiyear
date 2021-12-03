package adventofcode._2021.day03

import adventofcode.testutils.DayRunnerTestWrapper
import adventofcode.testutils.Expected
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day3RunnerTest: DayRunnerTestWrapper(
    runnerProvider = { readTestFile -> Day3Runner(readTestFile) },
    Expected(
        part1Test = 198,
        part1Answer = 1082324,
        part2Test = 23,
        part2Answer = -1,
    ),
) {

}