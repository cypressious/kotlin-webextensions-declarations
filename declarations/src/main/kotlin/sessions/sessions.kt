package sessions

import kotlin.js.Promise
import tabs.Tab
import windows.Window

external class Filter {
  /**
   * The maximum number of entries to be fetched in the requested list. Omit this parameter to fetch the maximum number of entries ($(ref:sessions.MAX_SESSION_RESULTS)).
   */
  val maxResults: Int
}

external class Session {
  /**
   * The time when the window or tab was closed or modified, represented in milliseconds since the epoch.
   */
  val lastModified: Int

  /**
   * The $(ref:tabs.Tab), if this entry describes a tab. Either this or $(ref:sessions.Session.window) will be set.
   */
  val tab: Tab

  /**
   * The $(ref:windows.Window), if this entry describes a window. Either this or $(ref:sessions.Session.tab) will be set.
   */
  val window: Window
}

external class Device {
  val info: String

  /**
   * The name of the foreign device.
   */
  val deviceName: String

  /**
   * A list of open window sessions for the foreign device, sorted from most recently to least recently modified session.
   */
  val sessions: Array<Session>
}

external class Sessions {
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
  fun getRecentlyClosed(filter: Filter): Promise<Array<Session>>

  /**
   * Reopens a $(ref:windows.Window) or $(ref:tabs.Tab), with an optional callback to run when the entry has been restored.
   */
  fun restore(sessionId: String): Promise<Session>

  /**
   * Set a key/value pair on a given tab.
   */
  fun setTabValue(
      tabId: Int,
      key: String,
      value: Any
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
      value: Any
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
