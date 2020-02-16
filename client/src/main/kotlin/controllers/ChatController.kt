package controllers

import javafx.fxml.FXML
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import models.ChatModel

class ChatController {

    lateinit var mainController: MainController

    private val chatModel = ChatModel(this)


    @FXML
    lateinit var chatArea: TextArea

    @FXML
    lateinit var messageBox: TextField

    fun startConnection(serverAddress: String) {
        chatModel.connectToChat(serverAddress)
    }

    @FXML
    fun messageBoxEnterPressed() {
        chatModel.sendMessage()
    }
}