package adventofcode._2021.testutils

import adventofcode._2021.utils.DayRunner
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

open class DayRunnerTestWrapper(
    private val runnerProvider: (readTestFile: Boolean) -> DayRunner,
    private val expected: Expected,
) {

    private lateinit var runnerWithTestFile: DayRunner
    private lateinit var runnerWithRealFile: DayRunner

    @BeforeEach
    internal fun setUp() {
        runnerWithTestFile = runnerProvider.invoke(true)
        runnerWithRealFile = runnerProvider.invoke(false)
    }

    @Test
    internal fun part1_getsExpectedResult_fromTestFile() {
        assertThat(runnerWithTestFile.part1())
            .isEqualTo(expected.part1Test)
    }

    @Test
    internal fun part2_getsExpectedResult_fromTestFile() {
        assertThat(runnerWithTestFile.part2())
            .isEqualTo(expected.part2Test)
    }

    @Test
    internal fun part1_getsExpectedResult_fromRealFile() {
        assertThat(runnerWithRealFile.part1())
            .isEqualTo(expected.part1Answer)
    }

    @Test
    internal fun part2_getsExpectedResult_fromRealFile() {
        assertThat(runnerWithRealFile.part2())
            .isEqualTo(expected.part2Answer)
    }

}

