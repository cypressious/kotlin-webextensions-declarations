package windows

import kotlin.js.Promise

class GetGetInfo(/**
 * If true, the $(ref:windows.Window) object will have a <var>tabs</var> property that contains a list of the $(ref:tabs.Tab) objects. The <code>Tab</code> objects only contain the <code>url</code>, <code>title</code> and <code>favIconUrl</code> properties if the extension's manifest file includes the <code>"tabs"</code> permission.
 */
val populate: Boolean, /**
 * If set, the $(ref:windows.Window) returned will be filtered based on its type. If unset the default filter is set to <code>['app', 'normal', 'panel', 'popup']</code>, with <code>'app'</code> and <code>'panel'</code> window types limited to the extension's own windows.
 */
val windowTypes: Array<WindowType>)

class GetCurrentGetInfo(/**
 * If true, the $(ref:windows.Window) object will have a <var>tabs</var> property that contains a list of the $(ref:tabs.Tab) objects. The <code>Tab</code> objects only contain the <code>url</code>, <code>title</code> and <code>favIconUrl</code> properties if the extension's manifest file includes the <code>"tabs"</code> permission.
 */
val populate: Boolean, /**
 * If set, the $(ref:windows.Window) returned will be filtered based on its type. If unset the default filter is set to <code>['app', 'normal', 'panel', 'popup']</code>, with <code>'app'</code> and <code>'panel'</code> window types limited to the extension's own windows.
 */
val windowTypes: Array<WindowType>)

class GetLastFocusedGetInfo(/**
 * If true, the $(ref:windows.Window) object will have a <var>tabs</var> property that contains a list of the $(ref:tabs.Tab) objects. The <code>Tab</code> objects only contain the <code>url</code>, <code>title</code> and <code>favIconUrl</code> properties if the extension's manifest file includes the <code>"tabs"</code> permission.
 */
val populate: Boolean, /**
 * If set, the $(ref:windows.Window) returned will be filtered based on its type. If unset the default filter is set to <code>['app', 'normal', 'panel', 'popup']</code>, with <code>'app'</code> and <code>'panel'</code> window types limited to the extension's own windows.
 */
val windowTypes: Array<WindowType>)

class GetAllGetInfo(/**
 * If true, each $(ref:windows.Window) object will have a <var>tabs</var> property that contains a list of the $(ref:tabs.Tab) objects for that window. The <code>Tab</code> objects only contain the <code>url</code>, <code>title</code> and <code>favIconUrl</code> properties if the extension's manifest file includes the <code>"tabs"</code> permission.
 */
val populate: Boolean, /**
 * If set, the $(ref:windows.Window) returned will be filtered based on its type. If unset the default filter is set to <code>['app', 'normal', 'panel', 'popup']</code>, with <code>'app'</code> and <code>'panel'</code> window types limited to the extension's own windows.
 */
val windowTypes: Array<WindowType>)

class CreateCreateData(
    /**
     * A URL or array of URLs to open as tabs in the window. Fully-qualified URLs must include a scheme (i.e. 'http://www.google.com', not 'www.google.com'). Relative URLs will be relative to the current page within the extension. Defaults to the New Tab Page.
     */
    val url: Any,
    /**
     * The id of the tab for which you want to adopt to the new window.
     */
    val tabId: Int,
    /**
     * The number of pixels to position the new window from the left edge of the screen. If not specified, the new window is offset naturally from the last focused window. This value is ignored for panels.
     */
    val left: Int,
    /**
     * The number of pixels to position the new window from the top edge of the screen. If not specified, the new window is offset naturally from the last focused window. This value is ignored for panels.
     */
    val top: Int,
    /**
     * The width in pixels of the new window, including the frame. If not specified defaults to a natural width.
     */
    val width: Int,
    /**
     * The height in pixels of the new window, including the frame. If not specified defaults to a natural height.
     */
    val height: Int,
    /**
     * Whether the new window should be an incognito window.
     */
    val incognito: Boolean,
    /**
     * Specifies what type of browser window to create. The 'panel' and 'detached_panel' types create a popup unless the '--enable-panels' flag is set.
     */
    val type: CreateType,
    /**
     * The initial state of the window. The 'minimized', 'maximized' and 'fullscreen' states cannot be combined with 'left', 'top', 'width' or 'height'.
     */
    val state: WindowState,
    /**
     * Allow scripts to close the window.
     */
    val allowScriptsToClose: Boolean,
    /**
     * A string to add to the beginning of the window title.
     */
    val titlePreface: String
)

