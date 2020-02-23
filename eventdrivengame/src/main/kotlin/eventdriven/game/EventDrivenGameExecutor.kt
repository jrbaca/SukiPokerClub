package eventdriven.game

import kotlinx.coroutines.Job

/**
 * An executor for [EventDrivenGame].
 */
interface EventDrivenGameExecutor {

    /**
     * Launches the specified game and returns the corresponding job.
     */
    fun launch(game: EventDrivenGame): Job
}