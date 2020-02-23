package eventdriven.game


import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.lang.Thread.sleep

class AsyncGameExecutorTest {

    @Test
    fun launchesWithoutBlocking() {

        val signal = mock(List::class.java)
        val game: EventDrivenGame = BlockingGame(signal)

        AsyncGameExecutor().launch(game)
        runBlocking {
            verify(signal, timeout(1000)).size
        }
    }
}

class BlockingGame(val signal: List<*>) : EventDrivenGame {
    override suspend fun run() {
        signal.size
        while (true) {
            sleep(1000)
        }
    }
}