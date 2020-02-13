package controllers

import javafx.application.Application
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Scene
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import javafx.stage.Stage
import models.ChatModel
import java.net.URL
import java.util.*

class MainController : Application(), Initializable {

    private val chatModel = ChatModel(this)

    @FXML
    lateinit var chatArea: TextArea

    @FXML
    lateinit var messageBox: TextField

    override fun start(primaryStage: Stage?) {
        // Load the scene
        val loader = FXMLLoader(
            javaClass.getResource("/views/MainView.fxml")
        )
        val scene = Scene(loader.load())
        scene.root.requestFocus()

        // Set up the scene
        primaryStage!!.title = "Suki's Poker Club"
        primaryStage.scene = scene
        primaryStage.show()
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {

        connectToChat()
    }

    private fun connectToChat() {
        chatModel.connectToChat()
    }

    @FXML
    fun messageBoxEnterPressed(actionEvent: ActionEvent) {
        chatModel.sendMessage()
    }
}