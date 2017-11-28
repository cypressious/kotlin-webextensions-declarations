package downloads

import kotlin.js.Promise

class Headers(/**
 * Name of the HTTP header.
 */
val name: String, /**
 * Value of the HTTP header.
 */
val value: String)

class DownloadOptions(
    /**
     * The URL to download.
     */
    val url: String,
    /**
     * A file path relative to the Downloads directory to contain the downloaded file.
     */
    val filename: String,
    /**
     * Whether to associate the download with a private browsing session.
     */
    val incognito: Boolean,
    val conflictAction: FilenameConflictAction,
    /**
     * Use a file-chooser to allow the user to select a filename. If the option is not specified, the file chooser will be shown only if the Firefox "Always ask you where to save files" option is enabled (i.e. the pref <code>browser.download.useDownloadDir</code> is set to <code>false</code>).
     */
    val saveAs: Boolean,
    /**
     * The HTTP method to use if the URL uses the HTTP[S] protocol.
     */
    val method: String,
    /**
     * Extra HTTP headers to send with the request if the URL uses the HTTP[s] protocol. Each header is represented as a dictionary containing the keys <code>name</code> and either <code>value</code> or <code>binaryValue</code>, restricted to those allowed by XMLHttpRequest.
     */
    val headers: Array<Headers>,
    /**
     * Post body.
     */
    val body: String
)

class GetFileIconOptions(/**
 * The size of the icon.  The returned icon will be square with dimensions size * size pixels.  The default size for the icon is 32x32 pixels.
 */
val size: Int)

typealias FilenameConflictAction = String

typealias InterruptReason = String

typealias DangerType = String

typealias State = String

external class DownloadItem {
  /**
   * An identifier that is persistent across browser sessions.
   */
  val id: Int

  /**
   * Absolute URL.
   */
  val url: String

  val referrer: String

  /**
   * Absolute local path.
   */
  val filename: String

  /**
   * False if this download is recorded in the history, true if it is not recorded.
   */
  val incognito: Boolean

  /**
   * Indication of whether this download is thought to be safe or known to be suspicious.
   */
  val danger: DangerType

  /**
   * The file's MIME type.
   */
  val mime: String

  /**
   * Number of milliseconds between the unix epoch and when this download began.
   */
  val startTime: String

  /**
   * Number of milliseconds between the unix epoch and when this download ended.
   */
  val endTime: String

  val estimatedEndTime: String

  /**
   * Indicates whether the download is progressing, interrupted, or complete.
   */
  val state: State

  /**
   * True if the download has stopped reading data from the host, but kept the connection open.
   */
  val paused: Boolean

  val canResume: Boolean

  /**
   * Number indicating why a download was interrupted.
   */
  val error: InterruptReason

  /**
   * Number of bytes received so far from the host, without considering file compression.
   */
  val bytesReceived: Any

  /**
   * Number of bytes in the whole file, without considering file compression, or -1 if unknown.
   */
  val totalBytes: Any

  /**
   * Number of bytes in the whole file post-decompression, or -1 if unknown.
   */
  val fileSize: Any

  val exists: Boolean

  val byExtensionId: String

  val byExtensionName: String
}

external class StringDelta {
  val current: String

  val previous: String
}

external class DoubleDelta {
  val current: Any

  val previous: Any
}

external class BooleanDelta {
  val current: Boolean

  val previous: Boolean
}

/**
 * A time specified as a Date object, a number or string representing milliseconds since the epoch, or an ISO 8601 string
 */
external class DownloadTime

/**
 * Parameters that combine to specify a predicate that can be used to select a set of downloads.  Used for example in search() and erase()
 */
