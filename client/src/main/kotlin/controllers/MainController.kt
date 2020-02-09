package controllers

import javafx.application.Application
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Scene
import javafx.scene.text.Text
import javafx.stage.Stage
import models.ChatModel
import java.net.URL
import java.util.*

class MainController : Application(), Initializable {

    val chatModel = ChatModel(this)

    @FXML
    lateinit var txtMessageBox: Text

    @FXML
    fun sayHello() {
        chatModel.sayHello()
    }

    private fun connectToChat() {
        chatModel.connectToChat()
    }

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
}