package tabs

import extensionTypes.ImageDetails
import extensionTypes.InjectDetails
import kotlin.Deprecated
import kotlin.js.Promise
import windows.Window

class ConnectConnectInfo(/**
 * Will be passed into onConnect for content scripts that are listening for the connection event.
 */
val name: String, /**
 * Open a port to a specific $(topic:frame_ids)[frame] identified by <code>frameId</code> instead of all frames in the tab.
 */
val frameId: Int)

class SendMessageOptions(/**
 * Send a message to a specific $(topic:frame_ids)[frame] identified by <code>frameId</code> instead of all frames in the tab.
 */
val frameId: Int)

class CreateCreateProperties(
    /**
     * The window to create the new tab in. Defaults to the $(topic:current-window)[current window].
     */
    val windowId: Int,
    /**
     * The position the tab should take in the window. The provided value will be clamped to between zero and the number of tabs in the window.
     */
    val index: Int,
    /**
     * The URL to navigate the tab to initially. Fully-qualified URLs must include a scheme (i.e. 'http://www.google.com', not 'www.google.com'). Relative URLs will be relative to the current page within the extension. Defaults to the New Tab Page.
     */
    val url: String,
    /**
     * Whether the tab should become the active tab in the window. Does not affect whether the window is focused (see $(ref:windows.update)). Defaults to <var>true</var>.
     */
    val active: Boolean,
    /**
     * Whether the tab should become the selected tab in the window. Defaults to <var>true</var>
     */
    val selected: Boolean,
    /**
     * Whether the tab should be pinned. Defaults to <var>false</var>
     */
    val pinned: Boolean,
    /**
     * The ID of the tab that opened this tab. If specified, the opener tab must be in the same window as the newly created tab.
     */
    val openerTabId: Int,
    /**
     * The CookieStoreId for the tab that opened this tab.
     */
    val cookieStoreId: String,
    /**
     * Whether the document in the tab should be opened in reader mode.
     */
    val openInReaderMode: Boolean
)

class QueryQueryInfo(
    /**
     * Whether the tabs are active in their windows.
     */
    val active: Boolean,
    /**
     * Whether the tabs are pinned.
     */
    val pinned: Boolean,
    /**
     * Whether the tabs are audible.
     */
    val audible: Boolean,
    /**
     * Whether the tabs are muted.
     */
    val muted: Boolean,
    /**
     * Whether the tabs are highlighted.  Works as an alias of active.
     */
    val highlighted: Boolean,
    /**
     * Whether the tabs are in the $(topic:current-window)[current window].
     */
    val currentWindow: Boolean,
    /**
     * Whether the tabs are in the last focused window.
     */
    val lastFocusedWindow: Boolean,
    /**
     * Whether the tabs have completed loading.
     */
    val status: TabStatus,
    /**
     * True while the tabs are not loaded with content.
     */
    val discarded: Boolean,
    /**
     * Match page titles against a pattern.
     */
    val title: String,
    /**
     * Match tabs against one or more $(topic:match_patterns)[URL patterns]. Note that fragment identifiers are not matched.
     */
    val url: Any,
    /**
     * The ID of the parent window, or $(ref:windows.WINDOW_ID_CURRENT) for the $(topic:current-window)[current window].
     */
    val windowId: Int,
    /**
     * The type of window the tabs are in.
     */
    val windowType: WindowType,
    /**
     * The position of the tabs within their windows.
     */
    val index: Int,
    /**
     * The CookieStoreId used for the tab.
     */
    val cookieStoreId: String,
    /**
     * The ID of the tab that opened this tab. If specified, the opener tab must be in the same window as this tab.
     */
    val openerTabId: Int
)

class HighlightHighlightInfo(/**
 * The window that contains the tabs.
 */
val windowId: Int, /**
 * One or more tab indices to highlight.
 */
val tabs: Any)

class UpdateUpdateProperties(
    /**
     * A URL to navigate the tab to.
     */
    val url: String,
    /**
     * Whether the tab should be active. Does not affect whether the window is focused (see $(ref:windows.update)).
     */
    val active: Boolean,
    /**
     * Adds or removes the tab from the current selection.
     */
    val highlighted: Boolean,
    /**
     * Whether the tab should be selected.
     */
    val selected: Boolean,
    /**
     * Whether the tab should be pinned.
     */
    val pinned: Boolean,
    /**
     * Whether the tab should be muted.
     */
    val muted: Boolean,
    /**
     * The ID of the tab that opened this tab. If specified, the opener tab must be in the same window as this tab.
     */
    val openerTabId: Int,
    /**
     * Whether the load should replace the current history entry for the tab.
     */
    val loadReplace: Boolean
)

