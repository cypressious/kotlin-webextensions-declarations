package tabs

import extensionTypes.ImageDetails
import extensionTypes.InjectDetails
import kotlin.js.Promise
import runtime.Port
import webextensions.Event
import windows.Window

/**
 * An event that caused a muted state change. */
typealias MutedInfoReason = String

/**
 * Tab muted state and the reason for the last state change.
 * @param muted Whether the tab is prevented from playing sound (but hasn't necessarily recently
        produced sound). Equivalent to whether the muted audio indicator is showing.
 * @param reason The reason the tab was muted or unmuted. Not set if the tab's mute state has never
        been changed.
 * @param extensionId The ID of the extension that changed the muted state. Not set if an extension
        was not the reason the muted state last changed.
 */
class MutedInfo(
    var muted: Boolean,
    var reason: MutedInfoReason? = null,
    var extensionId: String? = null
)

/**
 * Tab sharing state for screen, microphone and camera.
 * @param screen If the tab is sharing the screen the value will be one of "Screen", "Window", or
        "Application", or undefined if not screen sharing.
 * @param camera True if the tab is using the camera.
 * @param microphone True if the tab is using the microphone.
 */
class SharingState(
    var screen: String? = null,
    var camera: Boolean,
    var microphone: Boolean
)

/**
 * @param id The ID of the tab. Tab IDs are unique within a browser session. Under some
        circumstances a Tab may not be assigned an ID, for example when querying foreign tabs using
        the $(ref:sessions) API, in which case a session ID may be present. Tab ID can also be set
        to $(ref:tabs.TAB_ID_NONE) for apps and devtools windows.
 * @param index The zero-based index of the tab within its window.
 * @param windowId The ID of the window the tab is contained within.
 * @param openerTabId The ID of the tab that opened this tab, if any. This property is only present
        if the opener tab still exists.
 * @param highlighted Whether the tab is highlighted. Works as an alias of active
 * @param active Whether the tab is active in its window. (Does not necessarily mean the window is
        focused.)
 * @param pinned Whether the tab is pinned.
 * @param lastAccessed The last time the tab was accessed as the number of milliseconds since epoch.
 * @param audible Whether the tab has produced sound over the past couple of seconds (but it might
        not be heard if also muted). Equivalent to whether the speaker audio indicator is showing.
 * @param mutedInfo Current tab muted state and the reason for the last state change.
 * @param url The URL the tab is displaying. This property is only present if the extension's
        manifest includes the <code>"tabs"</code> permission.
 * @param title The title of the tab. This property is only present if the extension's manifest
        includes the <code>"tabs"</code> permission.
 * @param favIconUrl The URL of the tab's favicon. This property is only present if the extension's
        manifest includes the <code>"tabs"</code> permission. It may also be an empty string if the
        tab is loading.
 * @param status Either <em>loading</em> or <em>complete</em>.
 * @param discarded True while the tab is not loaded with content.
 * @param incognito Whether the tab is in an incognito window.
 * @param width The width of the tab in pixels.
 * @param height The height of the tab in pixels.
 * @param hidden True if the tab is hidden.
 * @param sessionId The session ID used to uniquely identify a Tab obtained from the $(ref:sessions)
        API.
 * @param cookieStoreId The CookieStoreId used for the tab.
 * @param isArticle Whether the document in the tab can be rendered in reader mode.
 * @param isInReaderMode Whether the document in the tab is being rendered in reader mode.
 * @param sharingState Current tab sharing state for screen, microphone and camera.
 * @param attention Whether the tab is drawing attention.
 * @param successorTabId The ID of this tab's successor, if any; $(ref:tabs.TAB_ID_NONE) otherwise.
 */
