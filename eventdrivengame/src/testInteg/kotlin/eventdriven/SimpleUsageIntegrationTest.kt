package eventdriven

import com.google.inject.Guice.createInjector
import eventdriven.action.EventDelegate
import eventdriven.action.EventDrivenGamePlayer
import eventdriven.game.EventDrivenGame
import eventdriven.game.EventDrivenGameExecutor
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.util.function.Consumer

class SimpleUsageIntegrationTest {

    // TODO test for game termination on main thread termination

    @Test
    fun testThreeEchos() {
        val injector = createInjector(EventDrivenGameModule())
        val executor = injector.getInstance(EventDrivenGameExecutor::class.java)
        val eventDelegate = injector.getInstance(EventDelegate::class.java)

        @Suppress("UNCHECKED_CAST")
        val consumer = mock(Consumer::class.java) as Consumer<String>
        val player = EventDrivenGamePlayer()
        val game = EchoGame(eventDelegate, consumer, player)

        executor.launch(game)

        val echoStrings = arrayOf("Hello", "world", "!")

        for (str in echoStrings) {
            waitForPlayerToHaveAction(player)
            player.actions.first().executeWithArgs(str)
            verify(consumer, timeout(1000)).accept(str)
        }
    }

    private fun waitForPlayerToHaveAction(player: EventDrivenGamePlayer) {
        while (player.actions.isEmpty()) {
            Thread.sleep(50)
        }
    }
}

class EchoGame(
    private val eventDelegate: EventDelegate,
    private val consumer: Consumer<String>,
    private val player: EventDrivenGamePlayer
) : EventDrivenGame, EventDelegate by eventDelegate {

    override suspend fun run() {
        while (true) {
            waitAndExecute(player, ::echo)
        }
    }

    // TODO don't require player as first arg
    private fun echo(player: EventDrivenGamePlayer, string: String) {
        consumer.accept(string)
    }
}