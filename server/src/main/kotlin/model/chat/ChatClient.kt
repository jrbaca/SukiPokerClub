package model.chat

import io.ktor.http.cio.websocket.DefaultWebSocketSession
import java.util.concurrent.atomic.AtomicInteger

class ChatClient(
    val webSocketSession: DefaultWebSocketSession
) {
    val id = lastId.getAndIncrement()
    val name = "user$id"

    override fun toString(): String {
        return name
    }

    companion object {
        var lastId = AtomicInteger(0)
    }
}