package commands

import kotlin.js.Promise

external class Command {
    /**
     * The name of the Extension Command
     */
    val name: String?

    /**
     * The Extension Command description
     */
    val description: String?

    /**
     * The shortcut active for this command, or blank if not active.
     */
    val shortcut: String?
}

external class CommandsNamespace {
    /**
     * Returns all the registered extension commands for this extension and their shortcut (if active).
     */
    fun getAll(): Promise<Array<Command>>
}
