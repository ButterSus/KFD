import com.buttersus.ConsoleInterfaceManager
import com.buttersus.util.ExitException

fun main() {
    ConsoleInterfaceManager.onInit()
    while (true) {
        try {
            ConsoleInterfaceManager.onLoop()
        } catch (e: Exception) {
            when (e) {
                is ExitException, is InterruptedException -> {
                    ConsoleInterfaceManager.onExit()
                    break
                }
                else -> throw e
            }
        }
    }
}