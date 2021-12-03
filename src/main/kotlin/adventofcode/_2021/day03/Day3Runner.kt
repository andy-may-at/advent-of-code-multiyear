package adventofcode._2021.day03

import adventofcode.utils.DayRunner


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


        part2LineProcessor = { lines: Sequence<String> ->
            // Sort the output once early on, then we can just keep finding the point where 0's turn to 1 at a char index to split the list
            val list = lines.sorted().toList()

            val (indexOfFirstOneAtPosition, numOnesComparedToNumZeros) = examineListAtCharIndex(list, 0)

            val oxygenStartsWithOne: Boolean = numOnesComparedToNumZeros > -1

            val o2List = splitList(list, indexOfFirstOneAtPosition, lookingForOnes = oxygenStartsWithOne)
            val co2List = splitList(list, indexOfFirstOneAtPosition, lookingForOnes = !oxygenStartsWithOne)

            val o2Rating = findStringForPart2(o2List, lookingForMostCommon = true, ifEqualNumbersPickOnes = true, charIndex = 1)
            val co2Rating = findStringForPart2(co2List, lookingForMostCommon = false, ifEqualNumbersPickOnes = false, charIndex = 1)

            o2Rating.toInt(2) * co2Rating.toInt(2)
        }
    }


    fun findStringForPart2(list: List<String>, lookingForMostCommon: Boolean, ifEqualNumbersPickOnes: Boolean, charIndex: Int): String {

        val (indexOfFirstOneAtPosition, numOnesComparedToNumZeros) = examineListAtCharIndex(list, charIndex)
        val keepingOnesWhenSplitting: Boolean = if(numOnesComparedToNumZeros == 0) ifEqualNumbersPickOnes else (lookingForMostCommon == (numOnesComparedToNumZeros > 0))
        val subList = splitList(list, indexOfFirstOneAtPosition, lookingForOnes = keepingOnesWhenSplitting)

        return if(subList.size == 1) {
            subList[0]
        } else {
            findStringForPart2(
                list = subList,
                lookingForMostCommon = lookingForMostCommon,
                ifEqualNumbersPickOnes = ifEqualNumbersPickOnes,
                charIndex = charIndex + 1)
        }
    }

    fun examineListAtCharIndex(list: List<String>, charIndex: Int): Pair<Int, Int> {
        val indexOfFirstOneAtPosition = list.indexOfFirst { string -> string[charIndex] == '1' }
        val numOnes = list.size - indexOfFirstOneAtPosition
        val numOnesComparedToNumZeros = numOnes.compareTo(indexOfFirstOneAtPosition)

        return Pair(indexOfFirstOneAtPosition, numOnesComparedToNumZeros)
    }


    fun splitList(list: List<String>, indexOfFirstOne: Int, lookingForOnes: Boolean): List<String> {
        return if(lookingForOnes) {
            list.subList(indexOfFirstOne, list.size)
        } else {
            list.subList(0, indexOfFirstOne)
        }
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
            return binaryAsInts.joinToString("").toInt(2)
        }
    }

}


