package adventofcode._2021.testutils

import adventofcode._2021.utils.Runner
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

open class DayRunnerTestWrapper(
    private val runnerProvider: (readTestFile: Boolean) -> Runner,
    private val expectedPart1: Int,
    private val expectedPart2: Int,
) {

    private lateinit var runner: Runner

    @BeforeEach
    internal fun setUp() {
        runner = runnerProvider.invoke(true)
    }

    @Test
    internal fun part1_getsExpectedResult_fromTestFile() {
        assertThat(runner.part1()).isEqualTo(expectedPart1)
    }

    @Test
    internal fun part2_getsExpectedResult_fromTestFile() {
        assertThat(runner.part2()).isEqualTo(expectedPart2)
    }

}

