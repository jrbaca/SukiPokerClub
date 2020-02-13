package model.chat

import chat.ChatMessage
import chat.ConnectMessage
import chat.CustomFrame
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.readBytes
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
                while (true) {
                    val frame = incoming.receive()
                    when (frame) {
                        is Frame.Binary -> {
                            val obj = CustomFrame.convertFromBytes(frame.readBytes())
                            when (obj) {
                                is ConnectMessage -> {
                                } // do nothing, already processed it
                                is ChatMessage -> {
                                    for (other in clients.toList()) {
                                        other.session.outgoing.send(frame.copy()) // need to copy it otherwise it will only be able to be received once
                                    }
                                }
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