class UpdateUpdateInfo(
    /**
     * The offset from the left edge of the screen to move the window to in pixels. This value is ignored for panels.
     */
    val left: Int,
    /**
     * The offset from the top edge of the screen to move the window to in pixels. This value is ignored for panels.
     */
    val top: Int,
    /**
     * The width to resize the window to in pixels. This value is ignored for panels.
     */
    val width: Int,
    /**
     * The height to resize the window to in pixels. This value is ignored for panels.
     */
    val height: Int,
    /**
     * If true, brings the window to the front. If false, brings the next window in the z-order to the front.
     */
    val focused: Boolean,
    /**
     * If true, causes the window to be displayed in a manner that draws the user's attention to the window, without changing the focused window. The effect lasts until the user changes focus to the window. This option has no effect if the window already has focus. Set to false to cancel a previous draw attention request.
     */
    val drawAttention: Boolean,
    /**
     * The new state of the window. The 'minimized', 'maximized' and 'fullscreen' states cannot be combined with 'left', 'top', 'width' or 'height'.
     */
    val state: WindowState,
    /**
     * A string to add to the beginning of the window title.
     */
    val titlePreface: String
)

typealias WindowType = String

typealias WindowState = String

external class Window {
  /**
   * The ID of the window. Window IDs are unique within a browser session. Under some circumstances a Window may not be assigned an ID, for example when querying windows using the $(ref:sessions) API, in which case a session ID may be present.
   */
  val id: Int

  /**
   * Whether the window is currently the focused window.
   */
  val focused: Boolean

  /**
   * The offset of the window from the top edge of the screen in pixels. Under some circumstances a Window may not be assigned top property, for example when querying closed windows from the $(ref:sessions) API.
   */
  val top: Int

  /**
   * The offset of the window from the left edge of the screen in pixels. Under some circumstances a Window may not be assigned left property, for example when querying closed windows from the $(ref:sessions) API.
   */
  val left: Int

  /**
   * The width of the window, including the frame, in pixels. Under some circumstances a Window may not be assigned width property, for example when querying closed windows from the $(ref:sessions) API.
   */
  val width: Int

  /**
   * The height of the window, including the frame, in pixels. Under some circumstances a Window may not be assigned height property, for example when querying closed windows from the $(ref:sessions) API.
   */
  val height: Int

  /**
   * Array of $(ref:tabs.Tab) objects representing the current tabs in the window.
   */
  val tabs: Array<tabs.Tab>

  /**
   * Whether the window is incognito.
   */
  val incognito: Boolean

  /**
   * The type of browser window this is.
   */
  val type: WindowType

  /**
   * The state of this browser window.
   */
  val state: WindowState

  /**
   * Whether the window is set to be always on top.
   */
  val alwaysOnTop: Boolean

  /**
   * The session ID used to uniquely identify a Window obtained from the $(ref:sessions) API.
   */
  val sessionId: String

  /**
   * The title of the window. Read-only.
   */
  val title: String
}

typealias CreateType = String

external class Windows {
  /**
   * Gets details about a window.
   */
  fun get(windowId: Int, getInfo: GetGetInfo): Promise<Window>

  /**
   * Gets the $(topic:current-window)[current window].
   */
  fun getCurrent(getInfo: GetCurrentGetInfo): Promise<Window>

  /**
   * Gets the window that was most recently focused &mdash; typically the window 'on top'.
   */
  fun getLastFocused(getInfo: GetLastFocusedGetInfo): Promise<Window>

  /**
   * Gets all windows.
   */
  fun getAll(getInfo: GetAllGetInfo): Promise<Array<Window>>

  /**
   * Creates (opens) a new browser with any optional sizing, position or default URL provided.
   */
  fun create(createData: CreateCreateData): Promise<Window>

  /**
   * Updates the properties of a window. Specify only the properties that you want to change; unspecified properties will be left unchanged.
   */
  fun update(windowId: Int, updateInfo: UpdateUpdateInfo): Promise<Window>

  /**
   * Removes (closes) a window, and all the tabs inside it.
   */
  fun remove(windowId: Int): Promise<Any>
}
