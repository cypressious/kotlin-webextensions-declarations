package commands

import kotlin.js.Promise
import manifest.KeyName
import webextensions.Event

/**
 * @param name The name of the Extension Command
 * @param description The Extension Command description
 * @param shortcut The shortcut active for this command, or blank if not active.
 */
class Command(
    var name: String? = null,
    var description: String? = null,
    var shortcut: String? = null
)

/**
 * The new description for the command.
 * @param name The name of the command.
 * @param description The new description for the command.
 */
class Detail(
    var name: String,
    var description: String? = null,
    var shortcut: KeyName? = null
)

external class CommandsNamespace {
    /**
     * Fired when a registered command is activated using a keyboard shortcut.
     *
     * @param command null */
    val onCommand: Event<(command: String) -> Unit>

    /**
     * Update the details of an already defined command.
     */
    fun update(detail: Detail): Promise<Any>

    /**
     * Reset a command's details to what is specified in the manifest.
     */
    fun reset(name: String): Promise<Any>

    /**
     * Returns all the registered extension commands for this extension and their shortcut (if
            active).
     */
    fun getAll(): Promise<Array<Command>>
}
