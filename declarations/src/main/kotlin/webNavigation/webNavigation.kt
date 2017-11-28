package webNavigation

import kotlin.js.Promise

class GetFrameDetails(
    /**
     * The ID of the tab in which the frame is.
     */
    val tabId: Int,
    /**
     * The ID of the process runs the renderer for this tab.
     */
    val processId: Int,
    /**
     * The ID of the frame in the given tab.
     */
    val frameId: Int
)

class GetFrameDetailsResult(
    /**
     * True if the last navigation in this frame was interrupted by an error, i.e. the onErrorOccurred event fired.
     */
    val errorOccurred: Boolean,
    /**
     * The URL currently associated with this frame, if the frame identified by the frameId existed at one point in the given tab. The fact that an URL is associated with a given frameId does not imply that the corresponding frame still exists.
     */
    val url: String,
    /**
     * The ID of the tab in which the frame is.
     */
    val tabId: Int,
    /**
     * The ID of the frame. 0 indicates that this is the main frame; a positive value indicates the ID of a subframe.
     */
    val frameId: Int,
    /**
     * ID of frame that wraps the frame. Set to -1 of no parent frame exists.
     */
    val parentFrameId: Int
)

class GetAllFramesDetails(/**
 * The ID of the tab.
 */
val tabId: Int)

class GetAllFramesDetailsResult(
    /**
     * True if the last navigation in this frame was interrupted by an error, i.e. the onErrorOccurred event fired.
     */
    val errorOccurred: Boolean,
    /**
     * The ID of the tab in which the frame is.
     */
    val tabId: Int,
    /**
     * The ID of the frame. 0 indicates that this is the main frame; a positive value indicates the ID of a subframe.
     */
    val frameId: Int,
    /**
     * ID of frame that wraps the frame. Set to -1 of no parent frame exists.
     */
    val parentFrameId: Int,
    /**
     * The URL currently associated with this frame.
     */
    val url: String
)

typealias TransitionType = String

typealias TransitionQualifier = String

external class EventUrlFilters {
  val url: Array<events.UrlFilter>
}

external class WebNavigation {
  /**
   * Retrieves information about the given frame. A frame refers to an &lt;iframe&gt; or a &lt;frame&gt; of a web page and is identified by a tab ID and a frame ID.
   */
  fun getFrame(details: GetFrameDetails): Promise<GetFrameDetailsResult>

  /**
   * Retrieves information about all frames of a given tab.
   */
  fun getAllFrames(details: GetAllFramesDetails): Promise<Array<GetAllFramesDetailsResult>>
}