class Tab(
    var id: Int? = null,
    var index: Int,
    var windowId: Int? = null,
    var openerTabId: Int? = null,
    var highlighted: Boolean,
    var active: Boolean,
    var pinned: Boolean,
    var lastAccessed: Int? = null,
    var audible: Boolean? = null,
    var mutedInfo: MutedInfo? = null,
    var url: String? = null,
    var title: String? = null,
    var favIconUrl: String? = null,
    var status: String? = null,
    var discarded: Boolean? = null,
    var incognito: Boolean,
    var width: Int? = null,
    var height: Int? = null,
    var hidden: Boolean? = null,
    var sessionId: String? = null,
    var cookieStoreId: String? = null,
    var isArticle: Boolean? = null,
    var isInReaderMode: Boolean? = null,
    var sharingState: SharingState? = null,
    var attention: Boolean? = null,
    var successorTabId: Int? = null
)

/**
 * Defines how zoom changes are handled, i.e. which entity is responsible for the actual scaling of
        the page; defaults to <code>automatic</code>. */
typealias ZoomSettingsMode = String

/**
 * Defines whether zoom changes will persist for the page's origin, or only take effect in this tab;
        defaults to <code>per-origin</code> when in <code>automatic</code> mode, and
        <code>per-tab</code> otherwise. */
typealias ZoomSettingsScope = String

/**
 * Defines how zoom changes in a tab are handled and at what scope.
 * @param mode Defines how zoom changes are handled, i.e. which entity is responsible for the actual
        scaling of the page; defaults to <code>automatic</code>.
 * @param scope Defines whether zoom changes will persist for the page's origin, or only take effect
        in this tab; defaults to <code>per-origin</code> when in <code>automatic</code> mode, and
        <code>per-tab</code> otherwise.
 * @param defaultZoomFactor Used to return the default zoom level for the current tab in calls to
        tabs.getZoomSettings.
 */
class ZoomSettings(
    var mode: ZoomSettingsMode? = null,
    var scope: ZoomSettingsScope? = null,
    var defaultZoomFactor: Float? = null
)

/**
 * The page settings including: orientation, scale, background, margins, headers, footers.
 * @param paperSizeUnit The page size unit: 0 = inches, 1 = millimeters. Default: 0.
 * @param paperWidth The paper width in paper size units. Default: 8.5.
 * @param paperHeight The paper height in paper size units. Default: 11.0.
 * @param orientation The page content orientation: 0 = portrait, 1 = landscape. Default: 0.
 * @param scaling The page content scaling factor: 1.0 = 100% = normal size. Default: 1.0.
 * @param shrinkToFit Whether the page content should shrink to fit the page width (overrides
        scaling). Default: true.
 * @param showBackgroundColors Whether the page background colors should be shown. Default: false.
 * @param showBackgroundImages Whether the page background images should be shown. Default: false.
 * @param edgeLeft The spacing between the left header/footer and the left edge of the paper
        (inches). Default: 0.
 * @param edgeRight The spacing between the right header/footer and the right edge of the paper
        (inches). Default: 0.
 * @param edgeTop The spacing between the top of the headers and the top edge of the paper (inches).
        Default: 0
 * @param edgeBottom The spacing between the bottom of the footers and the bottom edge of the paper
        (inches). Default: 0.
 * @param marginLeft The margin between the page content and the left edge of the paper (inches).
        Default: 0.5.
 * @param marginRight The margin between the page content and the right edge of the paper (inches).
        Default: 0.5.
 * @param marginTop The margin between the page content and the top edge of the paper (inches).
        Default: 0.5.
 * @param marginBottom The margin between the page content and the bottom edge of the paper
        (inches). Default: 0.5.
 * @param headerLeft The text for the page's left header. Default: '&T'.
 * @param headerCenter The text for the page's center header. Default: ''.
 * @param headerRight The text for the page's right header. Default: '&U'.
 * @param footerLeft The text for the page's left footer. Default: '&PT'.
 * @param footerCenter The text for the page's center footer. Default: ''.
 * @param footerRight The text for the page's right footer. Default: '&D'.
 */
