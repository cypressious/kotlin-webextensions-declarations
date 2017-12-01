package tabs

import browser.Event
import extensionTypes.ImageDetails
import extensionTypes.InjectDetails
import kotlin.js.Promise

/**
 * An event that caused a muted state change. */
typealias MutedInfoReason = String

/**
 * Tab muted state and the reason for the last state change.
 */
external class MutedInfo {
    /**
     * Whether the tab is prevented from playing sound (but hasn't necessarily recently produced sound). Equivalent to whether the muted audio indicator is showing.
     */
    var muted: Boolean

    /**
     * The reason the tab was muted or unmuted. Not set if the tab's mute state has never been changed.
     */
    var reason: MutedInfoReason?

    /**
     * The ID of the extension that changed the muted state. Not set if an extension was not the reason the muted state last changed.
     */
    var extensionId: String?
}

external class Tab {
    /**
     * The ID of the tab. Tab IDs are unique within a browser session. Under some circumstances a Tab may not be assigned an ID, for example when querying foreign tabs using the $(ref:sessions) API, in which case a session ID may be present. Tab ID can also be set to $(ref:tabs.TAB_ID_NONE) for apps and devtools windows.
     */
    var id: Int?

    /**
     * The zero-based index of the tab within its window.
     */
    var index: Int

    /**
     * The ID of the window the tab is contained within.
     */
    var windowId: Int?

    /**
     * The ID of the tab that opened this tab, if any. This property is only present if the opener tab still exists.
     */
    var openerTabId: Int?

    /**
     * Whether the tab is highlighted. Works as an alias of active
     */
    var highlighted: Boolean

    /**
     * Whether the tab is active in its window. (Does not necessarily mean the window is focused.)
     */
    var active: Boolean

    /**
     * Whether the tab is pinned.
     */
    var pinned: Boolean

    /**
     * The last time the tab was accessed as the number of milliseconds since epoch.
     */
    var lastAccessed: Int?

    /**
     * Whether the tab has produced sound over the past couple of seconds (but it might not be heard if also muted). Equivalent to whether the speaker audio indicator is showing.
     */
    var audible: Boolean?

    /**
     * Current tab muted state and the reason for the last state change.
     */
    var mutedInfo: MutedInfo?

    /**
     * The URL the tab is displaying. This property is only present if the extension's manifest includes the <code>"tabs"</code> permission.
     */
    var url: String?

    /**
     * The title of the tab. This property is only present if the extension's manifest includes the <code>"tabs"</code> permission.
     */
    var title: String?

    /**
     * The URL of the tab's favicon. This property is only present if the extension's manifest includes the <code>"tabs"</code> permission. It may also be an empty string if the tab is loading.
     */
    var favIconUrl: String?

    /**
     * Either <em>loading</em> or <em>complete</em>.
     */
    var status: String?

    /**
     * True while the tab is not loaded with content.
     */
    var discarded: Boolean?

    /**
     * Whether the tab is in an incognito window.
     */
    var incognito: Boolean

    /**
     * The width of the tab in pixels.
     */
    var width: Int?

    /**
     * The height of the tab in pixels.
     */
    var height: Int?

    /**
     * The session ID used to uniquely identify a Tab obtained from the $(ref:sessions) API.
     */
    var sessionId: String?

    /**
     * The CookieStoreId used for the tab.
     */
    var cookieStoreId: String?

    /**
     * Whether the document in the tab can be rendered in reader mode.
     */
    var isArticle: Boolean?

    /**
     * Whether the document in the tab is being rendered in reader mode.
     */
    var isInReaderMode: Boolean?
}

/**
 * Defines how zoom changes are handled, i.e. which entity is responsible for the actual scaling of the page; defaults to <code>automatic</code>. */
typealias ZoomSettingsMode = String

/**
 * Defines whether zoom changes will persist for the page's origin, or only take effect in this tab; defaults to <code>per-origin</code> when in <code>automatic</code> mode, and <code>per-tab</code> otherwise. */
