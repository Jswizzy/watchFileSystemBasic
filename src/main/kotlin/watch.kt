import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardWatchEventKinds.*
import java.nio.file.WatchService

private fun promt(msg: String): String {
    print("$msg => ")
    return readLine() ?: ""
}

private fun Path.watch(): WatchService {
    return fileSystem.newWatchService().apply {
        register(this, ENTRY_CREATE, OVERFLOW, ENTRY_DELETE, ENTRY_MODIFY)
    }
}

fun main(args: Array<String>) {
    val folder = promt("Enter a folder to watch")
    val path = Paths.get(folder)

    val watcher = path.watch()
    println("Press ctrl+c to exit")

    while (true) {
        //blocks till an event
        val key = watcher.take()

        //go through each event
        key.pollEvents().forEach {
            when (it.kind().name()) {
                "ENTRY_CREATE" -> println("${it.context()} was created")
                "ENTRY_MODIFY" -> println("${it.context()} was modified")
                "OVERFLOW" -> println("${it.context()} overflow")
                "ENTRY_DELETE" -> println("${it.context()} was deleted")
            }
        }
        //watch for future events
        key.reset()
    }
}

