package logging

import data.ConsoleItem
import javafx.application.Platform
import javafx.scene.control.ListView
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.core.Filter
import org.apache.logging.log4j.core.Layout
import org.apache.logging.log4j.core.LogEvent
import org.apache.logging.log4j.core.appender.AbstractAppender
import org.apache.logging.log4j.core.config.plugins.Plugin
import org.apache.logging.log4j.core.config.plugins.PluginAttribute
import org.apache.logging.log4j.core.config.plugins.PluginElement
import org.apache.logging.log4j.core.config.plugins.PluginFactory
import org.apache.logging.log4j.core.layout.PatternLayout

@Plugin(name="ListViewAppender", category="Core", elementType="appender", printObject=true)
class ListViewAppender(name: String,
                       layout: Layout<*>,
                       filter: Filter?,
                       private val maxLines: Int,
                       ignoreExceptions: Boolean)
    : AbstractAppender(name, filter, layout, ignoreExceptions) {

    private val logger by logger()

    companion object {
        private val logger by logger()
        private val listViews = ArrayList<ListView<ConsoleItem>>()

        fun addListView(listView: ListView<ConsoleItem>) {
            listViews.add(listView)
        }

        @JvmStatic
        @PluginFactory
        fun createAppender(@PluginAttribute("name") name: String?,
                           @PluginAttribute("maxLines") maxLines: Int,
                           @PluginAttribute("ignoreExceptions") ignoreExceptions: Boolean,
                           @PluginElement("Layout") layout: Layout<*>?,
                           @PluginElement("Filters") filter: Filter?): ListViewAppender {
            if (name == null) {
                logger.error("No name provided for ListViewAppender")
            }

            return ListViewAppender(name ?: "", layout ?: PatternLayout.createDefaultLayout(),
                    filter, maxLines, ignoreExceptions)
        }
    }



    override fun append(event: LogEvent?) {
        val message = String(layout.toByteArray(event))
        val level = event?.level ?: Level.INFO

        try {
            Platform.runLater { appendMessage(message, level) }
        } catch (e: Exception) {
            // ignore case when the platform hasn't yet been initialized
        }
    }

    private fun appendMessage(message: String, level: Level) {
        for (listView in ListViewAppender.listViews) {
            try {
                with(listView) {
                    items.add(ConsoleItem(message.trim(), level))
                    if (maxLines > 0 && items.size > maxLines + 1) {
                        items.removeAt(0)
                    }
                }
            } catch (e: Exception) {
                logger.error("Unable to append log to list view: %s".format(e.message))
            }
        }
    }
}