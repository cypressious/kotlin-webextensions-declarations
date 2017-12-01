package webNavigation

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

external class WebNavigationNamespace {
    /**
     * Retrieves information about the given frame. A frame refers to an &lt;iframe&gt; or a &lt;frame&gt; of a web page and is identified by a tab ID and a frame ID.
     */
    fun getFrame(details: Details): Promise<Details2>

    /**
     * Retrieves information about all frames of a given tab.
     */
    fun getAllFrames(details: Details3): Promise<Array<Details4>?>
}
