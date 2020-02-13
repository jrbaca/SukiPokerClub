package models


import chat.ChatMessage
import chat.ConnectMessage
import chat.CustomFrame
import controllers.MainController
import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.DefaultClientWebSocketSession
import io.ktor.client.features.websocket.WebSockets
import io.ktor.client.features.websocket.ws
import io.ktor.http.HttpMethod
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.readBytes
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ChatModel(private val mainController: MainController) {

    private lateinit var chatSocket: DefaultClientWebSocketSession


    fun connectToChat() {
        val client = HttpClient {
            install(WebSockets)
        }

        GlobalScope.launch {
            client.ws(
                method = HttpMethod.Get,
                host = "127.0.0.1",
                port = 8080, path = "/chat"
            ) {
                chatSocket = this
                send(ConnectMessage().toBinaryFrame())

                while (true) {
                    val frame = incoming.receive()
                    when (frame) {
                        is Frame.Binary -> {
                            val obj = CustomFrame.convertFromBytes(frame.readBytes())
                            when (obj) {
                                is ChatMessage -> {
                                    val text = obj.message
                                    mainController.chatArea.text += text + "\n"
                                    mainController.chatArea.scrollTop = Double.MAX_VALUE
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    fun sendMessage() {
        val msg = mainController.messageBox.text
        sendmessageToServer(msg)
        mainController.messageBox.text = ""
    }

    private fun sendmessageToServer(msg: String) {
        GlobalScope.launch {
            chatSocket.send(ChatMessage(msg).toBinaryFrame())
        }
    }
}