typealias ZoomSettingsScope = String

/**
 * Defines how zoom changes in a tab are handled and at what scope.
 */
external class ZoomSettings {
    /**
     * Defines how zoom changes are handled, i.e. which entity is responsible for the actual scaling of the page; defaults to <code>automatic</code>.
     */
    var mode: ZoomSettingsMode?

    /**
     * Defines whether zoom changes will persist for the page's origin, or only take effect in this tab; defaults to <code>per-origin</code> when in <code>automatic</code> mode, and <code>per-tab</code> otherwise.
     */
    var scope: ZoomSettingsScope?

    /**
     * Used to return the default zoom level for the current tab in calls to tabs.getZoomSettings.
     */
    var defaultZoomFactor: Int?
}

/**
 * The page settings including: orientation, scale, background, margins, headers, footers.
 */
external class PageSettings {
    /**
     * The page content orientation: 0 = portrait, 1 = landscape. Default: 0.
     */
    var orientation: Int?

    /**
     * The page content scaling factor: 1.0 = 100% = normal size. Default: 1.0.
     */
    var scaling: Int?

    /**
     * Whether the page content should shrink to fit the page width (overrides scaling). Default: true.
     */
    var shrinkToFit: Boolean?

    /**
     * Whether the page background colors should be shown. Default: false.
     */
    var showBackgroundColors: Boolean?

    /**
     * Whether the page background images should be shown. Default: false.
     */
    var showBackgroundImages: Boolean?

    /**
     * The page size unit: 0 = inches, 1 = millimeters. Default: 0.
     */
    var paperSizeUnit: Int?

    /**
     * The paper width in paper size units. Default: 8.5.
     */
    var paperWidth: Int?

    /**
     * The paper height in paper size units. Default: 11.0.
     */
    var paperHeight: Int?

    /**
     * The text for the page's left header. Default: '&T'.
     */
    var headerLeft: String?

    /**
     * The text for the page's center header. Default: ''.
     */
    var headerCenter: String?

    /**
     * The text for the page's right header. Default: '&U'.
     */
    var headerRight: String?

    /**
     * The text for the page's left footer. Default: '&PT'.
     */
    var footerLeft: String?

    /**
     * The text for the page's center footer. Default: ''.
     */
    var footerCenter: String?

    /**
     * The text for the page's right footer. Default: '&D'.
     */
    var footerRight: String?

    /**
     * The margin between the page content and the left edge of the paper (inches). Default: 0.5.
     */
    var marginLeft: Int?

    /**
     * The margin between the page content and the right edge of the paper (inches). Default: 0.5.
     */
    var marginRight: Int?

    /**
     * The margin between the page content and the top edge of the paper (inches). Default: 0.5.
     */
    var marginTop: Int?

    /**
     * The margin between the page content and the bottom edge of the paper (inches). Default: 0.5.
     */
    var marginBottom: Int?
}

/**
 * Whether the tabs have completed loading. */
typealias TabStatus = String

/**
 * The type of window. */
typealias WindowType = String

external class ConnectInfo {
    /**
     * Will be passed into onConnect for content scripts that are listening for the connection event.
     */
    var name: String?

    /**
     * Open a port to a specific $(topic:frame_ids)[frame] identified by <code>frameId</code> instead of all frames in the tab.
     */
    var frameId: Int?
}

typealias ResponseCallback = Any

external class Options {
    /**
     * Send a message to a specific $(topic:frame_ids)[frame] identified by <code>frameId</code> instead of all frames in the tab.
     */
    var frameId: Int?
}

