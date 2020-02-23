package eventdriven.action

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import kotlin.reflect.KVisibility

class EventDrivenGamePlayerTest {

    private lateinit var action: EventDrivenAction
    private lateinit var player: EventDrivenGamePlayer

    @BeforeEach
    fun setup() {
        action = mock(EventDrivenAction::class.java)
        player = EventDrivenGamePlayer()
    }

    @Test
    fun canInstallAnAction() {
        assertTrue(player.actions.isEmpty())
        player.installAction(action)
        assertFalse(player.actions.isEmpty())
    }

    @Test
    fun canUninstallAnAction() {
        player.installAction(action)
        assertFalse(player.actions.isEmpty())
        player.uninstallAction(action)
        assertTrue(player.actions.isEmpty())
    }

    @Test
    fun actionsAreVisible() {
        assertEquals(KVisibility.PUBLIC, EventDrivenGamePlayer::actions.visibility)
    }

    @Test
    fun actionsBackingSetIsNotModifiable() {
        assertTrue(player.actions !is MutableSet<*>)
    }
}