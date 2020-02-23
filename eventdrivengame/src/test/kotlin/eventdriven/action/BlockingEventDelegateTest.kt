package eventdriven.action

import any
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import kotlin.reflect.KFunction

class BlockingEventDelegateTest {

    private lateinit var blockingEventDelegate: BlockingEventDelegate
    private lateinit var player: EventDrivenGamePlayer
    private lateinit var function: KFunction<Unit>
    private lateinit var actionFactory: EventDrivenActionFactory
    private lateinit var action: EventDrivenAction
    private var testBoolean = false

    @BeforeEach
    @Suppress("UNCHECKED_CAST")
    fun setup() {
        player = mock(EventDrivenGamePlayer::class.java)
        function = mock(KFunction::class.java) as KFunction<Unit>
        action = mock(EventDrivenAction::class.java)

        actionFactory = mock(EventDrivenActionFactory::class.java)
        `when`(actionFactory.create(any())).thenReturn(action)

        blockingEventDelegate = BlockingEventDelegate(actionFactory)
    }

    @Test
    fun testWaitsOnAction() {
        runBlocking {
            blockingEventDelegate.waitAndExecute(player, function)
            verify(action).getInvocation()
        }
    }

    @Test
    fun testInvokesPrivateFunction() {
        runBlocking {
            blockingEventDelegate.waitAndExecute(player, ::privateSetTestBooleanToTrue)
            assertTrue(testBoolean)
        }
    }

    // TODO invoke without player
    private fun privateSetTestBooleanToTrue(player: EventDrivenGamePlayer) {
        testBoolean = true
    }
}