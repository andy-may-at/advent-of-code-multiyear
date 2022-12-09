package adventofcode._2021.day05

import adventofcode.utils.DayRunner


fun main() {
    Day5Runner().printResults()
}


class Day5Runner(readTestFile: Boolean = false): DayRunner(year = 2021, dayNumber = 5, readTestFile = readTestFile) {


    override fun part1(): Int {

        val linesSeq = inputReader.getParsedSequenceFromInput { stringLine ->
            CoordPair(stringLine.split(" -> ").map { Coord(it) })
        }

        val linesWithFromClosestToZero = linesSeq
            .filter { it.isVertical || it.isHorizontal }
            .map(CoordPair::withFromClosestToZeroZero)
            .toList()

        val maxExtentOfCoords = linesWithFromClosestToZero.reduce { maxCoords, line ->
            val minX = minOf(maxCoords.from.x, line.from.x)
            val minY = minOf(maxCoords.from.y, line.from.y)
            val maxX = maxOf(maxCoords.to.x, line.to.x)
            val maxY = maxOf(maxCoords.to.y, line.to.y)

            CoordPair(Coord(minX, minY), Coord(maxX, maxY))
        }

        val gridWidth = 1 + maxExtentOfCoords.maxX - maxExtentOfCoords.minX
        val gridDepth = 1 + maxExtentOfCoords.maxY - maxExtentOfCoords.minY

        val grid: Array<IntArray> = Array(gridWidth) { IntArray(gridDepth) }

        println("Max grid extent = $maxExtentOfCoords")
        println("gridWidth = $gridWidth")
        println("gridDepth = $gridDepth")

        val minGridPoint = Coord(maxExtentOfCoords.minX, maxExtentOfCoords.minY)

        linesWithFromClosestToZero.forEach { line ->
            val adjustedLine = line.withOffsetApplied(minGridPoint)

            for (x in adjustedLine.from.x .. adjustedLine.to.x) {
                for (y in adjustedLine.from.y .. adjustedLine.to.y) {
                    grid[y][x] += 1
                }
            }
        }



        grid.forEach { println (it.contentToString()) }

        return grid.sumOf { gridLine -> gridLine.count { it > 1 } }
    }


    override fun part2(): Int {

        return -999111
    }



    data class CoordPair (val from: Coord, val to: Coord) {
        constructor(coords: List<Coord>): this(coords[0], coords[1])

        val isHorizontal = from.y == to.y
        val isVertical = from.x == to.x

        val maxX = maxOf(from.x, to.x)
        val minX = minOf(from.x, to.x)
        val maxY = maxOf(from.y, to.y)
        val minY = minOf(from.y, to.y)


        fun withOffsetApplied(offsetCoord: Coord): CoordPair {
            return CoordPair(from.minus(offsetCoord), to.minus(offsetCoord))
        }

        fun withFromClosestToZeroZero(): CoordPair {
            return if((from.x + from.y) < (to.x + to.y)) this else CoordPair(to, from)
        }
    }


    data class Coord(val x: Int, val y: Int) {
        constructor(s: String): this(
            s.trim().split(",")[0].toInt(),
            s.trim().split(",")[1].toInt())

        fun minus(other: Coord):Coord {
            return Coord(x - other.x, y - other.y)
        }
    }

}


