package controllers

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.TextField

class TopBarController {

    lateinit var mainController: MainController

    @FXML
    lateinit var connectButton: Button

    @FXML
    lateinit var txtServerAddress: TextField

    @FXML
    private fun connectToServer() {
        val address = txtServerAddress.text

        txtServerAddress.text = ""
        txtServerAddress.promptText = address
        txtServerAddress.isDisable = true
        connectButton.isDisable = true

        mainController.startServerConnections(address)
    }
}