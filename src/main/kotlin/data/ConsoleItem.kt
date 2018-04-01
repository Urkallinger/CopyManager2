package data

import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.scene.paint.Color
import org.apache.logging.log4j.Level

data class ConsoleItem(val message: String, val level: Level) {
    var color: Color = Color.BLACK
    init {
        when(level) {
            Level.WARN -> color = Color.ORANGE
            Level.ERROR -> color = Color.RED
        }
    }
}