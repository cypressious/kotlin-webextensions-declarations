package downloads

import webextensions.Event
import kotlin.js.Promise

typealias FilenameConflictAction = String

typealias InterruptReason = String

/**
 * <dl><dt>file</dt><dd>The download's filename is suspicious.</dd><dt>url</dt><dd>The download's URL is known to be malicious.</dd><dt>content</dt><dd>The downloaded file is known to be malicious.</dd><dt>uncommon</dt><dd>The download's URL is not commonly downloaded and could be dangerous.</dd><dt>safe</dt><dd>The download presents no known danger to the user's computer.</dd></dl>These string constants will never change, however the set of DangerTypes may change. */
typealias DangerType = String

/**
 * <dl><dt>in_progress</dt><dd>The download is currently receiving data from the server.</dd><dt>interrupted</dt><dd>An error broke the connection with the file host.</dd><dt>complete</dt><dd>The download completed successfully.</dd></dl>These string constants will never change, however the set of States may change. */
typealias State = String

class DownloadItem(
        /**
         * An identifier that is persistent across browser sessions.
         */
        var id: Int,
        /**
         * Absolute URL.
         */
        var url: String,
        var referrer: String? = null,
        /**
         * Absolute local path.
         */
        var filename: String,
        /**
         * False if this download is recorded in the history, true if it is not recorded.
         */
        var incognito: Boolean,
        /**
         * Indication of whether this download is thought to be safe or known to be suspicious.
         */
        var danger: DangerType,
        /**
         * The file's MIME type.
         */
        var mime: String,
        /**
         * Number of milliseconds between the unix epoch and when this download began.
         */
        var startTime: String,
        /**
         * Number of milliseconds between the unix epoch and when this download ended.
         */
        var endTime: String? = null,
        var estimatedEndTime: String? = null,
        /**
         * Indicates whether the download is progressing, interrupted, or complete.
         */
        var state: State,
        /**
         * True if the download has stopped reading data from the host, but kept the connection open.
         */
        var paused: Boolean,
        var canResume: Boolean,
        /**
         * Number indicating why a download was interrupted.
         */
        var error: InterruptReason? = null,
        /**
         * Number of bytes received so far from the host, without considering file compression.
         */
        var bytesReceived: Int,
        /**
         * Number of bytes in the whole file, without considering file compression, or -1 if unknown.
         */
        var totalBytes: Int,
        /**
         * Number of bytes in the whole file post-decompression, or -1 if unknown.
         */
        var fileSize: Int,
        var exists: Boolean,
        var byExtensionId: String? = null,
        var byExtensionName: String? = null
)

class StringDelta(var current: String? = null, var previous: String? = null)

class DoubleDelta(var current: Int? = null, var previous: Int? = null)

class BooleanDelta(var current: Boolean? = null, var previous: Boolean? = null)

/**
 * A time specified as a Date object, a number or string representing milliseconds since the epoch, or an ISO 8601 string */
typealias DownloadTime = Any

/**
 * Parameters that combine to specify a predicate that can be used to select a set of downloads.  Used for example in search() and erase()
 */
class DownloadQuery(
        /**
         * This array of search terms limits results to <a href='#type-DownloadItem'>DownloadItems</a> whose <code>filename</code> or <code>url</code> contain all of the search terms that do not begin with a dash '-' and none of the search terms that do begin with a dash.
         */
        var query: Array<String>? = null,
        /**
         * Limits results to downloads that started before the given ms since the epoch.
         */
        var startedBefore: DownloadTime? = null,
        /**
         * Limits results to downloads that started after the given ms since the epoch.
         */
        var startedAfter: DownloadTime? = null,
        /**
         * Limits results to downloads that ended before the given ms since the epoch.
         */
        var endedBefore: DownloadTime? = null,
        /**
         * Limits results to downloads that ended after the given ms since the epoch.
         */
        var endedAfter: DownloadTime? = null,
        /**
         * Limits results to downloads whose totalBytes is greater than the given integer.
         */
        var totalBytesGreater: Int? = null,
        /**
         * Limits results to downloads whose totalBytes is less than the given integer.
         */
        var totalBytesLess: Int? = null,
        /**
         * Limits results to <a href='#type-DownloadItem'>DownloadItems</a> whose <code>filename</code> matches the given regular expression.
         */
        var filenameRegex: String? = null,
        /**
         * Limits results to <a href='#type-DownloadItem'>DownloadItems</a> whose <code>url</code> matches the given regular expression.
         */
        var urlRegex: String? = null,
        /**
         * Setting this integer limits the number of results. Otherwise, all matching <a href='#type-DownloadItem'>DownloadItems</a> will be returned.
         */
        var limit: Int? = null,
        /**
         * Setting elements of this array to <a href='#type-DownloadItem'>DownloadItem</a> properties in order to sort the search results. For example, setting <code>orderBy='startTime'</code> sorts the <a href='#type-DownloadItem'>DownloadItems</a> by their start time in ascending order. To specify descending order, prefix <code>orderBy</code> with a hyphen: '-startTime'.
         */
        var orderBy: Array<String>? = null,
        var id: Int? = null,
        /**
         * Absolute URL.
         */
        var url: String? = null,
        /**
         * Absolute local path.
         */
        var filename: String? = null,
        /**
         * Indication of whether this download is thought to be safe or known to be suspicious.
         */
        var danger: DangerType? = null,
        /**
         * The file's MIME type.
         */
        var mime: String? = null,
        var startTime: String? = null,
        var endTime: String? = null,
        /**
         * Indicates whether the download is progressing, interrupted, or complete.
         */
        var state: State? = null,
        /**
         * True if the download has stopped reading data from the host, but kept the connection open.
         */
        var paused: Boolean? = null,
        /**
         * Why a download was interrupted.
         */
        var error: InterruptReason? = null,
        /**
         * Number of bytes received so far from the host, without considering file compression.
         */
        var bytesReceived: Int? = null,
        /**
         * Number of bytes in the whole file, without considering file compression, or -1 if unknown.
         */
        var totalBytes: Int? = null,
        /**
         * Number of bytes in the whole file post-decompression, or -1 if unknown.
         */
        var fileSize: Int? = null,
        var exists: Boolean? = null
)

