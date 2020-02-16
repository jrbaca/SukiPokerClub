fun main() {
    launchApp()
}

private fun launchApp() {
    val isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows")
    if (isWindows) {
        Runtime.getRuntime().exec("cmd /c start bin/client.bat")
    } else {
        Runtime.getRuntime().exec("bin/client")
    }
}