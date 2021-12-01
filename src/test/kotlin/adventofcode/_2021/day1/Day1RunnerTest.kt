package adventofcode._2021.day1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day1RunnerTest {

    private val testFileName = "day_1.test.txt"

    @Test
    internal fun countNumIncreases_getsExpectedResult_forWindowSize1_fromTestFile() {
        assertThat(Day1Runner(testFileName).countNumIncreases(windowSize = 1)).isEqualTo(7)
    }

    @Test
    internal fun countNumIncreases_getsExpectedResult_forWindowSize3_fromTestFile() {
        assertThat(Day1Runner(testFileName).countNumIncreases(windowSize = 3)).isEqualTo(5)
    }

}