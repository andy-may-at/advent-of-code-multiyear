package adventofcode._2021.day2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day2RunnerTest {

    private lateinit var runner: Day2Runner

    @BeforeEach
    internal fun setUp() {
        runner = Day2Runner(readTestFile = true)
    }

    @Test
    internal fun calcDistance_getsExpectedResult_fromTestFile() {
        assertThat(runner.calcDistance()).isEqualTo(150)
    }

}