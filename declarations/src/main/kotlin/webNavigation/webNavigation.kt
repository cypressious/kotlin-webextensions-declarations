package webNavigation

import events.UrlFilter
import kotlin.js.Promise
import webextensions.Event

/**
 * Cause of the navigation. The same transition types as defined in the history API are used. These
        are the same transition types as defined in the $(topic:transition_types)[history API]
        except with <code>"start_page"</code> in place of <code>"auto_toplevel"</code> (for
        backwards compatibility). */
typealias TransitionType = String

typealias TransitionQualifier = String

class EventUrlFilters(var url: Array<UrlFilter>)

/**
 * Information about the frame to retrieve information about.
 * @param tabId The ID of the tab in which the frame is.
 * @param processId The ID of the process runs the renderer for this tab.
 * @param frameId The ID of the frame in the given tab.
 */
class Details(
    var tabId: Int,
    var processId: Int? = null,
    var frameId: Int
)

/**
 * Information about the requested frame, null if the specified frame ID and/or tab ID are invalid.
 * @param errorOccurred True if the last navigation in this frame was interrupted by an error, i.e.
        the onErrorOccurred event fired.
 * @param url The URL currently associated with this frame, if the frame identified by the frameId
        existed at one point in the given tab. The fact that an URL is associated with a given
        frameId does not imply that the corresponding frame still exists.
 * @param tabId The ID of the tab in which the frame is.
 * @param frameId The ID of the frame. 0 indicates that this is the main frame; a positive value
        indicates the ID of a subframe.
 * @param parentFrameId ID of frame that wraps the frame. Set to -1 of no parent frame exists.
 */
class Details2(
    var errorOccurred: Boolean? = null,
    var url: String,
    var tabId: Int,
    var frameId: Int,
    var parentFrameId: Int
)

/**
 * Information about the tab to retrieve all frames from.
 * @param tabId The ID of the tab.
 */
class Details3(
    var tabId: Int
)

/**
 * @param errorOccurred True if the last navigation in this frame was interrupted by an error, i.e.
        the onErrorOccurred event fired.
 * @param tabId The ID of the tab in which the frame is.
 * @param frameId The ID of the frame. 0 indicates that this is the main frame; a positive value
        indicates the ID of a subframe.
 * @param parentFrameId ID of frame that wraps the frame. Set to -1 of no parent frame exists.
 * @param url The URL currently associated with this frame.
 */
class Details4(
    var errorOccurred: Boolean? = null,
    var tabId: Int,
    var frameId: Int,
    var parentFrameId: Int,
    var url: String
)

/**
 * @param tabId The ID of the tab in which the navigation is about to occur.
 * @param frameId 0 indicates the navigation happens in the tab content window; a positive value
        indicates navigation in a subframe. Frame IDs are unique for a given tab and process.
 * @param parentFrameId ID of frame that wraps the frame. Set to -1 of no parent frame exists.
 * @param timeStamp The time when the browser was about to start the navigation, in milliseconds
        since the epoch.
 */
class Details5(
    var tabId: Int,
    var url: String,
    var frameId: Int,
    var parentFrameId: Int,
    var timeStamp: Float
)

/**
 * @param tabId The ID of the tab in which the navigation occurs.
 * @param frameId 0 indicates the navigation happens in the tab content window; a positive value
        indicates navigation in a subframe. Frame IDs are unique within a tab.
 * @param timeStamp The time when the navigation was committed, in milliseconds since the epoch.
 */
class Details6(
    var tabId: Int,
    var url: String,
    var frameId: Int,
    var timeStamp: Float
)

/**
 * @param tabId The ID of the tab in which the navigation occurs.
 * @param frameId 0 indicates the navigation happens in the tab content window; a positive value
        indicates navigation in a subframe. Frame IDs are unique within a tab.
 * @param timeStamp The time when the page's DOM was fully constructed, in milliseconds since the
        epoch.
 */
class Details7(
    var tabId: Int,
    var url: String,
    var frameId: Int,
    var timeStamp: Float
)

/**
 * @param tabId The ID of the tab in which the navigation occurs.
 * @param frameId 0 indicates the navigation happens in the tab content window; a positive value
        indicates navigation in a subframe. Frame IDs are unique within a tab.
 * @param timeStamp The time when the document finished loading, in milliseconds since the epoch.
 */
class Details8(
    var tabId: Int,
    var url: String,
    var frameId: Int,
    var timeStamp: Float
)

