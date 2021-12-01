package adventofcode._2021.day1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day1RunnerTest {

    private lateinit var runner: Day1Runner

    @BeforeEach
    internal fun setUp() {
        runner = Day1Runner(readTestFile = true)
    }

    @Test
    internal fun countNumIncreases_getsExpectedResult_forWindowSize1_fromTestFile() {
        assertThat(runner.countNumIncreases(windowSize = 1)).isEqualTo(7)
    }

    @Test
    internal fun countNumIncreases_getsExpectedResult_forWindowSize3_fromTestFile() {
        assertThat(runner.countNumIncreases(windowSize = 3)).isEqualTo(5)
    }

}