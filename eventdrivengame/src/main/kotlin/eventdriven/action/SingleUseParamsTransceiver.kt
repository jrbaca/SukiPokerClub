package eventdriven.action

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking

/**
 * Used for transmitting parameters across threads.
 */
internal class SingleUseParamsTransceiver {

    private val channel = Channel<Array<out Any>>(1)

    /**
     * Receives params, blocking until one has been produced.
     */
    suspend fun receiveParams(): Array<out Any> {
        val params = channel.receive()
        channel.cancel()
        return params
    }

    /**
     * Sends params. Only the first invocation of this across all threads will be received.
     */
    fun sendParams(args: Array<out Any>) {
        channel.offer(args)
    }
}