external class CreateProperties {
    /**
     * The window to create the new tab in. Defaults to the $(topic:current-window)[current window].
     */
    var windowId: Int?

    /**
     * The position the tab should take in the window. The provided value will be clamped to between zero and the number of tabs in the window.
     */
    var index: Int?

    /**
     * The URL to navigate the tab to initially. Fully-qualified URLs must include a scheme (i.e. 'http://www.google.com', not 'www.google.com'). Relative URLs will be relative to the current page within the extension. Defaults to the New Tab Page.
     */
    var url: String?

    /**
     * Whether the tab should become the active tab in the window. Does not affect whether the window is focused (see $(ref:windows.update)). Defaults to <var>true</var>.
     */
    var active: Boolean?

    /**
     * Whether the tab should be pinned. Defaults to <var>false</var>
     */
    var pinned: Boolean?

    /**
     * The ID of the tab that opened this tab. If specified, the opener tab must be in the same window as the newly created tab.
     */
    var openerTabId: Int?

    /**
     * The CookieStoreId for the tab that opened this tab.
     */
    var cookieStoreId: String?

    /**
     * Whether the document in the tab should be opened in reader mode.
     */
    var openInReaderMode: Boolean?
}

/**
 * Match tabs against one or more $(topic:match_patterns)[URL patterns]. Note that fragment identifiers are not matched. */
typealias Url = Any

external class QueryInfo {
    /**
     * Whether the tabs are active in their windows.
     */
    var active: Boolean?

    /**
     * Whether the tabs are pinned.
     */
    var pinned: Boolean?

    /**
     * Whether the tabs are audible.
     */
    var audible: Boolean?

    /**
     * Whether the tabs are muted.
     */
    var muted: Boolean?

    /**
     * Whether the tabs are highlighted.  Works as an alias of active.
     */
    var highlighted: Boolean?

    /**
     * Whether the tabs are in the $(topic:current-window)[current window].
     */
    var currentWindow: Boolean?

    /**
     * Whether the tabs are in the last focused window.
     */
    var lastFocusedWindow: Boolean?

    /**
     * Whether the tabs have completed loading.
     */
    var status: TabStatus?

    /**
     * True while the tabs are not loaded with content.
     */
    var discarded: Boolean?

    /**
     * Match page titles against a pattern.
     */
    var title: String?

    /**
     * Match tabs against one or more $(topic:match_patterns)[URL patterns]. Note that fragment identifiers are not matched.
     */
    var url: Url?

    /**
     * The ID of the parent window, or $(ref:windows.WINDOW_ID_CURRENT) for the $(topic:current-window)[current window].
     */
    var windowId: Int?

    /**
     * The type of window the tabs are in.
     */
    var windowType: WindowType?

    /**
     * The position of the tabs within their windows.
     */
    var index: Int?

    /**
     * The CookieStoreId used for the tab.
     */
    var cookieStoreId: String?

    /**
     * The ID of the tab that opened this tab. If specified, the opener tab must be in the same window as this tab.
     */
    var openerTabId: Int?
}

/**
 * One or more tab indices to highlight. */
typealias Tabs = Any

external class HighlightInfo {
    /**
     * The window that contains the tabs.
     */
    var windowId: Int?

    /**
     * One or more tab indices to highlight.
     */
    var tabs: Tabs
}

external class UpdateProperties {
    /**
     * A URL to navigate the tab to.
     */
    var url: String?

    /**
     * Whether the tab should be active. Does not affect whether the window is focused (see $(ref:windows.update)).
     */
    var active: Boolean?

    /**
     * Whether the tab should be pinned.
     */
    var pinned: Boolean?

    /**
     * Whether the tab should be muted.
     */
    var muted: Boolean?

    /**
     * The ID of the tab that opened this tab. If specified, the opener tab must be in the same window as this tab.
     */
    var openerTabId: Int?

    /**
     * Whether the load should replace the current history entry for the tab.
     */
    var loadReplace: Boolean?
}

/**
 * The tab or list of tabs to move. */
typealias TabIds = Any

external class MoveProperties {
    /**
     * Defaults to the window the tab is currently in.
     */
    var windowId: Int?

    /**
     * The position to move the window to. -1 will place the tab at the end of the window.
     */
    var index: Int
}

