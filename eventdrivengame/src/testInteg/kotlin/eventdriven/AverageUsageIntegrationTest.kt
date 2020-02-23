package eventdriven

import com.google.inject.Guice
import eventdriven.action.EventDelegate
import eventdriven.action.EventDrivenAction
import eventdriven.action.EventDrivenGamePlayer
import eventdriven.game.EventDrivenGame
import eventdriven.game.EventDrivenGameExecutor
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

class AverageUsageIntegrationTest {

    @Test
    fun testThreeActionsWithParams() {

        val injector = Guice.createInjector(EventDrivenGameModule())
        val executor = injector.getInstance(EventDrivenGameExecutor::class.java)
        val eventDelegate = injector.getInstance(EventDelegate::class.java)


        val gameHelper = mock(GameHelper::class.java)
        val john = PlayerWithName("John")
        val alice = PlayerWithName("Alice")
        val mark = PlayerWithName("Mark")

        val game = ClapGame(eventDelegate, gameHelper)

        game.addPlayer(john)
        game.addPlayer(alice)
        game.addPlayer(mark)
        executor.launch(game)

        waitForPlayerToHaveAction(john)
        john.actions.first().executeWithArgs()
        verify(gameHelper, timeout(1000)).jump(john)

        waitForPlayerToHaveAction(alice)
        alice.actions.first().executeWithArgs(Strength.HARD)
        verify(gameHelper, timeout(1000)).clap(alice,
            Strength.HARD
        )

        waitForPlayerToHaveAction(mark)
        mark.actions.first().executeWithArgs(
            Strength.HARD,
            Hand.RIGHT
        )
        verify(gameHelper, timeout(1000)).snap(mark,
            Strength.HARD,
            Hand.RIGHT
        )
    }

    private fun waitForPlayerToHaveAction(player: EventDrivenGamePlayer) {
        while (player.actions.isEmpty()) {
            Thread.sleep(50)
        }
    }
}

open class GameHelper {
    open fun jump(player: PlayerWithName) {
        println("${player.name} jumped!")
    }

    open fun clap(player: PlayerWithName, strength: Strength) {
        println("${player.name} clapped ${strength.str}!")
    }

    open fun snap(player: PlayerWithName, strength: Strength, hand: Hand) {
        println("${player.name} snapped ${strength.str} with their ${hand.str} hand!")
    }
}

class PlayerWithName(val name: String) : EventDrivenGamePlayer()
enum class Strength(val str: String) { HARD("hard"), SOFT("soft") }
enum class Hand(val str: String) { RIGHT("right"), LEFT("left") }



/**
 * Simple game in which players take turns snapping hard or soft
 */
class ClapGame(
    private val eventDelegate: EventDelegate,
    private val gameHelper: GameHelper
) : EventDrivenGame, EventDelegate by eventDelegate {
    private val players = mutableListOf<PlayerWithName>()

    fun addPlayer(eventDrivenGamePlayer: EventDrivenGamePlayer) {
        if (eventDrivenGamePlayer is PlayerWithName) {
            players.add(eventDrivenGamePlayer)
        }
    }

    override suspend fun run() {
        while (true) {
            waitAndExecute(players[0], this::jump)
            waitAndExecute(players[1], this::clap)
            waitAndExecute(players[2], this::snap)
        }
    }

    private fun jump(player: PlayerWithName) {
        gameHelper.jump(player)
    }

    private fun clap(player: PlayerWithName, strength: Strength) {
        gameHelper.clap(player, strength)
    }

    private fun snap(player: PlayerWithName, strength: Strength, hand: Hand) {
        gameHelper.snap(player, strength, hand)
    }
}
