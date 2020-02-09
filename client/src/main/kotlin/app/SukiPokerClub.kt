package app

import controllers.MainController
import javafx.application.Application

fun main(vararg args: String) {
    Application.launch(MainController::class.java, *args)
}