package commands

import browser.Event
import kotlin.js.Promise

external class Command {
    /**
     * The name of the Extension Command
     */
    var name: String?

    /**
     * The Extension Command description
     */
    var description: String?

    /**
     * The shortcut active for this command, or blank if not active.
     */
    var shortcut: String?
}

external class CommandsNamespace {
    val onCommand: Event<(command: String) -> Unit>

    /**
     * Returns all the registered extension commands for this extension and their shortcut (if active).
     */
    fun getAll(): Promise<Array<Command>>
}
