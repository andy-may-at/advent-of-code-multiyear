package adventofcode._2021.utils


import java.io.FileNotFoundException
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Stream

class FileInputReader(private val dayNumber: Number, private val isTest: Boolean = false) {

    private val inputFileUrl = getResourceUrlForFile("day-${dayNumber}${if (isTest) ".test" else ""}.txt")


    fun getIntSequenceFromInput(): Sequence<Int> {
        return getParsedSequenceFromInput(String::toInt)
    }

    fun <T> getParsedSequenceFromInput(entryParser: (String) -> T): Sequence<T> {
        return convertStreamToSequence(streamParsedEntriesFromInput(entryParser))
    }

    fun <T> streamParsedEntriesFromInput(entryParser: (String) -> T): Stream<T> {
        return streamStringsFromInput().map(entryParser)
    }

    fun streamStringsFromInput(): Stream<String> {
        return Files.lines(Paths.get(inputFileUrl.toURI()))
    }


    companion object {
        fun <T> convertStreamToSequence(stream: Stream<T>): Sequence<T>  {
            return Sequence { stream.iterator() }
        }

        private fun getResourceUrlForFile(filename: String): URL =
                FileInputReader::class.java.classLoader.getResource(filename)
                    ?: throw FileNotFoundException("$filename does not exist")
    }
}