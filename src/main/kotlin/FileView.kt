import javafx.scene.layout.AnchorPane
import tornadofx.View
import tornadofx.label
import tornadofx.row
import tornadofx.vbox

class FileView : View() {
    override val root : AnchorPane by fxml(location = "views/FileView.fxml", hasControllerAttribute = true)
}