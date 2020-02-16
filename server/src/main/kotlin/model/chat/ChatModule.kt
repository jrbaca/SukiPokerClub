package model.chat

import chat.ChatMessage
import chat.ConnectMessage
import chat.CustomFrame
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.http.cio.websocket.DefaultWebSocketSession
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.readBytes
import io.ktor.routing.routing
import io.ktor.websocket.WebSockets
import io.ktor.websocket.webSocket
import mu.KotlinLogging
import java.util.*
import kotlin.collections.LinkedHashSet

private val chatClients = Collections.synchronizedSet(LinkedHashSet<ChatClient>())
private val log = KotlinLogging.logger {}

fun Application.chatModule() {
    install(WebSockets)

    routing {
        webSocket(Shared.CHAT_PATH) {
            val client = ChatClient(this)
            chatClients += client

            log.info { "$client joined the chat" }
            try {
                while (true) waitForMessageAndProcessIt(this)
            } finally {
                chatClients -= client
                log.info { "$client left the chat" }
            }
        }
    }
}

private suspend fun waitForMessageAndProcessIt(webSocketSession: DefaultWebSocketSession) {
    when (val frame = webSocketSession.incoming.receive()) {
        is Frame.Binary -> {
            when (val obj = CustomFrame.convertFromBytes(frame.readBytes())) {
                is ConnectMessage -> return // do nothing, already processed it
                is ChatMessage -> processChatMessage(obj)
            }
        }
    }
}

suspend fun processChatMessage(chatMessage: ChatMessage) {
    for (other in chatClients) {
        // Shouldn't resend the frame, but can repackage or copy it
        other.webSocketSession.outgoing.send(chatMessage.toBinaryFrame())
    }
}