class PageSettings(
    var paperSizeUnit: Int? = null,
    var paperWidth: Float? = null,
    var paperHeight: Float? = null,
    var orientation: Int? = null,
    var scaling: Float? = null,
    var shrinkToFit: Boolean? = null,
    var showBackgroundColors: Boolean? = null,
    var showBackgroundImages: Boolean? = null,
    var edgeLeft: Float? = null,
    var edgeRight: Float? = null,
    var edgeTop: Float? = null,
    var edgeBottom: Float? = null,
    var marginLeft: Float? = null,
    var marginRight: Float? = null,
    var marginTop: Float? = null,
    var marginBottom: Float? = null,
    var headerLeft: String? = null,
    var headerCenter: String? = null,
    var headerRight: String? = null,
    var footerLeft: String? = null,
    var footerCenter: String? = null,
    var footerRight: String? = null
)

/**
 * Whether the tabs have completed loading. */
typealias TabStatus = String

/**
 * The type of window. */
typealias WindowType = String

/**
 * Event names supported in onUpdated. */
typealias UpdatePropertyName = String

/**
 * An object describing filters to apply to tabs.onUpdated events.
 * @param urls A list of URLs or URL patterns. Events that cannot match any of the URLs will be
        filtered out.  Filtering with urls requires the <code>"tabs"</code> or 
        <code>"activeTab"</code> permission.
 * @param properties A list of property names. Events that do not match any of the names will be
        filtered out.
 */
class UpdateFilter(
    var urls: Array<String>? = null,
    var properties: Array<UpdatePropertyName>? = null,
    var tabId: Int? = null,
    var windowId: Int? = null
)

/**
 * @param name Will be passed into onConnect for content scripts that are listening for the
        connection event.
 * @param frameId Open a port to a specific $(topic:frame_ids)[frame] identified by
        <code>frameId</code> instead of all frames in the tab.
 */
class ConnectInfo(
    var name: String? = null,
    var frameId: Int? = null
)

/**
 * @param frameId Send a message to a specific $(topic:frame_ids)[frame] identified by
        <code>frameId</code> instead of all frames in the tab.
 */
class Options(
    var frameId: Int? = null
)

/**
 * @param windowId The window to create the new tab in. Defaults to the
        $(topic:current-window)[current window].
 * @param index The position the tab should take in the window. The provided value will be clamped
        to between zero and the number of tabs in the window.
 * @param url The URL to navigate the tab to initially. Fully-qualified URLs must include a scheme
        (i.e. 'http://www.google.com', not 'www.google.com'). Relative URLs will be relative to the
        current page within the extension. Defaults to the New Tab Page.
 * @param active Whether the tab should become the active tab in the window. Does not affect whether
        the window is focused (see $(ref:windows.update)). Defaults to <var>true</var>.
 * @param pinned Whether the tab should be pinned. Defaults to <var>false</var>
 * @param openerTabId The ID of the tab that opened this tab. If specified, the opener tab must be
        in the same window as the newly created tab.
 * @param cookieStoreId The CookieStoreId for the tab that opened this tab.
 * @param openInReaderMode Whether the document in the tab should be opened in reader mode.
 * @param discarded Whether the tab is marked as 'discarded' when created.
 * @param title The title used for display if the tab is created in discarded mode.
 */
class CreateProperties(
    var windowId: Int? = null,
    var index: Int? = null,
    var url: String? = null,
    var active: Boolean? = null,
    var pinned: Boolean? = null,
    var openerTabId: Int? = null,
    var cookieStoreId: String? = null,
    var openInReaderMode: Boolean? = null,
    var discarded: Boolean? = null,
    var title: String? = null
)

/**
 * Match tabs against one or more $(topic:match_patterns)[URL patterns]. Note that fragment
        identifiers are not matched. */
typealias Url = Any

/**
 * True for any screen sharing, or a string to specify type of screen sharing. */
typealias Screen = Any

