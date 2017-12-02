package webNavigation

import events.UrlFilter
import kotlin.js.Promise
import webextensions.Event

/**
 * Cause of the navigation. The same transition types as defined in the history API are used. These are the same transition types as defined in the $(topic:transition_types)[history API] except with <code>"start_page"</code> in place of <code>"auto_toplevel"</code> (for backwards compatibility). */
typealias TransitionType = String

typealias TransitionQualifier = String

class EventUrlFilters(var url: Array<UrlFilter>)

/**
 * Information about the frame to retrieve information about.
 */
class Details(
        /**
         * The ID of the tab in which the frame is.
         */
        var tabId: Int,
        /**
         * The ID of the process runs the renderer for this tab.
         */
        var processId: Int? = null,
        /**
         * The ID of the frame in the given tab.
         */
        var frameId: Int
)

/**
 * Information about the requested frame, null if the specified frame ID and/or tab ID are invalid.
 */
class Details2(
        /**
         * True if the last navigation in this frame was interrupted by an error, i.e. the onErrorOccurred event fired.
         */
        var errorOccurred: Boolean? = null,
        /**
         * The URL currently associated with this frame, if the frame identified by the frameId existed at one point in the given tab. The fact that an URL is associated with a given frameId does not imply that the corresponding frame still exists.
         */
        var url: String,
        /**
         * The ID of the tab in which the frame is.
         */
        var tabId: Int,
        /**
         * The ID of the frame. 0 indicates that this is the main frame; a positive value indicates the ID of a subframe.
         */
        var frameId: Int,
        /**
         * ID of frame that wraps the frame. Set to -1 of no parent frame exists.
         */
        var parentFrameId: Int
)

/**
 * Information about the tab to retrieve all frames from.
 */
class Details3(/**
 * The ID of the tab.
 */
var tabId: Int)

class Details4(
        /**
         * True if the last navigation in this frame was interrupted by an error, i.e. the onErrorOccurred event fired.
         */
        var errorOccurred: Boolean? = null,
        /**
         * The ID of the tab in which the frame is.
         */
        var tabId: Int,
        /**
         * The ID of the frame. 0 indicates that this is the main frame; a positive value indicates the ID of a subframe.
         */
        var frameId: Int,
        /**
         * ID of frame that wraps the frame. Set to -1 of no parent frame exists.
         */
        var parentFrameId: Int,
        /**
         * The URL currently associated with this frame.
         */
        var url: String
)

class Details5(
        /**
         * The ID of the tab in which the navigation is about to occur.
         */
        var tabId: Int,
        var url: String,
        /**
         * 0 indicates the navigation happens in the tab content window; a positive value indicates navigation in a subframe. Frame IDs are unique for a given tab and process.
         */
        var frameId: Int,
        /**
         * ID of frame that wraps the frame. Set to -1 of no parent frame exists.
         */
        var parentFrameId: Int,
        /**
         * The time when the browser was about to start the navigation, in milliseconds since the epoch.
         */
        var timeStamp: Int
)

class Details6(
        /**
         * The ID of the tab in which the navigation occurs.
         */
        var tabId: Int,
        var url: String,
        /**
         * 0 indicates the navigation happens in the tab content window; a positive value indicates navigation in a subframe. Frame IDs are unique within a tab.
         */
        var frameId: Int,
        /**
         * The time when the navigation was committed, in milliseconds since the epoch.
         */
        var timeStamp: Int
)

class Details7(
        /**
         * The ID of the tab in which the navigation occurs.
         */
        var tabId: Int,
        var url: String,
        /**
         * 0 indicates the navigation happens in the tab content window; a positive value indicates navigation in a subframe. Frame IDs are unique within a tab.
         */
        var frameId: Int,
        /**
         * The time when the page's DOM was fully constructed, in milliseconds since the epoch.
         */
        var timeStamp: Int
)

class Details8(
        /**
         * The ID of the tab in which the navigation occurs.
         */
        var tabId: Int,
        var url: String,
        /**
         * 0 indicates the navigation happens in the tab content window; a positive value indicates navigation in a subframe. Frame IDs are unique within a tab.
         */
        var frameId: Int,
        /**
         * The time when the document finished loading, in milliseconds since the epoch.
         */
        var timeStamp: Int
)

class Details9(
        /**
         * The ID of the tab in which the navigation occurs.
         */
        var tabId: Int,
        var url: String,
        /**
         * 0 indicates the navigation happens in the tab content window; a positive value indicates navigation in a subframe. Frame IDs are unique within a tab.
         */
        var frameId: Int,
        /**
         * The time when the error occurred, in milliseconds since the epoch.
         */
        var timeStamp: Int
)

class Details10(
        /**
         * The ID of the tab in which the navigation is triggered.
         */
        var sourceTabId: Int,
        /**
         * The ID of the process runs the renderer for the source tab.
         */
        var sourceProcessId: Int,
        /**
         * The ID of the frame with sourceTabId in which the navigation is triggered. 0 indicates the main frame.
         */
        var sourceFrameId: Int,
        /**
         * The URL to be opened in the new window.
         */
        var url: String,
        /**
         * The ID of the tab in which the url is opened
         */
        var tabId: Int,
        /**
         * The time when the browser was about to create a new view, in milliseconds since the epoch.
         */
        var timeStamp: Int
)

class Details11(
        /**
         * The ID of the tab in which the navigation occurs.
         */
        var tabId: Int,
        var url: String,
        /**
         * 0 indicates the navigation happens in the tab content window; a positive value indicates navigation in a subframe. Frame IDs are unique within a tab.
         */
        var frameId: Int,
        /**
         * The time when the navigation was committed, in milliseconds since the epoch.
         */
        var timeStamp: Int
)

class Details12(
        /**
         * The ID of the tab that was replaced.
         */
        var replacedTabId: Int,
        /**
         * The ID of the tab that replaced the old tab.
         */
        var tabId: Int,
        /**
         * The time when the replacement happened, in milliseconds since the epoch.
         */
        var timeStamp: Int
)

class Details13(
        /**
         * The ID of the tab in which the navigation occurs.
         */
        var tabId: Int,
        var url: String,
        /**
         * 0 indicates the navigation happens in the tab content window; a positive value indicates navigation in a subframe. Frame IDs are unique within a tab.
         */
        var frameId: Int,
        /**
         * The time when the navigation was committed, in milliseconds since the epoch.
         */
        var timeStamp: Int
)

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
    fun getFrame(details: Details): Promise<Details2?>

    /**
     * Retrieves information about all frames of a given tab.
     */
    fun getAllFrames(details: Details3): Promise<Array<Details4>?>
}
