package eventdriven.action

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking

/**
 * Used for transmitting parameters across threads.
 */
internal class SingleUseParamsTransceiver {

    private val channel = Channel<Array<out Any>>(0)

    /**
     * Receives params, blocking until one has been produced.
     */
    suspend fun receiveParams(): Array<out Any> {
        val params = channel.receive()
        channel.cancel()
        return params
    }

    /**
     * Sends params, blocking until it has been consumed.
     */
    fun sendParams(args: Array<out Any>) {
        // TODO implement this differently. This could block for extremely long periods.
        runBlocking {
            channel.send(args)
        }
    }
}