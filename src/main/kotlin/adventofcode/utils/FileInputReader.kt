package adventofcode.utils


import java.io.File
import java.io.FileNotFoundException
import java.net.URI
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Stream

class FileInputReader(year: Number = 2021, dayNumber: Number, readTestFile: Boolean = false) {

    private val inputFileName = getResourceFilenameForDay(year, dayNumber, readTestFile)
    private val inputFileUri = getResourceUriForFile(inputFileName)
    private val inputFilePath = getResourceUriForFile(inputFileName).toPath()
    private val inputFile: File = File(inputFileUri)


    fun <T> useInputLines(block: (Sequence<String>) -> T): T {
        return inputFile.useLines (block = block)
    }

    fun <T> useInputInts(intBlock: (Sequence<Int>) -> T): T {
        val stringBlock: (Sequence<String>) -> T = {
            stringSeq -> intBlock.invoke(stringSeq.map (String::toInt))
        }
        return inputFile.useLines (block = stringBlock)
    }

    fun getIntSequenceFromInput(): Sequence<Int> {
        return getParsedSequenceFromInput(String::toInt)
    }

    fun <T> getParsedSequenceFromInput(entryParser: (String) -> T): Sequence<T> {
        return streamParsedEntriesFromInput(entryParser).toSequence()
    }

    fun <T> streamParsedEntriesFromInput(entryParser: (String) -> T): Stream<T> {
        return streamLinesFromInput().map(entryParser)
    }

    fun streamLinesFromInput(): Stream<String> {
        return Files.lines(inputFilePath)
    }


    companion object {

        fun <T> Stream<T>.toSequence(): Sequence<T>  {
            return Sequence { iterator() }
        }

        private fun URI.toPath(): Path {
            return Paths.get(this)
        }

        private fun getResourceUriForFile(filename: String): URI =
                FileInputReader::class.java.classLoader.getResource(filename)?.toURI()
                    ?: throw FileNotFoundException("$filename does not exist")

        private fun getResourceFilenameForDay(year: Number, dayNumber: Number, useTestFile: Boolean = false): String {
            val extension = if(useTestFile) ".test.txt" else ".txt"
            return "$year/day-" + "%02d".format(dayNumber) + extension
        }
    }
}