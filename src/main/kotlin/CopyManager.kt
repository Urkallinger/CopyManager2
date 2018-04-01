import tornadofx.App
import javafx.application.Application
import javafx.scene.Scene
import tornadofx.UIComponent

class CopyManager : App() {
    override val primaryView = MainView::class

    override fun createPrimaryScene(view: UIComponent): Scene {
        val scene = super.createPrimaryScene(view)
        scene.stylesheets.add("/css/GlobalStyles.css")
        return scene
    }
}

fun main(args: Array<String>) {
    Application.launch(CopyManager::class.java, *args)
}