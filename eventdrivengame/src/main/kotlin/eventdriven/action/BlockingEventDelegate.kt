package eventdriven.action

import com.google.inject.Inject
import kotlin.reflect.KFunction
import kotlin.reflect.jvm.isAccessible

/**
 * Blocks until an event has been received. See also [EventDelegate].
 */
internal class BlockingEventDelegate @Inject constructor(
    private val actionFactory: EventDrivenActionFactory
) : EventDelegate {

    override suspend fun waitAndExecute(
        invokingPlayer: EventDrivenGamePlayer,
        function: KFunction<Unit>,
        functionName: String
    ) {
        val action = actionFactory.create(functionName)

        invokingPlayer.installAction(action)
        val params = action.getInvocation()
        invokingPlayer.uninstallAction(action)

        // TODO invoke without player if not needed
        callFunctionWithArgs(function, invokingPlayer, *params)
    }

    private fun callFunctionWithArgs(function: KFunction<Unit>, vararg args: Any?) {
        function.isAccessible = true
        function.call(*args)
        function.isAccessible = false
    }
}