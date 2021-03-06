package models


import Shared
import chat.ChatMessage
import chat.ConnectMessage
import chat.CustomWebsocketFrame
import controllers.ChatController
import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.DefaultClientWebSocketSession
import io.ktor.client.features.websocket.WebSockets
import io.ktor.client.features.websocket.ws
import io.ktor.http.HttpMethod
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.readBytes
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import mu.KotlinLogging

class ChatModel(
    private val chatController: ChatController
) {

    private lateinit var chatSocket: DefaultClientWebSocketSession
    private val log = KotlinLogging.logger {}


    fun connectToChat(serverAddress: String) {
        val client = HttpClient {
            install(WebSockets)
        }

        GlobalScope.launch {
            client.ws(
                method = HttpMethod.Get,
                host = serverAddress,
                port = 8080, path = Shared.CHAT_PATH
            ) {
                chatSocket = this
                send(ConnectMessage().toBinaryFrame())
                log.info { "Connecting to $serverAddress..." }
                runMessageReceiveLoop()
            }
        }
    }

    private suspend fun runMessageReceiveLoop() {
        while (true) {
            when (val frame = chatSocket.incoming.receive()) {
                is Frame.Binary -> {
                    when (val frameObject = CustomWebsocketFrame.convertFromBytes(frame.readBytes())) {
                        is ChatMessage -> processReceivedChatMessage(frameObject)
                    }
                }
            }
        }
    }

    private fun processReceivedChatMessage(msg: ChatMessage) {
        val text = msg.message
        chatController.addToChat(text)

    }

    fun sendChatMessageToServer(msg: String) {
        GlobalScope.launch {
            chatSocket.send(ChatMessage(msg).toBinaryFrame())
        }
    }
}