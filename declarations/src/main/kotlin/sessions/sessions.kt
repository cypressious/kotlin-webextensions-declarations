package sessions

import kotlin.js.Promise
import tabs.Tab
import webextensions.Event
import windows.Window

/**
 * @param maxResults The maximum number of entries to be fetched in the requested list. Omit this
        parameter to fetch the maximum number of entries ($(ref:sessions.MAX_SESSION_RESULTS)).
 */
class Filter(
    var maxResults: Int? = null
)

/**
 * @param lastModified The time when the window or tab was closed or modified, represented in
        milliseconds since the epoch.
 * @param tab The $(ref:tabs.Tab), if this entry describes a tab. Either this or
        $(ref:sessions.Session.window) will be set.
 * @param window The $(ref:windows.Window), if this entry describes a window. Either this or
        $(ref:sessions.Session.tab) will be set.
 */
class Session(
    var lastModified: Int,
    var tab: Tab? = null,
    var window: Window? = null
)

/**
 * @param deviceName The name of the foreign device.
 * @param sessions A list of open window sessions for the foreign device, sorted from most recently
        to least recently modified session.
 */
class Device(
    var info: String,
    var deviceName: String,
    var sessions: Array<Session>
)

external class SessionsNamespace {
    /**
     * Fired when recently closed tabs and/or windows are changed. This event does not monitor
            synced sessions changes.
     */
    val onChanged: Event<() -> Unit>

    /**
     * Forget a recently closed tab.
     */
    fun forgetClosedTab(windowId: Int, sessionId: String): Promise<Any>

    /**
     * Forget a recently closed window.
     */
    fun forgetClosedWindow(sessionId: String): Promise<Any>

    /**
     * Gets the list of recently closed tabs and/or windows.
     */
    fun getRecentlyClosed(filter: Filter? = definedExternally): Promise<Array<Session>>

    /**
     * Reopens a $(ref:windows.Window) or $(ref:tabs.Tab), with an optional callback to run when the
            entry has been restored.
     */
    fun restore(sessionId: String? = definedExternally): Promise<Session>

    /**
     * Set a key/value pair on a given tab.
     */
    fun setTabValue(
        tabId: Int,
        key: String,
        value: dynamic
    ): Promise<Any>

    /**
     * Retrieve a value that was set for a given key on a given tab.
     */
    fun getTabValue(tabId: Int, key: String): Promise<Any>

    /**
     * Remove a key/value pair that was set on a given tab.
     */
    fun removeTabValue(tabId: Int, key: String): Promise<Any>

    /**
     * Set a key/value pair on a given window.
     */
    fun setWindowValue(
        windowId: Int,
        key: String,
        value: dynamic
    ): Promise<Any>

    /**
     * Retrieve a value that was set for a given key on a given window.
     */
    fun getWindowValue(windowId: Int, key: String): Promise<Any>

    /**
     * Remove a key/value pair that was set on a given window.
     */
    fun removeWindowValue(windowId: Int, key: String): Promise<Any>
}