external class DownloadQuery {
  /**
   * This array of search terms limits results to <a href='#type-DownloadItem'>DownloadItems</a> whose <code>filename</code> or <code>url</code> contain all of the search terms that do not begin with a dash '-' and none of the search terms that do begin with a dash.
   */
  val query: Array<String>

  /**
   * Limits results to downloads that started before the given ms since the epoch.
   */
  val startedBefore: DownloadTime

  /**
   * Limits results to downloads that started after the given ms since the epoch.
   */
  val startedAfter: DownloadTime

  /**
   * Limits results to downloads that ended before the given ms since the epoch.
   */
  val endedBefore: DownloadTime

  /**
   * Limits results to downloads that ended after the given ms since the epoch.
   */
  val endedAfter: DownloadTime

  /**
   * Limits results to downloads whose totalBytes is greater than the given integer.
   */
  val totalBytesGreater: Any

  /**
   * Limits results to downloads whose totalBytes is less than the given integer.
   */
  val totalBytesLess: Any

  /**
   * Limits results to <a href='#type-DownloadItem'>DownloadItems</a> whose <code>filename</code> matches the given regular expression.
   */
  val filenameRegex: String

  /**
   * Limits results to <a href='#type-DownloadItem'>DownloadItems</a> whose <code>url</code> matches the given regular expression.
   */
  val urlRegex: String

  /**
   * Setting this integer limits the number of results. Otherwise, all matching <a href='#type-DownloadItem'>DownloadItems</a> will be returned.
   */
  val limit: Int

  /**
   * Setting elements of this array to <a href='#type-DownloadItem'>DownloadItem</a> properties in order to sort the search results. For example, setting <code>orderBy='startTime'</code> sorts the <a href='#type-DownloadItem'>DownloadItems</a> by their start time in ascending order. To specify descending order, prefix <code>orderBy</code> with a hyphen: '-startTime'.
   */
  val orderBy: Array<String>

  val id: Int

  /**
   * Absolute URL.
   */
  val url: String

  /**
   * Absolute local path.
   */
  val filename: String

  /**
   * Indication of whether this download is thought to be safe or known to be suspicious.
   */
  val danger: DangerType

  /**
   * The file's MIME type.
   */
  val mime: String

  val startTime: String

  val endTime: String

  /**
   * Indicates whether the download is progressing, interrupted, or complete.
   */
  val state: State

  /**
   * True if the download has stopped reading data from the host, but kept the connection open.
   */
  val paused: Boolean

  /**
   * Why a download was interrupted.
   */
  val error: InterruptReason

  /**
   * Number of bytes received so far from the host, without considering file compression.
   */
  val bytesReceived: Any

  /**
   * Number of bytes in the whole file, without considering file compression, or -1 if unknown.
   */
  val totalBytes: Any

  /**
   * Number of bytes in the whole file post-decompression, or -1 if unknown.
   */
  val fileSize: Any

  val exists: Boolean
}

external class Downloads {
  /**
   * Download a URL. If the URL uses the HTTP[S] protocol, then the request will include all cookies currently set for its hostname. If both <code>filename</code> and <code>saveAs</code> are specified, then the Save As dialog will be displayed, pre-populated with the specified <code>filename</code>. If the download started successfully, <code>callback</code> will be called with the new <a href='#type-DownloadItem'>DownloadItem</a>'s <code>downloadId</code>. If there was an error starting the download, then <code>callback</code> will be called with <code>downloadId=undefined</code> and <a href='extension.html#property-lastError'>chrome.extension.lastError</a> will contain a descriptive string. The error strings are not guaranteed to remain backwards compatible between releases. You must not parse it.
   */
  fun download(options: DownloadOptions): Promise<Int>

  /**
   * Find <a href='#type-DownloadItem'>DownloadItems</a>. Set <code>query</code> to the empty object to get all <a href='#type-DownloadItem'>DownloadItems</a>. To get a specific <a href='#type-DownloadItem'>DownloadItem</a>, set only the <code>id</code> field.
   */
  fun search(query: DownloadQuery): Promise<Array<DownloadItem>>

  /**
   * Pause the download. If the request was successful the download is in a paused state. Otherwise <a href='extension.html#property-lastError'>chrome.extension.lastError</a> contains an error message. The request will fail if the download is not active.
   */
  fun pause(downloadId: Int): Promise<Any>

  /**
   * Resume a paused download. If the request was successful the download is in progress and unpaused. Otherwise <a href='extension.html#property-lastError'>chrome.extension.lastError</a> contains an error message. The request will fail if the download is not active.
   */
  fun resume(downloadId: Int): Promise<Any>

  /**
   * Cancel a download. When <code>callback</code> is run, the download is cancelled, completed, interrupted or doesn't exist anymore.
   */
  fun cancel(downloadId: Int): Promise<Any>

  /**
   * Retrieve an icon for the specified download. For new downloads, file icons are available after the <a href='#event-onCreated'>onCreated</a> event has been received. The image returned by this function while a download is in progress may be different from the image returned after the download is complete. Icon retrieval is done by querying the underlying operating system or toolkit depending on the platform. The icon that is returned will therefore depend on a number of factors including state of the download, platform, registered file types and visual theme. If a file icon cannot be determined, <a href='extension.html#property-lastError'>chrome.extension.lastError</a> will contain an error message.
   */
  fun getFileIcon(downloadId: Int, options: GetFileIconOptions): Promise<String>

  /**
   * Open the downloaded file.
   */
  fun open(downloadId: Int): Promise<Any>

  /**
   * Show the downloaded file in its folder in a file manager.
   */
  fun show(downloadId: Int): Promise<Boolean>

  fun showDefaultFolder()

  /**
   * Erase matching <a href='#type-DownloadItem'>DownloadItems</a> from history
   */
  fun erase(query: DownloadQuery): Promise<Array<Int>>

  fun removeFile(downloadId: Int): Promise<Any>

  /**
   * Prompt the user to either accept or cancel a dangerous download. <code>acceptDanger()</code> does not automatically accept dangerous downloads.
   */
  fun acceptDanger(downloadId: Int, callback: Any)

  /**
   * Initiate dragging the file to another application.
   */
  fun drag(downloadId: Int)

  fun setShelfEnabled(enabled: Boolean)
}
