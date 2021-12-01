package adventofcode._2021.utils


import java.io.FileNotFoundException
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Stream

class FileInputReader(private val dayNumber: Number, private val isTest: Boolean = false) {

    private val filename: String = "day-${dayNumber}${if (isTest) ".test" else ""}.txt"
    private val fileUrl = getResourceUrlForFile(filename)

    fun iterateOverNumbersInFile(): Iterable<Int> {
        return convertStreamToIterable { streamFileLines(fileUrl).map(String::toInt) }
    }

    companion object {

        fun <T> convertStreamToIterable(streamSupplier: () -> Stream<T>): Iterable<T>  {
            return Iterable {
                streamSupplier.invoke()
                    .iterator()
            }
        }

        fun streamFileLines(fileURL: URL): Stream<String> {
            return Files.lines(Paths.get(fileURL.toURI()))
        }

        private fun getResourceUrlForFile(filename: String): URL = (FileInputReader::class.java.classLoader.getResource(filename)
            ?: throw FileNotFoundException("$filename does not exist"))

    }
}