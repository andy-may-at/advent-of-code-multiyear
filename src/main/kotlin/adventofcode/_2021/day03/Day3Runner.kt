package adventofcode._2021.day03

import adventofcode.utils.DayRunner
import java.util.*
import kotlin.math.pow


fun main() {
    Day3Runner().printResults()
}

typealias IndexAndBits = Pair<Int, IntArray>

class Day3Runner(readTestFile: Boolean = false): DayRunner(year = 2021, dayNumber = 3, readTestFile = readTestFile) {

    init {
        part1LineProcessor = { lines: Sequence<String> ->

            val gammaRateAsIntArray: List<Int> = getGammaRate(lines)
            val epsilonRateAsIntArray: List<Int> = gammaRateAsIntArray.map { (it + 1) % 2 }

            val gammaRateAsNumber = binaryAsIntArrayToDecimalNumber(gammaRateAsIntArray)
            val epsilonRateAsNumber = binaryAsIntArrayToDecimalNumber(epsilonRateAsIntArray)

            val powerConsumption = gammaRateAsNumber * epsilonRateAsNumber

            powerConsumption
        }


        part2LineProcessor = Sequence<String>::count
    }

    private fun getGammaRate(lines: Sequence<String>): List<Int> {
        val initial = Pair(0, intArrayOf())
        val (lastIndex: Int, summedBits: IntArray) = lines
            .map { it.splitToIntArray() }
            .foldIndexed(initial) { idx: Int, accIdxAndBits: IndexAndBits, bits: IntArray ->
                Pair(idx, if (idx == 0) bits else accIdxAndBits.second.add(bits))
            }

        return summedBits.map { bitSum -> if ((bitSum * 2) > (lastIndex + 1)) 1 else 0 }
    }


    companion object {


        fun String.splitToIntArray(): IntArray {
            return this.split("")
                .filter { it.isNotBlank() }
                .map { it.toInt() }
                .toIntArray()
        }

        fun IntArray.add(other: IntArray): IntArray {
            assert(size == other.size)
            return this.mapIndexed { idx, a -> a + other[idx] }.toIntArray()
        }

        fun binaryAsIntArrayToDecimalNumber(binaryAsInts: List<Int>): Int {
            return binaryAsInts
                .mapIndexed { index, bit -> bit * ((2.0).pow(binaryAsInts.size - index - 1)).toInt() }
                .sum()
        }
    }

}


