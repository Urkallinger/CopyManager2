import javafx.collections.FXCollections
import javafx.scene.layout.GridPane
import tornadofx.*

class DemoTableView : View() {
    override val root = GridPane()

    val mapTableContent = mapOf(Pair("item 1", 5), Pair("item 2", 10), Pair("item 3", 6))

    init {
        with (root) {
            row {
                vbox {
                    label("Tableview from a map")
                    button("click me") {
                        action {
                            runAsync {
                                doMagic()
                            } ui { magicString ->
                                this.text = magicString
                            }
                        }
                    }
                }
            }
        }
    }

    private fun doMagic(): String {
        Thread.sleep(2000)
        return "magic"
    }
}