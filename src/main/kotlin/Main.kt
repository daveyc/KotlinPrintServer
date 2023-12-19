import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class PrintServer : CliktCommand() {

    private val host by option("-n", "--host", help="The server host name").default("127.0.0.1")
    private val port by option("-p", "--port" ,help="server port number").int().default(19701)
    private val verbose by option("-v", "--verbose", help = "enable verbose mode").flag()

    override fun run() {
        runBlocking {
            val server = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().bind(host, port)
            if (verbose) println("Started server at ${server.localAddress}")
            while (true) {
                val socket = server.accept()
                launch {
                    if (verbose) println("Socket accepted: ${socket.remoteAddress}")
                    val input = socket.openReadChannel()
                    try {
                        while (input.readUTF8LineTo(System.out, 100000)) {
                            print('\n')
                        }
                    } catch (e: Throwable) {
                        e.printStackTrace()
                        socket.close()
                    }
                }
            }
        }
    }
}

fun main(args: Array<String>) = PrintServer().main(args)