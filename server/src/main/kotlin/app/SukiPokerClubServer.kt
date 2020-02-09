package app

import io.ktor.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import model.chat.chatModule

fun main() {
    embeddedServer(
        Netty,
        port = 8080,
        module = Application::chatModule
    )
        .start(wait = true)
}
