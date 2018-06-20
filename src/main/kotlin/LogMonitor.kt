import java.io.BufferedReader
import java.io.FileReader

fun main(args: Array<String>) {

    val fileReader = FileReader("c:/temp/logSample/test.log")
    val bufferReader = BufferedReader(fileReader)

    while (true) {
        val line = bufferReader.readLine()
        if (line.isNullOrBlank()) {
            Thread.sleep(1*1000)
        } else {
            println(line.trimEnd())
        }
    }
}