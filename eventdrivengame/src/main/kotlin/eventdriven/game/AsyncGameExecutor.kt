package eventdriven.game

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Async executor for [EventDrivenGame]. See also [EventDrivenGameExecutor]
 */
internal class AsyncGameExecutor : EventDrivenGameExecutor {

    /**
     * Launches an [EventDrivenGame] asynchronously and returns the corresponding [Job].
     */
    override fun launch(game: EventDrivenGame): Job {
        return GlobalScope.launch {
            game.run()
        }
    }
}