/**
 * @param active Whether the tabs are active in their windows.
 * @param attention Whether the tabs are drawing attention.
 * @param pinned Whether the tabs are pinned.
 * @param audible Whether the tabs are audible.
 * @param muted Whether the tabs are muted.
 * @param highlighted Whether the tabs are highlighted.  Works as an alias of active.
 * @param currentWindow Whether the tabs are in the $(topic:current-window)[current window].
 * @param lastFocusedWindow Whether the tabs are in the last focused window.
 * @param status Whether the tabs have completed loading.
 * @param discarded True while the tabs are not loaded with content.
 * @param hidden True while the tabs are hidden.
 * @param title Match page titles against a pattern.
 * @param url Match tabs against one or more $(topic:match_patterns)[URL patterns]. Note that
        fragment identifiers are not matched.
 * @param windowId The ID of the parent window, or $(ref:windows.WINDOW_ID_CURRENT) for the
        $(topic:current-window)[current window].
 * @param windowType The type of window the tabs are in.
 * @param index The position of the tabs within their windows.
 * @param cookieStoreId The CookieStoreId used for the tab.
 * @param openerTabId The ID of the tab that opened this tab. If specified, the opener tab must be
        in the same window as this tab.
 * @param screen True for any screen sharing, or a string to specify type of screen sharing.
 * @param camera True if the tab is using the camera.
 * @param microphone True if the tab is using the microphone.
 */
class QueryInfo(
    var active: Boolean? = null,
    var attention: Boolean? = null,
    var pinned: Boolean? = null,
    var audible: Boolean? = null,
    var muted: Boolean? = null,
    var highlighted: Boolean? = null,
    var currentWindow: Boolean? = null,
    var lastFocusedWindow: Boolean? = null,
    var status: TabStatus? = null,
    var discarded: Boolean? = null,
    var hidden: Boolean? = null,
    var title: String? = null,
    var url: Url? = null,
    var windowId: Int? = null,
    var windowType: WindowType? = null,
    var index: Int? = null,
    var cookieStoreId: String? = null,
    var openerTabId: Int? = null,
    var screen: Screen? = null,
    var camera: Boolean? = null,
    var microphone: Boolean? = null
)

/**
 * One or more tab indices to highlight. */
typealias Tabs = Any

/**
 * @param windowId The window that contains the tabs.
 * @param populate If true, the $(ref:windows.Window) returned will have a <var>tabs</var> property
        that contains a list of the $(ref:tabs.Tab) objects. The <code>Tab</code> objects only
        contain the <code>url</code>, <code>title</code> and <code>favIconUrl</code> properties if
        the extension's manifest file includes the <code>"tabs"</code> permission. If false, the
        $(ref:windows.Window) won't have the <var>tabs</var> property.
 * @param tabs One or more tab indices to highlight.
 */
class HighlightInfo(
    var windowId: Int? = null,
    var populate: Boolean? = null,
    var tabs: Tabs
)

/**
 * @param url A URL to navigate the tab to.
 * @param active Whether the tab should be active. Does not affect whether the window is focused
        (see $(ref:windows.update)).
 * @param highlighted Adds or removes the tab from the current selection.
 * @param pinned Whether the tab should be pinned.
 * @param muted Whether the tab should be muted.
 * @param openerTabId The ID of the tab that opened this tab. If specified, the opener tab must be
        in the same window as this tab.
 * @param loadReplace Whether the load should replace the current history entry for the tab.
 * @param successorTabId The ID of this tab's successor. If specified, the successor tab must be in
        the same window as this tab.
 */
class UpdateProperties(
    var url: String? = null,
    var active: Boolean? = null,
    var highlighted: Boolean? = null,
    var pinned: Boolean? = null,
    var muted: Boolean? = null,
    var openerTabId: Int? = null,
    var loadReplace: Boolean? = null,
    var successorTabId: Int? = null
)

/**
 * The tab or list of tabs to move. */
typealias TabIds = Any

/**
 * @param windowId Defaults to the window the tab is currently in.
 * @param index The position to move the window to. -1 will place the tab at the end of the window.
 */
