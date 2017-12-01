package webNavigation

import browser.Event
import kotlin.js.Promise

/**
 * Cause of the navigation. The same transition types as defined in the history API are used. These are the same transition types as defined in the $(topic:transition_types)[history API] except with <code>"start_page"</code> in place of <code>"auto_toplevel"</code> (for backwards compatibility). */
typealias TransitionType = String

typealias TransitionQualifier = String

external class EventUrlFilters {
    val url: Array<events.UrlFilter>
}

/**
 * Information about the frame to retrieve information about.
 */
class Details(
        /**
         * The ID of the tab in which the frame is.
         */
        val tabId: Int,
        /**
         * The ID of the process runs the renderer for this tab.
         */
        val processId: Int?,
        /**
         * The ID of the frame in the given tab.
         */
        val frameId: Int
)

/**
 * Information about the requested frame, null if the specified frame ID and/or tab ID are invalid.
 */
external class Details2 {
    /**
     * True if the last navigation in this frame was interrupted by an error, i.e. the onErrorOccurred event fired.
     */
    val errorOccurred: Boolean?

    /**
     * The URL currently associated with this frame, if the frame identified by the frameId existed at one point in the given tab. The fact that an URL is associated with a given frameId does not imply that the corresponding frame still exists.
     */
    val url: String

    /**
     * The ID of the tab in which the frame is.
     */
    val tabId: Int

    /**
     * The ID of the frame. 0 indicates that this is the main frame; a positive value indicates the ID of a subframe.
     */
    val frameId: Int

    /**
     * ID of frame that wraps the frame. Set to -1 of no parent frame exists.
     */
    val parentFrameId: Int
}

/**
 * Information about the tab to retrieve all frames from.
 */
class Details3(/**
 * The ID of the tab.
 */
val tabId: Int)

external class Details4 {
    /**
     * True if the last navigation in this frame was interrupted by an error, i.e. the onErrorOccurred event fired.
     */
    val errorOccurred: Boolean?

    /**
     * The ID of the tab in which the frame is.
     */
    val tabId: Int

    /**
     * The ID of the frame. 0 indicates that this is the main frame; a positive value indicates the ID of a subframe.
     */
    val frameId: Int

    /**
     * ID of frame that wraps the frame. Set to -1 of no parent frame exists.
     */
    val parentFrameId: Int

    /**
     * The URL currently associated with this frame.
     */
    val url: String
}

external class Details5 {
    /**
     * The ID of the tab in which the navigation is about to occur.
     */
    val tabId: Int

    val url: String

    /**
     * 0 indicates the navigation happens in the tab content window; a positive value indicates navigation in a subframe. Frame IDs are unique for a given tab and process.
     */
    val frameId: Int

    /**
     * ID of frame that wraps the frame. Set to -1 of no parent frame exists.
     */
    val parentFrameId: Int

    /**
     * The time when the browser was about to start the navigation, in milliseconds since the epoch.
     */
    val timeStamp: Int
}

external class Details6 {
    /**
     * The ID of the tab in which the navigation occurs.
     */
    val tabId: Int

    val url: String

    /**
     * 0 indicates the navigation happens in the tab content window; a positive value indicates navigation in a subframe. Frame IDs are unique within a tab.
     */
    val frameId: Int

    /**
     * The time when the navigation was committed, in milliseconds since the epoch.
     */
    val timeStamp: Int
}

external class Details7 {
    /**
     * The ID of the tab in which the navigation occurs.
     */
    val tabId: Int

    val url: String

    /**
     * 0 indicates the navigation happens in the tab content window; a positive value indicates navigation in a subframe. Frame IDs are unique within a tab.
     */
    val frameId: Int

    /**
     * The time when the page's DOM was fully constructed, in milliseconds since the epoch.
     */
    val timeStamp: Int
}

external class Details8 {
    /**
     * The ID of the tab in which the navigation occurs.
     */
    val tabId: Int

    val url: String

    /**
     * 0 indicates the navigation happens in the tab content window; a positive value indicates navigation in a subframe. Frame IDs are unique within a tab.
     */
    val frameId: Int

    /**
     * The time when the document finished loading, in milliseconds since the epoch.
     */
    val timeStamp: Int
}

external class Details9 {
    /**
     * The ID of the tab in which the navigation occurs.
     */
    val tabId: Int

    val url: String

    /**
     * 0 indicates the navigation happens in the tab content window; a positive value indicates navigation in a subframe. Frame IDs are unique within a tab.
     */
    val frameId: Int

    /**
     * The time when the error occurred, in milliseconds since the epoch.
     */
    val timeStamp: Int
}

external class Details10 {
    /**
     * The ID of the tab in which the navigation is triggered.
     */
    val sourceTabId: Int

    /**
     * The ID of the process runs the renderer for the source tab.
     */
    val sourceProcessId: Int

    /**
     * The ID of the frame with sourceTabId in which the navigation is triggered. 0 indicates the main frame.
     */
    val sourceFrameId: Int

    /**
     * The URL to be opened in the new window.
     */
    val url: String

    /**
     * The ID of the tab in which the url is opened
     */
    val tabId: Int

    /**
     * The time when the browser was about to create a new view, in milliseconds since the epoch.
     */
    val timeStamp: Int
}

external class Details11 {
    /**
     * The ID of the tab in which the navigation occurs.
     */
    val tabId: Int

    val url: String

    /**
     * 0 indicates the navigation happens in the tab content window; a positive value indicates navigation in a subframe. Frame IDs are unique within a tab.
     */
    val frameId: Int

    /**
     * The time when the navigation was committed, in milliseconds since the epoch.
     */
    val timeStamp: Int
}

external class Details12 {
    /**
     * The ID of the tab that was replaced.
     */
    val replacedTabId: Int

    /**
     * The ID of the tab that replaced the old tab.
     */
    val tabId: Int

    /**
     * The time when the replacement happened, in milliseconds since the epoch.
     */
    val timeStamp: Int
}

external class Details13 {
    /**
     * The ID of the tab in which the navigation occurs.
     */
    val tabId: Int

    val url: String

    /**
     * 0 indicates the navigation happens in the tab content window; a positive value indicates navigation in a subframe. Frame IDs are unique within a tab.
     */
    val frameId: Int

    /**
     * The time when the navigation was committed, in milliseconds since the epoch.
     */
    val timeStamp: Int
}

external class WebNavigationNamespace {
    val onBeforeNavigate: Event<(details: Details5) -> Unit>

    val onCommitted: Event<(details: Details6) -> Unit>

    val onDOMContentLoaded: Event<(details: Details7) -> Unit>

    val onCompleted: Event<(details: Details8) -> Unit>

    val onErrorOccurred: Event<(details: Details9) -> Unit>

    val onCreatedNavigationTarget: Event<(details: Details10) -> Unit>

    val onReferenceFragmentUpdated: Event<(details: Details11) -> Unit>

    val onTabReplaced: Event<(details: Details12) -> Unit>

    val onHistoryStateUpdated: Event<(details: Details13) -> Unit>

    /**
     * Retrieves information about the given frame. A frame refers to an &lt;iframe&gt; or a &lt;frame&gt; of a web page and is identified by a tab ID and a frame ID.
     */
    fun getFrame(details: Details): Promise<Details2>

    /**
     * Retrieves information about all frames of a given tab.
     */
    fun getAllFrames(details: Details3): Promise<Array<Details4>?>
}