class MoveMoveProperties(/**
 * Defaults to the window the tab is currently in.
 */
val windowId: Int, /**
 * The position to move the window to. -1 will place the tab at the end of the window.
 */
val index: Int)

class ReloadReloadProperties(/**
 * Whether using any local cache. Default is false.
 */
val bypassCache: Boolean)

typealias MutedInfoReason = String

/**
 * Tab muted state and the reason for the last state change.
 */
external class MutedInfo {
  /**
   * Whether the tab is prevented from playing sound (but hasn't necessarily recently produced sound). Equivalent to whether the muted audio indicator is showing.
   */
  val muted: Boolean

  /**
   * The reason the tab was muted or unmuted. Not set if the tab's mute state has never been changed.
   */
  val reason: MutedInfoReason

  /**
   * The ID of the extension that changed the muted state. Not set if an extension was not the reason the muted state last changed.
   */
  val extensionId: String
}

external class Tab {
  /**
   * The ID of the tab. Tab IDs are unique within a browser session. Under some circumstances a Tab may not be assigned an ID, for example when querying foreign tabs using the $(ref:sessions) API, in which case a session ID may be present. Tab ID can also be set to $(ref:tabs.TAB_ID_NONE) for apps and devtools windows.
   */
  val id: Int

  /**
   * The zero-based index of the tab within its window.
   */
  val index: Int

  /**
   * The ID of the window the tab is contained within.
   */
  val windowId: Int

  /**
   * The ID of the tab that opened this tab, if any. This property is only present if the opener tab still exists.
   */
  val openerTabId: Int

  /**
   * Whether the tab is selected.
   */
  val selected: Boolean

  /**
   * Whether the tab is highlighted. Works as an alias of active
   */
  val highlighted: Boolean

  /**
   * Whether the tab is active in its window. (Does not necessarily mean the window is focused.)
   */
  val active: Boolean

  /**
   * Whether the tab is pinned.
   */
  val pinned: Boolean

  /**
   * The last time the tab was accessed as the number of milliseconds since epoch.
   */
  val lastAccessed: Int

  /**
   * Whether the tab has produced sound over the past couple of seconds (but it might not be heard if also muted). Equivalent to whether the speaker audio indicator is showing.
   */
  val audible: Boolean

  /**
   * Current tab muted state and the reason for the last state change.
   */
  val mutedInfo: MutedInfo

  /**
   * The URL the tab is displaying. This property is only present if the extension's manifest includes the <code>"tabs"</code> permission.
   */
  val url: String

  /**
   * The title of the tab. This property is only present if the extension's manifest includes the <code>"tabs"</code> permission.
   */
  val title: String

  /**
   * The URL of the tab's favicon. This property is only present if the extension's manifest includes the <code>"tabs"</code> permission. It may also be an empty string if the tab is loading.
   */
  val favIconUrl: String

  /**
   * Either <em>loading</em> or <em>complete</em>.
   */
  val status: String

  /**
   * True while the tab is not loaded with content.
   */
  val discarded: Boolean

  /**
   * Whether the tab is in an incognito window.
   */
  val incognito: Boolean

  /**
   * The width of the tab in pixels.
   */
  val width: Int

  /**
   * The height of the tab in pixels.
   */
  val height: Int

  /**
   * The session ID used to uniquely identify a Tab obtained from the $(ref:sessions) API.
   */
  val sessionId: String

  /**
   * The CookieStoreId used for the tab.
   */
  val cookieStoreId: String

  /**
   * Whether the document in the tab can be rendered in reader mode.
   */
  val isArticle: Boolean

  /**
   * Whether the document in the tab is being rendered in reader mode.
   */
  val isInReaderMode: Boolean
}

typealias ZoomSettingsMode = String

typealias ZoomSettingsScope = String

/**
 * Defines how zoom changes in a tab are handled and at what scope.
 */
