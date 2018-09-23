package theme

import kotlin.js.Promise
import manifest.ThemeType
import webextensions.Event

/**
 * Info provided in the onUpdated listener.
 * @param theme The new theme after update
 * @param windowId The id of the window the theme has been applied to
 */
class ThemeUpdateInfo(
    var theme: Theme,
    var windowId: Int? = null
)

/**
 * The new theme after update */
typealias Theme = Any

external class ThemeNamespace {
    /**
     * Fired when a new theme has been applied
     *
     * @param updateInfo Details of the theme update */
    val onUpdated: Event<(updateInfo: ThemeUpdateInfo) -> Unit>

    /**
     * Returns the current theme for the specified window or the last focused window.
     */
    fun getCurrent(windowId: Int? = definedExternally): Promise<Any>

    /**
     * Make complete updates to the theme. Resolves when the update has completed.
     */
    fun update(windowId: Int? = definedExternally, details: ThemeType): Promise<Any>

    /**
     * Removes the updates made to the theme.
     */
    fun reset(windowId: Int? = definedExternally): Promise<Any>
}
