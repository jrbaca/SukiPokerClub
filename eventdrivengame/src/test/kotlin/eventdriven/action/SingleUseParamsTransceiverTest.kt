package eventdriven.action

import eventdriven.action.SingleUseParamsTransceiver
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SingleUseParamsTransceiverTest {

//    @Test
//    fun testParamsAreEqual() {
//        runBlocking {
//            val transceiver = SingleUseParamsTransceiver()
//            val sendArgs = arrayOf("test")
//            var receiveArgs: Array<out Any> = arrayOf()
//
//            val sendJob = GlobalScope.launch {
//                transceiver.sendParams(sendArgs)
//            }
//
//            val receiveJob = GlobalScope.launch {
//                receiveArgs = transceiver.receiveParams()
//            }
//
//            sendJob.join()
//            receiveJob.join()
//
//            assertEquals(sendArgs, receiveArgs as Array<*>)
//        }
//    }
//
//    @Test
//    fun testParamContentsAreEqual() {
//        runBlocking {
//            val transceiver = SingleUseParamsTransceiver()
//            val sendArgs = arrayOf("test")
//            var receiveArgs: Array<out Any> = arrayOf()
//
//            val sendJob = GlobalScope.launch {
//                transceiver.sendParams(sendArgs)
//            }
//
//            val receiveJob = GlobalScope.launch {
//                receiveArgs = transceiver.receiveParams()
//            }
//
//            sendJob.join()
//            receiveJob.join()
//
//            assertEquals(sendArgs[0], receiveArgs[0])
//        }
//    }
//
//    @Test
//    fun testDoesntHangIfMultipleSent() {
//        runBlocking {
//            val transceiver = SingleUseParamsTransceiver()
//            val sendArgs = arrayOf("test")
//            var receiveArgs: Array<out Any> = arrayOf()
//
//            val sendJob1 = GlobalScope.launch {
//                transceiver.sendParams(sendArgs)
//            }
//
//            val sendJob2 = GlobalScope.launch {
//                transceiver.sendParams(sendArgs)
//            }
//
//            val receiveJob = GlobalScope.launch {
//                receiveArgs = transceiver.receiveParams()
//            }
//
//            sendJob1.join()
//            sendJob2.join()
//            receiveJob.join()
//
//            assertEquals(sendArgs[0], receiveArgs[0])
//        }
//    }
}