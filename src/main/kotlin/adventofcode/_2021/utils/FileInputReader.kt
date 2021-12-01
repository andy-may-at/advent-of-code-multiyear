package adventofcode._2021.utils


import java.io.File
import java.io.FileNotFoundException
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Stream

class FileInputReader(dayNumber: Number, readTestFile: Boolean = false) {

    private val inputFile: File = File(getResourceUrlForFile(getResourceFilenameForDay(dayNumber, readTestFile)).toURI())
    private val inputFilePath = getFilePath(getResourceFilenameForDay(dayNumber, readTestFile))


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
        return convertStreamToSequence(streamParsedEntriesFromInput(entryParser))
    }

    fun <T> streamParsedEntriesFromInput(entryParser: (String) -> T): Stream<T> {
        return streamLinesFromInput().map(entryParser)
    }

    fun streamLinesFromInput(): Stream<String> {
        return Files.lines(inputFilePath)
    }

    companion object {

        fun <T> convertStreamToSequence(stream: Stream<T>): Sequence<T>  {
            return Sequence { stream.iterator() }
        }

        private fun getFilePath(resourceFilename: String): Path {
            return Paths.get(getResourceUrlForFile(resourceFilename).toURI())
        }

        private fun getResourceUrlForFile(filename: String): URL =
                FileInputReader::class.java.classLoader.getResource(filename)
                    ?: throw FileNotFoundException("$filename does not exist")

        private fun getResourceFilenameForDay(dayNumber: Number, useTestFile: Boolean = false): String {
            val extension = if(useTestFile) ".test.txt" else ".txt"
            return "day-${dayNumber}" + extension
        }
    }
}