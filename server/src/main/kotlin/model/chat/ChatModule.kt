package model.chat

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.readText
import io.ktor.routing.routing
import io.ktor.websocket.WebSockets
import io.ktor.websocket.webSocket
import java.util.*
import kotlin.collections.LinkedHashSet

fun Application.chatModule() {
    install(WebSockets)

    routing {
        val clients = Collections.synchronizedSet(LinkedHashSet<ChatClient>())

        webSocket("/chat") {
            val client = ChatClient(this)
            clients += client
            println("$client joined the chat")
            try {
                loop@ while (true) {
                    val frame = incoming.receive()
                    when (frame) {
                        is Frame.Text -> {
                            val text = frame.readText()

                            if (text == "CONNECT") continue@loop

                            val textToSend = "${client.name}: $text"
                            for (other in clients.toList()) {
                                other.session.outgoing.send(Frame.Text(textToSend))
                            }
                        }
                    }
                }
            } finally {
                clients -= client
                println("$client left the chat")
            }
        }
    }
}