external class ZoomSettings {
  /**
   * Defines how zoom changes are handled, i.e. which entity is responsible for the actual scaling of the page; defaults to <code>automatic</code>.
   */
  val mode: ZoomSettingsMode

  /**
   * Defines whether zoom changes will persist for the page's origin, or only take effect in this tab; defaults to <code>per-origin</code> when in <code>automatic</code> mode, and <code>per-tab</code> otherwise.
   */
  val scope: ZoomSettingsScope

  /**
   * Used to return the default zoom level for the current tab in calls to tabs.getZoomSettings.
   */
  val defaultZoomFactor: Any
}

/**
 * The page settings including: orientation, scale, background, margins, headers, footers.
 */
external class PageSettings {
  /**
   * The page content orientation: 0 = portrait, 1 = landscape. Default: 0.
   */
  val orientation: Int

  /**
   * The page content scaling factor: 1.0 = 100% = normal size. Default: 1.0.
   */
  val scaling: Any

  /**
   * Whether the page content should shrink to fit the page width (overrides scaling). Default: true.
   */
  val shrinkToFit: Boolean

  /**
   * Whether the page background colors should be shown. Default: false.
   */
  val showBackgroundColors: Boolean

  /**
   * Whether the page background images should be shown. Default: false.
   */
  val showBackgroundImages: Boolean

  /**
   * The page size unit: 0 = inches, 1 = millimeters. Default: 0.
   */
  val paperSizeUnit: Int

  /**
   * The paper width in paper size units. Default: 8.5.
   */
  val paperWidth: Any

  /**
   * The paper height in paper size units. Default: 11.0.
   */
  val paperHeight: Any

  /**
   * The text for the page's left header. Default: '&T'.
   */
  val headerLeft: String

  /**
   * The text for the page's center header. Default: ''.
   */
  val headerCenter: String

  /**
   * The text for the page's right header. Default: '&U'.
   */
  val headerRight: String

  /**
   * The text for the page's left footer. Default: '&PT'.
   */
  val footerLeft: String

  /**
   * The text for the page's center footer. Default: ''.
   */
  val footerCenter: String

  /**
   * The text for the page's right footer. Default: '&D'.
   */
  val footerRight: String

  /**
   * The margin between the page content and the left edge of the paper (inches). Default: 0.5.
   */
  val marginLeft: Any

  /**
   * The margin between the page content and the right edge of the paper (inches). Default: 0.5.
   */
  val marginRight: Any

  /**
   * The margin between the page content and the top edge of the paper (inches). Default: 0.5.
   */
  val marginTop: Any

  /**
   * The margin between the page content and the bottom edge of the paper (inches). Default: 0.5.
   */
  val marginBottom: Any
}

typealias TabStatus = String

typealias WindowType = String

