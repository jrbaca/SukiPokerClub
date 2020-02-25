package eventdriven.action

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SingleUseParamsTransceiverTest {

    @Test
    fun testParamsAreEqual() {
        runBlocking {

            val transceiver = SingleUseParamsTransceiver()
            val sendArgs = arrayOf("test")
            var receiveArgs: Array<out Any> = arrayOf()

            transceiver.sendParams(sendArgs)

            val receiveJob = GlobalScope.launch {
                receiveArgs = transceiver.receiveParams()
            }

            receiveJob.join()
            assertEquals(sendArgs, receiveArgs as Array<*>)
        }
    }

    @Test
    fun testParamContentsAreEqual() {
        runBlocking {
            val transceiver = SingleUseParamsTransceiver()
            val sendArgs = arrayOf("test")
            var receiveArgs: Array<out Any> = arrayOf()

            transceiver.sendParams(sendArgs)

            val receiveJob = GlobalScope.launch {
                receiveArgs = transceiver.receiveParams()
            }

            receiveJob.join()
            assertEquals(sendArgs[0], receiveArgs[0])
        }
    }

    @Test
    fun testDoesntHangIfMultipleSent() {
        runBlocking {
            val transceiver = SingleUseParamsTransceiver()
            val sendArgs = arrayOf("test")
            var receiveArgs: Array<out Any> = arrayOf()

            transceiver.sendParams(sendArgs)
            transceiver.sendParams(sendArgs)

            val receiveJob = GlobalScope.launch {
                receiveArgs = transceiver.receiveParams()
            }

            receiveJob.join()
            assertEquals(sendArgs[0], receiveArgs[0])
        }
    }
}