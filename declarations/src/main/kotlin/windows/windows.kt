package windows

import kotlin.js.Promise
import tabs.Tab
import webextensions.Event

/**
 * The type of browser window this is. Under some circumstances a Window may not be assigned type property, for example when querying closed windows from the $(ref:sessions) API. */
typealias WindowType = String

/**
 * The state of this browser window. Under some circumstances a Window may not be assigned state property, for example when querying closed windows from the $(ref:sessions) API. */
typealias WindowState = String

/**
 * @param id The ID of the window. Window IDs are unique within a browser session. Under some circumstances a Window may not be assigned an ID, for example when querying windows using the $(ref:sessions) API, in which case a session ID may be present.
 * @param focused Whether the window is currently the focused window.
 * @param top The offset of the window from the top edge of the screen in pixels. Under some circumstances a Window may not be assigned top property, for example when querying closed windows from the $(ref:sessions) API.
 * @param left The offset of the window from the left edge of the screen in pixels. Under some circumstances a Window may not be assigned left property, for example when querying closed windows from the $(ref:sessions) API.
 * @param width The width of the window, including the frame, in pixels. Under some circumstances a Window may not be assigned width property, for example when querying closed windows from the $(ref:sessions) API.
 * @param height The height of the window, including the frame, in pixels. Under some circumstances a Window may not be assigned height property, for example when querying closed windows from the $(ref:sessions) API.
 * @param tabs Array of $(ref:tabs.Tab) objects representing the current tabs in the window.
 * @param incognito Whether the window is incognito.
 * @param type The type of browser window this is.
 * @param state The state of this browser window.
 * @param alwaysOnTop Whether the window is set to be always on top.
 * @param sessionId The session ID used to uniquely identify a Window obtained from the $(ref:sessions) API.
 * @param title The title of the window. Read-only.
 */
class Window(
    var id: Int? = null,
    var focused: Boolean,
    var top: Int? = null,
    var left: Int? = null,
    var width: Int? = null,
    var height: Int? = null,
    var tabs: Array<Tab>? = null,
    var incognito: Boolean,
    var type: WindowType? = null,
    var state: WindowState? = null,
    var alwaysOnTop: Boolean,
    var sessionId: String? = null,
    var title: String? = null
)

/**
 * Specifies what type of browser window to create. The 'panel' and 'detached_panel' types create a popup unless the '--enable-panels' flag is set. */
typealias CreateType = String

/**
 * Specifies whether the $(ref:windows.Window) returned should contain a list of the $(ref:tabs.Tab) objects.
 * @param populate If true, the $(ref:windows.Window) returned will have a <var>tabs</var> property that contains a list of the $(ref:tabs.Tab) objects. The <code>Tab</code> objects only contain the <code>url</code>, <code>title</code> and <code>favIconUrl</code> properties if the extension's manifest file includes the <code>"tabs"</code> permission.
 * @param windowTypes <code>windowTypes</code> is deprecated and ignored on Firefox.
 */
class GetInfo(
    var populate: Boolean? = null,
    var windowTypes: Array<WindowType>? = null
)

/**
 * Specifies properties used to filter the $(ref:windows.Window) returned and to determine whether they should contain a list of the $(ref:tabs.Tab) objects.
 * @param windowTypes If set, the $(ref:windows.Window) returned will be filtered based on its type. If unset the default filter is set to <code>['app', 'normal', 'panel', 'popup']</code>, with <code>'app'</code> and <code>'panel'</code> window types limited to the extension's own windows.
 */
class GetInfo2(
    var windowTypes: Array<WindowType>? = null
)

/**
 * A URL or array of URLs to open as tabs in the window. Fully-qualified URLs must include a scheme (i.e. 'http://www.google.com', not 'www.google.com'). Relative URLs will be relative to the current page within the extension. Defaults to the New Tab Page. */
typealias Url = Any

