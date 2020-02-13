package chat

import java.io.*


class ChatMessage(
    val message: String
) : Serializable, CustomFrame {

    companion object {
        private const val serialVersionUID: Long = -90013188L
    }
}

