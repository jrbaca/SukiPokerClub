package eventdriven.action

import com.google.inject.Inject
import com.google.inject.assistedinject.Assisted

internal class EventDrivenActionImpl @Inject constructor(
    private val signalChannel: SingleUseParamsTransceiver,
    @Assisted val name: String
) : EventDrivenAction {

    // TODO should this be merged with SingleUseParamsTransceiver? Needs investigation on the many to many nature.

    override fun executeWithArgs(vararg args: Any) {
        signalChannel.sendParams(args)
    }

    override suspend fun getInvocation(): Array<out Any> {
        return signalChannel.receiveParams()
    }

    override fun toString(): String {
        return name
    }
}

