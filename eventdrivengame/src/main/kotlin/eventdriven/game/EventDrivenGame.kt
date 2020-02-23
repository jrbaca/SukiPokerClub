package eventdriven.game


/**
 * An event driven game executed from method [run]. Start the game using an [EventDrivenGameExecutor] and
 * signify events using [eventdriven.action.EventDelegate].
 */
interface EventDrivenGame {

    /**
     * Main game content.
     */
    suspend fun run()
}