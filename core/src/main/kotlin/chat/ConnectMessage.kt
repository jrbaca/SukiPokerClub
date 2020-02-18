package chat

import java.io.Serializable

class ConnectMessage : Serializable, CustomWebsocketFrame {
    companion object {
        private const val serialVersionUID: Long = -90000034L
    }

}