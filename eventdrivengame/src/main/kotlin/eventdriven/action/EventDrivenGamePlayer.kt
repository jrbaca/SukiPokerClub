package eventdriven.action

/**
 * A player that can take actions. Is usable on its own, but you may subclass it to include
 * additional functionality.
 */
open class EventDrivenGamePlayer {

    /**
     * Available actions that can be taken. Is managed internally.
     */
    var actions = setOf<EventDrivenAction>()
        private set

    /**
     * Install an action onto this player.
     */
    internal fun installAction(action: EventDrivenAction) {
        actions = actions.plus(action)
    }

    /**
     * Uninstall an action from this player.
     */
    internal fun uninstallAction(action: EventDrivenAction) {
        actions = actions.minus(action)
    }
}