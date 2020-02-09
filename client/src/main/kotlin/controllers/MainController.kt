package controllers

import javafx.application.Application
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.text.Text
import javafx.stage.Stage
import models.ChatModel

class MainController : Application() {

    val chatModel = ChatModel(this)

    @FXML
    lateinit var txtMessageBox: Text

    @FXML
    fun sayHello() {
        chatModel.sayHello()
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
}