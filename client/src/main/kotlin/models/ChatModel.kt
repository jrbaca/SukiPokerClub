package models

import controllers.MainController

class ChatModel(private val mainController: MainController) {

    fun sayHello() {
        mainController.txtMessageBox.text += Shared.getHello() + "\n"
    }
}