package eventdriven.action

import kotlin.reflect.KFunction

/**
 * Allows for event driven code within procedural code. Provides methods for waiting on an
 * [EventDrivenGamePlayer] to invoke an action before continuing execution.
 */
interface EventDelegate {

    /**
     * Waits for the specified player to invoke the specified function.
     */
    suspend fun waitAndExecute(
        invokingPlayer: EventDrivenGamePlayer,
        function: KFunction<Unit>,
        functionName: String = function.name
    )
}