class Headers(/**
 * Name of the HTTP header.
 */
var name: String, /**
 * Value of the HTTP header.
 */
var value: String)

/**
 * What to download and how.
 */
class Options(
        /**
         * The URL to download.
         */
        var url: String,
        /**
         * A file path relative to the Downloads directory to contain the downloaded file.
         */
        var filename: String? = null,
        /**
         * Whether to associate the download with a private browsing session.
         */
        var incognito: Boolean? = null,
        var conflictAction: FilenameConflictAction? = null,
        /**
         * Use a file-chooser to allow the user to select a filename. If the option is not specified, the file chooser will be shown only if the Firefox "Always ask you where to save files" option is enabled (i.e. the pref <code>browser.download.useDownloadDir</code> is set to <code>false</code>).
         */
        var saveAs: Boolean? = null,
        /**
         * The HTTP method to use if the URL uses the HTTP[S] protocol.
         */
        var method: String? = null,
        /**
         * Extra HTTP headers to send with the request if the URL uses the HTTP[s] protocol. Each header is represented as a dictionary containing the keys <code>name</code> and either <code>value</code> or <code>binaryValue</code>, restricted to those allowed by XMLHttpRequest.
         */
        var headers: Array<Headers>? = null,
        /**
         * Post body.
         */
        var body: String? = null
)

class Options2(/**
 * The size of the icon.  The returned icon will be square with dimensions size * size pixels.  The default size for the icon is 32x32 pixels.
 */
var size: Int? = null)

class DownloadDelta(
        /**
         * The <code>id</code> of the <a href='#type-DownloadItem'>DownloadItem</a> that changed.
         */
        var id: Int,
        /**
         * Describes a change in a <a href='#type-DownloadItem'>DownloadItem</a>'s <code>url</code>.
         */
        var url: StringDelta? = null,
        /**
         * Describes a change in a <a href='#type-DownloadItem'>DownloadItem</a>'s <code>filename</code>.
         */
        var filename: StringDelta? = null,
        /**
         * Describes a change in a <a href='#type-DownloadItem'>DownloadItem</a>'s <code>danger</code>.
         */
        var danger: StringDelta? = null,
        /**
         * Describes a change in a <a href='#type-DownloadItem'>DownloadItem</a>'s <code>mime</code>.
         */
        var mime: StringDelta? = null,
        /**
         * Describes a change in a <a href='#type-DownloadItem'>DownloadItem</a>'s <code>startTime</code>.
         */
        var startTime: StringDelta? = null,
        /**
         * Describes a change in a <a href='#type-DownloadItem'>DownloadItem</a>'s <code>endTime</code>.
         */
        var endTime: StringDelta? = null,
        /**
         * Describes a change in a <a href='#type-DownloadItem'>DownloadItem</a>'s <code>state</code>.
         */
        var state: StringDelta? = null,
        var canResume: BooleanDelta? = null,
        /**
         * Describes a change in a <a href='#type-DownloadItem'>DownloadItem</a>'s <code>paused</code>.
         */
        var paused: BooleanDelta? = null,
        /**
         * Describes a change in a <a href='#type-DownloadItem'>DownloadItem</a>'s <code>error</code>.
         */
        var error: StringDelta? = null,
        /**
         * Describes a change in a <a href='#type-DownloadItem'>DownloadItem</a>'s <code>totalBytes</code>.
         */
        var totalBytes: DoubleDelta? = null,
        /**
         * Describes a change in a <a href='#type-DownloadItem'>DownloadItem</a>'s <code>fileSize</code>.
         */
        var fileSize: DoubleDelta? = null,
        var exists: BooleanDelta? = null
)

external class DownloadsNamespace {
    val onCreated: Event<(downloadItem: DownloadItem) -> Unit>

    val onErased: Event<(downloadId: Int) -> Unit>

    val onChanged: Event<(downloadDelta: DownloadDelta) -> Unit>

    /**
     * Download a URL. If the URL uses the HTTP[S] protocol, then the request will include all cookies currently set for its hostname. If both <code>filename</code> and <code>saveAs</code> are specified, then the Save As dialog will be displayed, pre-populated with the specified <code>filename</code>. If the download started successfully, <code>callback</code> will be called with the new <a href='#type-DownloadItem'>DownloadItem</a>'s <code>downloadId</code>. If there was an error starting the download, then <code>callback</code> will be called with <code>downloadId=undefined</code> and <a href='extension.html#property-lastError'>chrome.extension.lastError</a> will contain a descriptive string. The error strings are not guaranteed to remain backwards compatible between releases. You must not parse it.
     */
    fun download(options: Options): Promise<Int>

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
    fun getFileIcon(downloadId: Int, options: Options2? = definedExternally): Promise<String?>

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
}
