package chat

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import io.ktor.http.cio.websocket.Frame

interface CustomWebsocketFrame {

    fun toBinaryFrame(): Frame.Binary {
        return Frame.Binary(true, this.convertToBytes())
    }

    private fun convertToBytes(): ByteArray {
        val obj = this
        ByteArrayOutputStream().use { bos ->
            ObjectOutputStream(bos).use { out ->
                out.writeObject(obj)
                return bos.toByteArray()
            }
        }
    }

    companion object {
        fun convertFromBytes(bytes: ByteArray): CustomWebsocketFrame {
            ByteArrayInputStream(bytes).use { bis ->
                ObjectInputStream(bis).use { ois ->
                    return ois.readObject() as CustomWebsocketFrame
                }
            }
        }
    }
}