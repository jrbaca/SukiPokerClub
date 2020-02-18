package chat

import java.io.*


class ChatMessage(
    val message: String
) : Serializable, CustomWebsocketFrame {

    companion object {
        private const val serialVersionUID: Long = -94783021L
    }
}

