package adventofcode._2021.day1

import adventofcode._2021.utils.FileReader


fun main() {

    val runner1 = Day1Runner("day-1.txt", 1)
    val runner2 = Day1Runner("day-1.txt", 3)

    println("Num increases in depth = ${runner1.countNumIncreases()}")
    println("Num increases in depth based on window size 3 = ${runner2.countNumIncreases()}")
}

class Day1Runner(private val filename: String, private val windowSize: Int) {

    private val depths = FileReader.readNumbersFromFile(filename).toList()

    fun countNumIncreases(): Int {

        var numIncreases: Int = 0
        for (i in windowSize until depths.size) {
            if(sumOfWindow(i) < sumOfWindow(i + 1)) {
                numIncreases++
            }
        }
        return numIncreases
    }

    private fun sumOfWindow(offset: Int): Int {
        var sum = 0
        for (i in 1..windowSize) {
            sum += depths[offset - i]
        }
        return sum
    }
}