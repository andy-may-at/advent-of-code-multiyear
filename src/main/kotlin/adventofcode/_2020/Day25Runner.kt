package adventofcode._2020

import adventofcode.utils.DayRunner
import adventofcode.utils.LinesToIntProcessor


fun main() {
    Day25Runner().printResults()
}

class Day25Runner(readTestFile: Boolean = false) : DayRunner(year = 2020, dayNumber = 1, readTestFile = readTestFile) {


    override fun part1(): Int {
        val (cardPublicKey, doorPublicKey) = inputReader.getIntSequenceFromInput()
            .zipWithNext()
            .first()



        return 0
    }


}
