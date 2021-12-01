package adventofcode._2021.day1

import adventofcode._2021.utils.FileReader


fun main() {

    val runner = Day1Runner("day-1.txt")

    println("Num increases in depth = ${runner.countNumIncreases()}")
}

class Day1Runner(private val filename: String) {

    fun countNumIncreases(): Int {
        return FileReader.readNumbersFromFile(filename).size
    }
}