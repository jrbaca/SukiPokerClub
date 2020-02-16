package controllers

import javafx.fxml.FXML
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import models.ChatModel

class ChatController {

    lateinit var mainController: MainController

    private val chatModel = ChatModel(this)


    @FXML
    private lateinit var chatArea: TextArea

    @FXML
    private lateinit var messageBox: TextField

    fun startConnection(serverAddress: String) {
        chatModel.connectToChat(serverAddress)
    }

    @FXML
    fun messageBoxEnterPressed() {
        val msg = messageBox.text
        messageBox.text = ""
        chatModel.sendChatMessageToServer(msg)
    }

    fun addToChat(str: String) {
        chatArea.text += "$str\n"
        chatArea.scrollTop = Double.MAX_VALUE
    }
}