external class Tabs {
  /**
   * Retrieves details about the specified tab.
   */
  fun get(tabId: Int): Promise<Tab>

  /**
   * Gets the tab that this script call is being made from. May be undefined if called from a non-tab context (for example: a background page or popup view).
   */
  fun getCurrent(): Promise<Tab>

  /**
   * Connects to the content script(s) in the specified tab. The $(ref:runtime.onConnect) event is fired in each content script running in the specified tab for the current extension. For more details, see $(topic:messaging)[Content Script Messaging].
   */
  fun connect(tabId: Int, connectInfo: ConnectConnectInfo)

  /**
   * Sends a single request to the content script(s) in the specified tab, with an optional callback to run when a response is sent back.  The $(ref:extension.onRequest) event is fired in each content script running in the specified tab for the current extension.
   */
  @Deprecated("Please use $(ref:runtime.sendMessage).")
  fun sendRequest(
      tabId: Int,
      request: Any,
      responseCallback: Any
  )

  /**
   * Sends a single message to the content script(s) in the specified tab, with an optional callback to run when a response is sent back.  The $(ref:runtime.onMessage) event is fired in each content script running in the specified tab for the current extension.
   */
  fun sendMessage(
      tabId: Int,
      message: Any,
      options: SendMessageOptions
  ): Promise<Any>

  /**
   * Gets the tab that is selected in the specified window.
   */
  @Deprecated("Please use $(ref:tabs.query) <code>{active: true}</code>.")
  fun getSelected(windowId: Int): Promise<Tab>

  /**
   * Gets details about all tabs in the specified window.
   */
  @Deprecated("Please use $(ref:tabs.query) <code>{windowId: windowId}</code>.")
  fun getAllInWindow(windowId: Int): Promise<Array<Tab>>

  /**
   * Creates a new tab.
   */
  fun create(createProperties: CreateCreateProperties): Promise<Tab>

  /**
   * Duplicates a tab.
   */
  fun duplicate(tabId: Int): Promise<Tab>

  /**
   * Gets all tabs that have the specified properties, or all tabs if no properties are specified.
   */
  fun query(queryInfo: QueryQueryInfo): Promise<Array<Tab>>

  /**
   * Highlights the given tabs.
   */
  fun highlight(highlightInfo: HighlightHighlightInfo): Promise<Window>

  /**
   * Modifies the properties of a tab. Properties that are not specified in <var>updateProperties</var> are not modified.
   */
  fun update(tabId: Int, updateProperties: UpdateUpdateProperties): Promise<Tab>

  /**
   * Moves one or more tabs to a new position within its window, or to a new window. Note that tabs can only be moved to and from normal (window.type === "normal") windows.
   */
  fun move(tabIds: Int, moveProperties: MoveMoveProperties): Promise<Any>

  /**
   * Moves one or more tabs to a new position within its window, or to a new window. Note that tabs can only be moved to and from normal (window.type === "normal") windows.
   */
  fun move(tabIds: Array<Int>, moveProperties: MoveMoveProperties): Promise<Any>

  /**
   * Reload a tab.
   */
  fun reload(tabId: Int, reloadProperties: ReloadReloadProperties): Promise<Any>

  /**
   * Closes one or more tabs.
   */
  fun remove(tabIds: Int): Promise<Any>

  /**
   * Closes one or more tabs.
   */
  fun remove(tabIds: Array<Int>): Promise<Any>

  /**
   * discards one or more tabs.
   */
  fun discard(tabIds: Int): Promise<Any>

  /**
   * discards one or more tabs.
   */
  fun discard(tabIds: Array<Int>): Promise<Any>

  /**
   * Detects the primary language of the content in a tab.
   */
  fun detectLanguage(tabId: Int): Promise<String>

  /**
   * Toggles reader mode for the document in the tab.
   */
  fun toggleReaderMode(tabId: Int): Promise<Any>

  /**
   * Captures the visible area of the currently active tab in the specified window. You must have $(topic:declare_permissions)[&lt;all_urls&gt;] permission to use this method.
   */
  fun captureVisibleTab(windowId: Int, options: ImageDetails): Promise<String>

  /**
   * Injects JavaScript code into a page. For details, see the $(topic:content_scripts)[programmatic injection] section of the content scripts doc.
   */
  fun executeScript(tabId: Int, details: InjectDetails): Promise<Array<Any>>

  /**
   * Injects CSS into a page. For details, see the $(topic:content_scripts)[programmatic injection] section of the content scripts doc.
   */
  fun insertCSS(tabId: Int, details: InjectDetails): Promise<Any>

  /**
   * Removes injected CSS from a page. For details, see the $(topic:content_scripts)[programmatic injection] section of the content scripts doc.
   */
  fun removeCSS(tabId: Int, details: InjectDetails): Promise<Any>

  /**
   * Zooms a specified tab.
   */
  fun setZoom(tabId: Int, zoomFactor: Any): Promise<Any>

  /**
   * Gets the current zoom factor of a specified tab.
   */
  fun getZoom(tabId: Int): Promise<Any>

  /**
   * Sets the zoom settings for a specified tab, which define how zoom changes are handled. These settings are reset to defaults upon navigating the tab.
   */
  fun setZoomSettings(tabId: Int, zoomSettings: ZoomSettings): Promise<Any>

  /**
   * Gets the current zoom settings of a specified tab.
   */
  fun getZoomSettings(tabId: Int): Promise<ZoomSettings>

  /**
   * Prints page in active tab.
   */
  fun print()

  /**
   * Shows print preview for page in active tab.
   */
  fun printPreview(): Promise<Any>

  /**
   * Saves page in active tab as a PDF file.
   */
  fun saveAsPDF(pageSettings: PageSettings): Promise<String>
}