/**
 * @param url A URL or array of URLs to open as tabs in the window. Fully-qualified URLs must include a scheme (i.e. 'http://www.google.com', not 'www.google.com'). Relative URLs will be relative to the current page within the extension. Defaults to the New Tab Page.
 * @param tabId The id of the tab for which you want to adopt to the new window.
 * @param left The number of pixels to position the new window from the left edge of the screen. If not specified, the new window is offset naturally from the last focused window. This value is ignored for panels.
 * @param top The number of pixels to position the new window from the top edge of the screen. If not specified, the new window is offset naturally from the last focused window. This value is ignored for panels.
 * @param width The width in pixels of the new window, including the frame. If not specified defaults to a natural width.
 * @param height The height in pixels of the new window, including the frame. If not specified defaults to a natural height.
 * @param incognito Whether the new window should be an incognito window.
 * @param type Specifies what type of browser window to create. The 'panel' and 'detached_panel' types create a popup unless the '--enable-panels' flag is set.
 * @param state The initial state of the window. The 'minimized', 'maximized' and 'fullscreen' states cannot be combined with 'left', 'top', 'width' or 'height'.
 * @param allowScriptsToClose Allow scripts to close the window.
 * @param titlePreface A string to add to the beginning of the window title.
 */
class CreateData(
    var url: Url? = null,
    var tabId: Int? = null,
    var left: Int? = null,
    var top: Int? = null,
    var width: Int? = null,
    var height: Int? = null,
    var incognito: Boolean? = null,
    var type: CreateType? = null,
    var state: WindowState? = null,
    var allowScriptsToClose: Boolean? = null,
    var titlePreface: String? = null
)

/**
 * @param left The offset from the left edge of the screen to move the window to in pixels. This value is ignored for panels.
 * @param top The offset from the top edge of the screen to move the window to in pixels. This value is ignored for panels.
 * @param width The width to resize the window to in pixels. This value is ignored for panels.
 * @param height The height to resize the window to in pixels. This value is ignored for panels.
 * @param focused If true, brings the window to the front. If false, brings the next window in the z-order to the front.
 * @param drawAttention If true, causes the window to be displayed in a manner that draws the user's attention to the window, without changing the focused window. The effect lasts until the user changes focus to the window. This option has no effect if the window already has focus. Set to false to cancel a previous draw attention request.
 * @param state The new state of the window. The 'minimized', 'maximized' and 'fullscreen' states cannot be combined with 'left', 'top', 'width' or 'height'.
 * @param titlePreface A string to add to the beginning of the window title.
 */
class UpdateInfo(
    var left: Int? = null,
    var top: Int? = null,
    var width: Int? = null,
    var height: Int? = null,
    var focused: Boolean? = null,
    var drawAttention: Boolean? = null,
    var state: WindowState? = null,
    var titlePreface: String? = null
)

external class WindowsNamespace {
    /**
     * Fired when a window is created.
     *
     * @param window Details of the window that was created. */
    val onCreated: Event<(window: Window) -> Unit>

    /**
     * Fired when a window is removed (closed).
     *
     * @param windowId ID of the removed window. */
    val onRemoved: Event<(windowId: Int) -> Unit>

    /**
     * Fired when the currently focused window changes. Will be $(ref:windows.WINDOW_ID_NONE) if all browser windows have lost focus. Note: On some Linux window managers, WINDOW_ID_NONE will always be sent immediately preceding a switch from one browser window to another.
     *
     * @param windowId ID of the newly focused window. */
    val onFocusChanged: Event<(windowId: Int) -> Unit>

    /**
     * Gets details about a window.
     */
    fun get(windowId: Int, getInfo: GetInfo? = definedExternally): Promise<Window>

    /**
     * Gets the $(topic:current-window)[current window].
     */
    fun getCurrent(getInfo: GetInfo? = definedExternally): Promise<Window>

    /**
     * Gets the window that was most recently focused &mdash; typically the window 'on top'.
     */
    fun getLastFocused(getInfo: GetInfo? = definedExternally): Promise<Window>

    /**
     * Gets all windows.
     */
    fun getAll(getInfo: GetInfo2? = definedExternally): Promise<Array<Window>>

    /**
     * Creates (opens) a new browser with any optional sizing, position or default URL provided.
     */
    fun create(createData: CreateData? = definedExternally): Promise<Window?>

    /**
     * Updates the properties of a window. Specify only the properties that you want to change; unspecified properties will be left unchanged.
     */
    fun update(windowId: Int, updateInfo: UpdateInfo): Promise<Window>

    /**
     * Removes (closes) a window, and all the tabs inside it.
     */
    fun remove(windowId: Int): Promise<Any>
}