/**
 * Details about the moved tabs. */
typealias Tabs2 = Any

external class ReloadProperties {
    /**
     * Whether using any local cache. Default is false.
     */
    var bypassCache: Boolean?
}

/**
 * The tab or list of tabs to close. */
typealias TabIds2 = Any

/**
 * The tab or list of tabs to discard. */
typealias TabIds3 = Any

/**
 * Lists the changes to the state of the tab that was updated.
 */
external class ChangeInfo {
    /**
     * The status of the tab. Can be either <em>loading</em> or <em>complete</em>.
     */
    var status: String?

    /**
     * True while the tab is not loaded with content.
     */
    var discarded: Boolean?

    /**
     * The tab's URL if it has changed.
     */
    var url: String?

    /**
     * The tab's new pinned state.
     */
    var pinned: Boolean?

    /**
     * The tab's new audible state.
     */
    var audible: Boolean?

    /**
     * The tab's new muted state and the reason for the change.
     */
    var mutedInfo: MutedInfo?

    /**
     * The tab's new favicon URL.
     */
    var favIconUrl: String?
}

external class MoveInfo {
    var windowId: Int

    var fromIndex: Int

    var toIndex: Int
}

external class SelectInfo {
    /**
     * The ID of the window the selected tab changed inside of.
     */
    var windowId: Int
}

external class SelectInfo2 {
    /**
     * The ID of the window the selected tab changed inside of.
     */
    var windowId: Int
}

external class ActiveInfo {
    /**
     * The ID of the tab that has become active.
     */
    var tabId: Int

    /**
     * The ID of the window the active tab changed inside of.
     */
    var windowId: Int
}

external class SelectInfo3 {
    /**
     * The window whose tabs changed.
     */
    var windowId: Int

    /**
     * All highlighted tabs in the window.
     */
    var tabIds: Array<Int>
}

external class HighlightInfo2 {
    /**
     * The window whose tabs changed.
     */
    var windowId: Int

    /**
     * All highlighted tabs in the window.
     */
    var tabIds: Array<Int>
}

external class DetachInfo {
    var oldWindowId: Int

    var oldPosition: Int
}

external class AttachInfo {
    var newWindowId: Int

    var newPosition: Int
}

external class RemoveInfo {
    /**
     * The window whose tab is closed.
     */
    var windowId: Int

    /**
     * True when the tab is being closed because its window is being closed.
     */
    var isWindowClosing: Boolean
}

external class ZoomChangeInfo {
    var tabId: Int

    var oldZoomFactor: Int

    var newZoomFactor: Int

    var zoomSettings: ZoomSettings
}