class MoveProperties(
    var windowId: Int? = null,
    var index: Int
)

/**
 * Details about the moved tabs. */
typealias Tabs2 = Any

/**
 * @param bypassCache Whether using any local cache. Default is false.
 */
class ReloadProperties(
    var bypassCache: Boolean? = null
)

/**
 * The tab or list of tabs to close. */
typealias TabIds2 = Any

/**
 * The tab or list of tabs to discard. */
typealias TabIds3 = Any

/**
 * The TAB ID or list of TAB IDs to show. */
typealias TabIds4 = Any

/**
 * The TAB ID or list of TAB IDs to hide. */
typealias TabIds5 = Any

/**
 * @param append Whether to move the tabs before (false) or after (true) tabId in the succession.
        Defaults to false.
 * @param insert Whether to link up the current predecessors or successor (depending on
        options.append) of tabId to the other side of the chain after it is prepended or appended.
        If true, one of the following happens: if options.append is false, the first tab in the
        array is set as the successor of any current predecessors of tabId; if options.append is
        true, the current successor of tabId is set as the successor of the last tab in the array.
        Defaults to false.
 */
class Options2(
    var append: Boolean? = null,
    var insert: Boolean? = null
)

/**
 * Lists the changes to the state of the tab that was updated.
 * @param attention The tab's new attention state.
 * @param audible The tab's new audible state.
 * @param discarded True while the tab is not loaded with content.
 * @param favIconUrl The tab's new favicon URL. This property is only present if the extension's
        manifest includes the <code>"tabs"</code> permission.
 * @param hidden The tab's new hidden state.
 * @param isArticle Whether the document in the tab can be rendered in reader mode.
 * @param mutedInfo The tab's new muted state and the reason for the change.
 * @param pinned The tab's new pinned state.
 * @param sharingState The tab's new sharing state for screen, microphone and camera.
 * @param status The status of the tab. Can be either <em>loading</em> or <em>complete</em>.
 * @param title The title of the tab if it has changed. This property is only present if the
        extension's manifest includes the <code>"tabs"</code> permission.
 * @param url The tab's URL if it has changed. This property is only present if the extension's
        manifest includes the <code>"tabs"</code> permission.
 */
class ChangeInfo(
    var attention: Boolean? = null,
    var audible: Boolean? = null,
    var discarded: Boolean? = null,
    var favIconUrl: String? = null,
    var hidden: Boolean? = null,
    var isArticle: Boolean? = null,
    var mutedInfo: MutedInfo? = null,
    var pinned: Boolean? = null,
    var sharingState: SharingState? = null,
    var status: String? = null,
    var title: String? = null,
    var url: String? = null
)

class MoveInfo(
    var windowId: Int,
    var fromIndex: Int,
    var toIndex: Int
)

/**
 * @param windowId The ID of the window the selected tab changed inside of.
 */
class SelectInfo(
    var windowId: Int
)

/**
 * @param windowId The ID of the window the selected tab changed inside of.
 */
class SelectInfo2(
    var windowId: Int
)

/**
 * @param tabId The ID of the tab that has become active.
 * @param previousTabId The ID of the tab that was previously active, if that tab is still open.
 * @param windowId The ID of the window the active tab changed inside of.
 */
class ActiveInfo(
    var tabId: Int,
    var previousTabId: Int? = null,
    var windowId: Int
)

/**
 * @param windowId The window whose tabs changed.
 * @param tabIds All highlighted tabs in the window.
 */
class SelectInfo3(
    var windowId: Int,
    var tabIds: Array<Int>
)

/**
 * @param windowId The window whose tabs changed.
 * @param tabIds All highlighted tabs in the window.
 */
class HighlightInfo2(
    var windowId: Int,
    var tabIds: Array<Int>
)

class DetachInfo(var oldWindowId: Int, var oldPosition: Int)

class AttachInfo(var newWindowId: Int, var newPosition: Int)

