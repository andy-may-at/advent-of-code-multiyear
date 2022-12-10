package adventofcode._2022.day03


import adventofcode.testutils.DayRunnerTestWrapper
import adventofcode.testutils.Expected
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day3RunnerTest: DayRunnerTestWrapper(
    runnerProvider = { readTestFile -> Day3Runner(readTestFile) },
    Expected(
        part1Test = 157,
        part2Test = 70,
        part1Answer = 7917,
        part2Answer = -11111,
    ),
) {
    @Test
    internal fun priorityParsing() {
        assertThat(Day3Runner.toPriority('a')).isEqualTo(1)
        assertThat(Day3Runner.toPriority('z')).isEqualTo(26)
        assertThat(Day3Runner.toPriority('A')).isEqualTo(27)
        assertThat(Day3Runner.toPriority('Z')).isEqualTo(52)
    }
}