external class TabsNamespace {
    val onCreated: Event<(tab: Tab) -> Unit>

    val onUpdated: Event<(
            tabId: Int,
            changeInfo: ChangeInfo,
            tab: Tab
    ) -> Unit>

    val onMoved: Event<(tabId: Int, moveInfo: MoveInfo) -> Unit>

    val onActivated: Event<(activeInfo: ActiveInfo) -> Unit>

    val onHighlighted: Event<(highlightInfo: HighlightInfo2) -> Unit>

    val onDetached: Event<(tabId: Int, detachInfo: DetachInfo) -> Unit>

    val onAttached: Event<(tabId: Int, attachInfo: AttachInfo) -> Unit>

    val onRemoved: Event<(tabId: Int, removeInfo: RemoveInfo) -> Unit>

    val onReplaced: Event<(addedTabId: Int, removedTabId: Int) -> Unit>

    val onZoomChange: Event<(ZoomChangeInfo: ZoomChangeInfo) -> Unit>

    /**
     * Retrieves details about the specified tab.
     */
    fun get(tabId: Int): Promise<Tab>

    /**
     * Gets the tab that this script call is being made from. May be undefined if called from a non-tab context (for example: a background page or popup view).
     */
    fun getCurrent(): Promise<Tab?>

    /**
     * Connects to the content script(s) in the specified tab. The $(ref:runtime.onConnect) event is fired in each content script running in the specified tab for the current extension. For more details, see $(topic:messaging)[Content Script Messaging].
     */
    fun connect(tabId: Int, connectInfo: ConnectInfo? = definedExternally)

    /**
     * Sends a single message to the content script(s) in the specified tab, with an optional callback to run when a response is sent back.  The $(ref:runtime.onMessage) event is fired in each content script running in the specified tab for the current extension.
     */
    fun sendMessage(
            tabId: Int,
            message: Any,
            options: Options? = definedExternally
    ): Promise<Any>

    /**
     * Creates a new tab.
     */
    fun create(createProperties: CreateProperties): Promise<Tab?>

    /**
     * Duplicates a tab.
     */
    fun duplicate(tabId: Int): Promise<Tab?>

    /**
     * Gets all tabs that have the specified properties, or all tabs if no properties are specified.
     */
    fun query(queryInfo: QueryInfo): Promise<Array<Tab>>

    /**
     * Modifies the properties of a tab. Properties that are not specified in <var>updateProperties</var> are not modified.
     */
    fun update(tabId: Int? = definedExternally, updateProperties: UpdateProperties): Promise<Tab?>

    /**
     * Moves one or more tabs to a new position within its window, or to a new window. Note that tabs can only be moved to and from normal (window.type === "normal") windows.
     */
    fun move(tabIds: Int, moveProperties: MoveProperties): Promise<Tabs2>

    /**
     * Moves one or more tabs to a new position within its window, or to a new window. Note that tabs can only be moved to and from normal (window.type === "normal") windows.
     */
    fun move(tabIds: Array<Int>, moveProperties: MoveProperties): Promise<Tabs2>

    /**
     * Reload a tab.
     */
    fun reload(tabId: Int? = definedExternally, reloadProperties: ReloadProperties? = definedExternally): Promise<Any>

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
    fun detectLanguage(tabId: Int? = definedExternally): Promise<String>

    /**
     * Toggles reader mode for the document in the tab.
     */
    fun toggleReaderMode(tabId: Int? = definedExternally): Promise<Any>

    /**
     * Captures the visible area of the currently active tab in the specified window. You must have $(topic:declare_permissions)[&lt;all_urls&gt;] permission to use this method.
     */
    fun captureVisibleTab(windowId: Int? = definedExternally, options: ImageDetails? = definedExternally): Promise<String>

    /**
     * Injects JavaScript code into a page. For details, see the $(topic:content_scripts)[programmatic injection] section of the content scripts doc.
     */
    fun executeScript(tabId: Int? = definedExternally, details: InjectDetails): Promise<Array<Any>>

    /**
     * Injects CSS into a page. For details, see the $(topic:content_scripts)[programmatic injection] section of the content scripts doc.
     */
    fun insertCSS(tabId: Int? = definedExternally, details: InjectDetails): Promise<Any>

    /**
     * Removes injected CSS from a page. For details, see the $(topic:content_scripts)[programmatic injection] section of the content scripts doc.
     */
    fun removeCSS(tabId: Int? = definedExternally, details: InjectDetails): Promise<Any>

    /**
     * Zooms a specified tab.
     */
    fun setZoom(tabId: Int? = definedExternally, zoomFactor: Int): Promise<Any>

    /**
     * Gets the current zoom factor of a specified tab.
     */
    fun getZoom(tabId: Int? = definedExternally): Promise<Int>

    /**
     * Sets the zoom settings for a specified tab, which define how zoom changes are handled. These settings are reset to defaults upon navigating the tab.
     */
    fun setZoomSettings(tabId: Int? = definedExternally, zoomSettings: ZoomSettings): Promise<Any>

    /**
     * Gets the current zoom settings of a specified tab.
     */
    fun getZoomSettings(tabId: Int? = definedExternally): Promise<ZoomSettings>

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
