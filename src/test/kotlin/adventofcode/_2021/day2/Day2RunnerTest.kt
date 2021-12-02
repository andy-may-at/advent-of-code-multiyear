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
    internal fun calcPart1Distance_getsExpectedResult_fromTestFile() {
        assertThat(runner.calcPart1Distance()).isEqualTo(150)
    }
    @Test
    internal fun calcPart2Distance_getsExpectedResult_fromTestFile() {
        assertThat(runner.calcPart2Distance()).isEqualTo(900)
    }

}