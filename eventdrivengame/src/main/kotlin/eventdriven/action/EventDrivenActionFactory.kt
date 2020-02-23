package eventdriven.action

/**
 * Factory for producing [EventDrivenAction].
 */
internal interface EventDrivenActionFactory {
    fun create(name: String): EventDrivenAction
}