package eventdriven.action

/**
 * An action that can be taken.
 */
interface EventDrivenAction {
    fun executeWithArgs(vararg args: Any)

    suspend fun getInvocation(): Array<out Any>
}