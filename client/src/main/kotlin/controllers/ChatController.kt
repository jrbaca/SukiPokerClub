package controllers

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import models.ChatModel
import java.net.URL
import java.util.*

class ChatController : Initializable {

    private val chatModel = ChatModel(this)

    lateinit var mainController: MainController

    @FXML
    lateinit var chatArea: TextArea

    @FXML
    lateinit var messageBox: TextField


    override fun initialize(location: URL?, resources: ResourceBundle?) {
        chatModel.connectToChat()
    }

    @FXML
    fun messageBoxEnterPressed() {
        chatModel.sendMessage()
    }
}