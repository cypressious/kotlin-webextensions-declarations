package commands

import kotlin.js.Promise
import webextensions.Event

class Command(
        /**
         * The name of the Extension Command
         */
        var name: String? = null,
        /**
         * The Extension Command description
         */
        var description: String? = null,
        /**
         * The shortcut active for this command, or blank if not active.
         */
        var shortcut: String? = null
)

external class CommandsNamespace {
    val onCommand: Event<(command: String) -> Unit>

    /**
     * Returns all the registered extension commands for this extension and their shortcut (if active).
     */
    fun getAll(): Promise<Array<Command>>
}