/**
 * @param windowId The window whose tab is closed.
 * @param isWindowClosing True when the tab is being closed because its window is being closed.
 */
class RemoveInfo(
    var windowId: Int,
    var isWindowClosing: Boolean
)

class ZoomChangeInfo(
    var tabId: Int,
    var oldZoomFactor: Float,
    var newZoomFactor: Float,
    var zoomSettings: ZoomSettings
)

external class TabsNamespace {
    /**
     * Fired when a tab is created. Note that the tab's URL may not be set at the time this event
            fired, but you can listen to onUpdated events to be notified when a URL is set.
     *
     * @param tab Details of the tab that was created. */
    val onCreated: Event<(tab: Tab) -> Unit>

    /**
     * Fired when a tab is updated.
     *
     * @param tabId null
     * @param changeInfo Lists the changes to the state of the tab that was updated.
     * @param tab Gives the state of the tab that was updated. */
    val onUpdated: Event<(
        tabId: Int,
        changeInfo: ChangeInfo,
        tab: Tab
    ) -> Unit>

    /**
     * Fired when a tab is moved within a window. Only one move event is fired, representing the tab
            the user directly moved. Move events are not fired for the other tabs that must move in
            response. This event is not fired when a tab is moved between windows. For that, see
            $(ref:tabs.onDetached).
     *
     * @param tabId null
     * @param moveInfo null */
    val onMoved: Event<(tabId: Int, moveInfo: MoveInfo) -> Unit>

    /**
     * Fires when the active tab in a window changes. Note that the tab's URL may not be set at the
            time this event fired, but you can listen to onUpdated events to be notified when a URL
            is set.
     *
     * @param activeInfo null */
    val onActivated: Event<(activeInfo: ActiveInfo) -> Unit>

    /**
     * Fired when the highlighted or selected tabs in a window changes.
     *
     * @param highlightInfo null */
    val onHighlighted: Event<(highlightInfo: HighlightInfo2) -> Unit>

    /**
     * Fired when a tab is detached from a window, for example because it is being moved between
            windows.
     *
     * @param tabId null
     * @param detachInfo null */
    val onDetached: Event<(tabId: Int, detachInfo: DetachInfo) -> Unit>

    /**
     * Fired when a tab is attached to a window, for example because it was moved between windows.
     *
     * @param tabId null
     * @param attachInfo null */
    val onAttached: Event<(tabId: Int, attachInfo: AttachInfo) -> Unit>

    /**
     * Fired when a tab is closed.
     *
     * @param tabId null
     * @param removeInfo null */
    val onRemoved: Event<(tabId: Int, removeInfo: RemoveInfo) -> Unit>

    /**
     * Fired when a tab is replaced with another tab due to prerendering or instant.
     *
     * @param addedTabId null
     * @param removedTabId null */
    val onReplaced: Event<(addedTabId: Int, removedTabId: Int) -> Unit>

    /**
     * Fired when a tab is zoomed.
     *
     * @param ZoomChangeInfo null */
    val onZoomChange: Event<(ZoomChangeInfo: ZoomChangeInfo) -> Unit>

    /**
     * Retrieves details about the specified tab.
     */
    fun get(tabId: Int): Promise<Tab>

    /**
     * Gets the tab that this script call is being made from. May be undefined if called from a
            non-tab context (for example: a background page or popup view).
     */
    fun getCurrent(): Promise<Tab?>

    /**
     * Connects to the content script(s) in the specified tab. The $(ref:runtime.onConnect) event is
            fired in each content script running in the specified tab for the current extension. For
            more details, see $(topic:messaging)[Content Script Messaging].
     */
    fun connect(tabId: Int, connectInfo: ConnectInfo? = definedExternally): Port

    /**
     * Sends a single message to the content script(s) in the specified tab, with an optional
            callback to run when a response is sent back.  The $(ref:runtime.onMessage) event is
            fired in each content script running in the specified tab for the current extension.
     */
    fun sendMessage(
        tabId: Int,
        message: dynamic,
        options: Options? = definedExternally
    ): Promise<dynamic>

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
     * Highlights the given tabs.
     */
    fun highlight(highlightInfo: HighlightInfo): Promise<Window>

    /**
     * Modifies the properties of a tab. Properties that are not specified in
            <var>updateProperties</var> are not modified.
     */
    fun update(tabId: Int? = definedExternally, updateProperties: UpdateProperties): Promise<Tab?>

    /**
     * Moves one or more tabs to a new position within its window, or to a new window. Note that
            tabs can only be moved to and from normal (window.type === "normal") windows.
     */
    fun move(tabIds: Int, moveProperties: MoveProperties): Promise<Tabs2>

    /**
     * Moves one or more tabs to a new position within its window, or to a new window. Note that
            tabs can only be moved to and from normal (window.type === "normal") windows.
     */
    fun move(tabIds: Array<Int>, moveProperties: MoveProperties): Promise<Tabs2>

    /**
     * Reload a tab.
     */
    fun reload(tabId: Int? = definedExternally, reloadProperties: ReloadProperties? =
            definedExternally): Promise<Any>

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
     * Captures the visible area of a specified tab. You must have
            $(topic:declare_permissions)[&lt;all_urls&gt;] permission to use this method.
     */
    fun captureTab(tabId: Int? = definedExternally, options: ImageDetails? = definedExternally):
            Promise<Any>

    /**
     * Captures the visible area of the currently active tab in the specified window. You must have
            $(topic:declare_permissions)[&lt;all_urls&gt;] permission to use this method.
     */
    fun captureVisibleTab(windowId: Int? = definedExternally, options: ImageDetails? =
            definedExternally): Promise<String>

    /**
     * Injects JavaScript code into a page. For details, see the
            $(topic:content_scripts)[programmatic injection] section of the content scripts doc.
     */
    fun executeScript(tabId: Int? = definedExternally, details: InjectDetails):
            Promise<Array<dynamic>?>

    /**
     * Injects CSS into a page. For details, see the $(topic:content_scripts)[programmatic
            injection] section of the content scripts doc.
     */
    fun insertCSS(tabId: Int? = definedExternally, details: InjectDetails): Promise<Any>

    /**
     * Removes injected CSS from a page. For details, see the $(topic:content_scripts)[programmatic
            injection] section of the content scripts doc.
     */
    fun removeCSS(tabId: Int? = definedExternally, details: InjectDetails): Promise<Any>

    /**
     * Zooms a specified tab.
     */
    fun setZoom(tabId: Int? = definedExternally, zoomFactor: Float): Promise<Any>

    /**
     * Gets the current zoom factor of a specified tab.
     */
    fun getZoom(tabId: Int? = definedExternally): Promise<Float>

    /**
     * Sets the zoom settings for a specified tab, which define how zoom changes are handled. These
            settings are reset to defaults upon navigating the tab.
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

    /**
     * Shows one or more tabs.
     */
    fun show(tabIds: Int): Promise<Any>

    /**
     * Shows one or more tabs.
     */
    fun show(tabIds: Array<Int>): Promise<Any>

    /**
     * Hides one or more tabs. The <code>"tabHide"</code> permission is required to hide tabs.  Not
            all tabs are hidable.  Returns an array of hidden tabs.
     */
    fun hide(tabIds: Int): Promise<Any>

    /**
     * Hides one or more tabs. The <code>"tabHide"</code> permission is required to hide tabs.  Not
            all tabs are hidable.  Returns an array of hidden tabs.
     */
    fun hide(tabIds: Array<Int>): Promise<Any>

    /**
     * Removes an array of tabs from their lines of succession and prepends or appends them in a
            chain to another tab.
     */
    fun moveInSuccession(
        tabIds: Array<Int>,
        tabId: Int? = definedExternally,
        options: Options2? = definedExternally
    ): Promise<Any>
}
