import data.ConsoleItem
import javafx.beans.Observable
import javafx.beans.value.ChangeListener
import javafx.collections.ListChangeListener
import javafx.scene.control.ListView
import javafx.scene.layout.AnchorPane
import logging.ListViewAppender
import tornadofx.View

class ConsoleView : View() {
    override val root: AnchorPane by fxml(location = "views/ConsoleView.fxml", hasControllerAttribute = true)
    private val console: ListView<ConsoleItem> by fxid()

    init {
        ListViewAppender.addListView(console)
        with(console) {
            cellFormat { consoleItem ->
                text = consoleItem.message
                textFill = consoleItem.color
            }

            items.addListener(ListChangeListener { console.scrollTo(console.items.size - 1) } )
        }
    }
}