/**
 * @param tabId The ID of the tab in which the navigation occurs.
 * @param frameId 0 indicates the navigation happens in the tab content window; a positive value
        indicates navigation in a subframe. Frame IDs are unique within a tab.
 * @param timeStamp The time when the error occurred, in milliseconds since the epoch.
 */
class Details9(
    var tabId: Int,
    var url: String,
    var frameId: Int,
    var timeStamp: Float
)

/**
 * @param sourceTabId The ID of the tab in which the navigation is triggered.
 * @param sourceProcessId The ID of the process runs the renderer for the source tab.
 * @param sourceFrameId The ID of the frame with sourceTabId in which the navigation is triggered. 0
        indicates the main frame.
 * @param url The URL to be opened in the new window.
 * @param tabId The ID of the tab in which the url is opened
 * @param timeStamp The time when the browser was about to create a new view, in milliseconds since
        the epoch.
 */
class Details10(
    var sourceTabId: Int,
    var sourceProcessId: Int,
    var sourceFrameId: Int,
    var url: String,
    var tabId: Int,
    var timeStamp: Float
)

/**
 * @param tabId The ID of the tab in which the navigation occurs.
 * @param frameId 0 indicates the navigation happens in the tab content window; a positive value
        indicates navigation in a subframe. Frame IDs are unique within a tab.
 * @param timeStamp The time when the navigation was committed, in milliseconds since the epoch.
 */
class Details11(
    var tabId: Int,
    var url: String,
    var frameId: Int,
    var timeStamp: Float
)

/**
 * @param replacedTabId The ID of the tab that was replaced.
 * @param tabId The ID of the tab that replaced the old tab.
 * @param timeStamp The time when the replacement happened, in milliseconds since the epoch.
 */
class Details12(
    var replacedTabId: Int,
    var tabId: Int,
    var timeStamp: Float
)

/**
 * @param tabId The ID of the tab in which the navigation occurs.
 * @param frameId 0 indicates the navigation happens in the tab content window; a positive value
        indicates navigation in a subframe. Frame IDs are unique within a tab.
 * @param timeStamp The time when the navigation was committed, in milliseconds since the epoch.
 */
class Details13(
    var tabId: Int,
    var url: String,
    var frameId: Int,
    var timeStamp: Float
)

external class WebNavigationNamespace {
    /**
     * Fired when a navigation is about to occur.
     *
     * @param details null */
    val onBeforeNavigate: Event<(details: Details5) -> Unit>

    /**
     * Fired when a navigation is committed. The document (and the resources it refers to, such as
            images and subframes) might still be downloading, but at least part of the document has
            been received from the server and the browser has decided to switch to the new document.
     *
     * @param details null */
    val onCommitted: Event<(details: Details6) -> Unit>

    /**
     * Fired when the page's DOM is fully constructed, but the referenced resources may not finish
            loading.
     *
     * @param details null */
    val onDOMContentLoaded: Event<(details: Details7) -> Unit>

    /**
     * Fired when a document, including the resources it refers to, is completely loaded and
            initialized.
     *
     * @param details null */
    val onCompleted: Event<(details: Details8) -> Unit>

    /**
     * Fired when an error occurs and the navigation is aborted. This can happen if either a network
            error occurred, or the user aborted the navigation.
     *
     * @param details null */
    val onErrorOccurred: Event<(details: Details9) -> Unit>

    /**
     * Fired when a new window, or a new tab in an existing window, is created to host a navigation.
     *
     * @param details null */
    val onCreatedNavigationTarget: Event<(details: Details10) -> Unit>

    /**
     * Fired when the reference fragment of a frame was updated. All future events for that frame
            will use the updated URL.
     *
     * @param details null */
    val onReferenceFragmentUpdated: Event<(details: Details11) -> Unit>

    /**
     * Fired when the contents of the tab is replaced by a different (usually previously
            pre-rendered) tab.
     *
     * @param details null */
    val onTabReplaced: Event<(details: Details12) -> Unit>

    /**
     * Fired when the frame's history was updated to a new URL. All future events for that frame
            will use the updated URL.
     *
     * @param details null */
    val onHistoryStateUpdated: Event<(details: Details13) -> Unit>

    /**
     * Retrieves information about the given frame. A frame refers to an &lt;iframe&gt; or a
            &lt;frame&gt; of a web page and is identified by a tab ID and a frame ID.
     */
    fun getFrame(details: Details): Promise<Details2?>

    /**
     * Retrieves information about all frames of a given tab.
     */
    fun getAllFrames(details: Details3): Promise<Array<Details4>?>
}
