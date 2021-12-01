package adventofcode._2021.utils


import java.io.FileNotFoundException
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors
import java.util.stream.Stream

class FileReader {

    companion object {

        fun iterateOverNumbersInFile(filename: String): Iterable<Int> {
            return Iterable {
                streamNumbersFromFile(filename).iterator()
            }
        }

        fun readNumbersFromFile(filename: String): List<Int> {

            return streamNumbersFromFile(filename).collect(Collectors.toList())
        }

        fun streamNumbersFromFile(filename: String): Stream<Int> {

            return readLinesFromResourceFileAsStream(filename)
                .map(String::toInt)
        }

        private fun readLinesFromResourceFileAsStream(filename: String): Stream<String> {

            val resourceUrl: URL = FileReader::class.java.classLoader.getResource(filename)
                ?: throw FileNotFoundException("$filename does not exist")


            return Files.lines(Paths.get(resourceUrl.toURI()))

        }

    }
}