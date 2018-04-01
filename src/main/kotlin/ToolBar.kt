import logging.logger
import javafx.scene.control.Button
import javafx.scene.layout.AnchorPane
import tornadofx.View
import tornadofx.action
import tornadofx.chooseDirectory
import tornadofx.imageview

class ToolBar : View() {
    val logger by logger()

    override val root : AnchorPane by fxml(location = "views/ToolBar.fxml", hasControllerAttribute = true)
    private val btnOpen: Button by fxid()
    private val btnCopy: Button by fxid()


    init {
        setUpBtnOpen()
        setUpBtnCopy()
    }

    private fun setUpBtnOpen() {
        with(btnOpen) {
            imageview("images/open.png")
            action {
                var dir = chooseDirectory("Select source directory")
                logger.info(dir.toString())
            }
        }
    }

    private fun setUpBtnCopy() {
        with(btnCopy) {
            imageview("images/copy.png")
            action {
                logger.warn("copy not implemented yet!")
            }
        }
    }
}