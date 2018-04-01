import javafx.scene.layout.BorderPane
import tornadofx.View

class MainView : View() {
    override val root : BorderPane by fxml(location = "views/MainView.fxml", hasControllerAttribute = true)
    val fileView: FileView by inject()
    val toolBar: ToolBar by inject()
    val console: ConsoleView by inject()

    init {
        root.center = fileView.root
        root.top = toolBar.root
        root.bottom = console.root
    }
}