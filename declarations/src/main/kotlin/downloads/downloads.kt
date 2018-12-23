package downloads

import kotlin.js.Promise
import webextensions.Event

typealias FilenameConflictAction = String

typealias InterruptReason = String

/**
 * <dl><dt>file</dt><dd>The download's filename is suspicious.</dd><dt>url</dt><dd>The download's
        URL is known to be malicious.</dd><dt>content</dt><dd>The downloaded file is known to be
        malicious.</dd><dt>uncommon</dt><dd>The download's URL is not commonly downloaded and could
        be dangerous.</dd><dt>safe</dt><dd>The download presents no known danger to the user's
        computer.</dd></dl>These string constants will never change, however the set of DangerTypes
        may change. */
typealias DangerType = String

/**
 * <dl><dt>in_progress</dt><dd>The download is currently receiving data from the
        server.</dd><dt>interrupted</dt><dd>An error broke the connection with the file
        host.</dd><dt>complete</dt><dd>The download completed successfully.</dd></dl>These string
        constants will never change, however the set of States may change. */
typealias State = String

/**
 * @param id An identifier that is persistent across browser sessions.
 * @param url Absolute URL.
 * @param filename Absolute local path.
 * @param incognito False if this download is recorded in the history, true if it is not recorded.
 * @param danger Indication of whether this download is thought to be safe or known to be
        suspicious.
 * @param mime The file's MIME type.
 * @param startTime Number of milliseconds between the unix epoch and when this download began.
 * @param endTime Number of milliseconds between the unix epoch and when this download ended.
 * @param state Indicates whether the download is progressing, interrupted, or complete.
 * @param paused True if the download has stopped reading data from the host, but kept the
        connection open.
 * @param error Number indicating why a download was interrupted.
 * @param bytesReceived Number of bytes received so far from the host, without considering file
        compression.
 * @param totalBytes Number of bytes in the whole file, without considering file compression, or -1
        if unknown.
 * @param fileSize Number of bytes in the whole file post-decompression, or -1 if unknown.
 */
class DownloadItem(
    var id: Int,
    var url: String,
    var referrer: String? = null,
    var filename: String,
    var incognito: Boolean,
    var danger: DangerType,
    var mime: String,
    var startTime: String,
    var endTime: String? = null,
    var estimatedEndTime: String? = null,
    var state: State,
    var paused: Boolean,
    var canResume: Boolean,
    var error: InterruptReason? = null,
    var bytesReceived: Float,
    var totalBytes: Float,
    var fileSize: Float,
    var exists: Boolean,
    var byExtensionId: String? = null,
    var byExtensionName: String? = null
)

class StringDelta(var current: String? = null, var previous: String? = null)

class DoubleDelta(var current: Float? = null, var previous: Float? = null)

class BooleanDelta(var current: Boolean? = null, var previous: Boolean? = null)

/**
 * A time specified as a Date object, a number or string representing milliseconds since the epoch,
        or an ISO 8601 string */
typealias DownloadTime = Any

/**
 * Parameters that combine to specify a predicate that can be used to select a set of downloads. 
        Used for example in search() and erase()
 * @param query This array of search terms limits results to <a
        href='#type-DownloadItem'>DownloadItems</a> whose <code>filename</code> or <code>url</code>
        contain all of the search terms that do not begin with a dash '-' and none of the search
        terms that do begin with a dash.
 * @param startedBefore Limits results to downloads that started before the given ms since the
        epoch.
 * @param startedAfter Limits results to downloads that started after the given ms since the epoch.
 * @param endedBefore Limits results to downloads that ended before the given ms since the epoch.
 * @param endedAfter Limits results to downloads that ended after the given ms since the epoch.
 * @param totalBytesGreater Limits results to downloads whose totalBytes is greater than the given
        integer.
 * @param totalBytesLess Limits results to downloads whose totalBytes is less than the given
        integer.
 * @param filenameRegex Limits results to <a href='#type-DownloadItem'>DownloadItems</a> whose
        <code>filename</code> matches the given regular expression.
 * @param urlRegex Limits results to <a href='#type-DownloadItem'>DownloadItems</a> whose
        <code>url</code> matches the given regular expression.
 * @param limit Setting this integer limits the number of results. Otherwise, all matching <a
        href='#type-DownloadItem'>DownloadItems</a> will be returned.
 * @param orderBy Setting elements of this array to <a href='#type-DownloadItem'>DownloadItem</a>
        properties in order to sort the search results. For example, setting
        <code>orderBy='startTime'</code> sorts the <a href='#type-DownloadItem'>DownloadItems</a> by
        their start time in ascending order. To specify descending order, prefix
        <code>orderBy</code> with a hyphen: '-startTime'.
 * @param url Absolute URL.
 * @param filename Absolute local path.
 * @param danger Indication of whether this download is thought to be safe or known to be
        suspicious.
 * @param mime The file's MIME type.
 * @param state Indicates whether the download is progressing, interrupted, or complete.
 * @param paused True if the download has stopped reading data from the host, but kept the
        connection open.
 * @param error Why a download was interrupted.
 * @param bytesReceived Number of bytes received so far from the host, without considering file
        compression.
 * @param totalBytes Number of bytes in the whole file, without considering file compression, or -1
        if unknown.
 * @param fileSize Number of bytes in the whole file post-decompression, or -1 if unknown.
 */
class DownloadQuery(
    var query: Array<String>? = null,
    var startedBefore: DownloadTime? = null,
    var startedAfter: DownloadTime? = null,
    var endedBefore: DownloadTime? = null,
    var endedAfter: DownloadTime? = null,
    var totalBytesGreater: Float? = null,
    var totalBytesLess: Float? = null,
    var filenameRegex: String? = null,
    var urlRegex: String? = null,
    var limit: Int? = null,
    var orderBy: Array<String>? = null,
    var id: Int? = null,
    var url: String? = null,
    var filename: String? = null,
    var danger: DangerType? = null,
    var mime: String? = null,
    var startTime: String? = null,
    var endTime: String? = null,
    var state: State? = null,
    var paused: Boolean? = null,
    var error: InterruptReason? = null,
    var bytesReceived: Float? = null,
    var totalBytes: Float? = null,
    var fileSize: Float? = null,
    var exists: Boolean? = null
)

/**
 * @param name Name of the HTTP header.
 * @param value Value of the HTTP header.
 */
class Headers(
    var name: String,
    var value: String
)

/**
 * What to download and how.
 * @param url The URL to download.
 * @param filename A file path relative to the Downloads directory to contain the downloaded file.
 * @param incognito Whether to associate the download with a private browsing session.
 * @param saveAs Use a file-chooser to allow the user to select a filename. If the option is not
        specified, the file chooser will be shown only if the Firefox "Always ask you where to save
        files" option is enabled (i.e. the pref <code>browser.download.useDownloadDir</code> is set
        to <code>false</code>).
 * @param method The HTTP method to use if the URL uses the HTTP[S] protocol.
 * @param headers Extra HTTP headers to send with the request if the URL uses the HTTP[s] protocol.
        Each header is represented as a dictionary containing the keys <code>name</code> and either
        <code>value</code> or <code>binaryValue</code>, restricted to those allowed by
        XMLHttpRequest.
 * @param body Post body.
 */
class Options(
    var url: String,
    var filename: String? = null,
    var incognito: Boolean? = null,
    var conflictAction: FilenameConflictAction? = null,
    var saveAs: Boolean? = null,
    var method: String? = null,
    var headers: Array<Headers>? = null,
    var body: String? = null
)

/**
 * @param size The size of the icon.  The returned icon will be square with dimensions size * size
        pixels.  The default size for the icon is 32x32 pixels.
 */
class Options2(
    var size: Int? = null
)

/**
 * @param id The <code>id</code> of the <a href='#type-DownloadItem'>DownloadItem</a> that changed.
 * @param url Describes a change in a <a href='#type-DownloadItem'>DownloadItem</a>'s
        <code>url</code>.
 * @param filename Describes a change in a <a href='#type-DownloadItem'>DownloadItem</a>'s
        <code>filename</code>.
 * @param danger Describes a change in a <a href='#type-DownloadItem'>DownloadItem</a>'s
        <code>danger</code>.
 * @param mime Describes a change in a <a href='#type-DownloadItem'>DownloadItem</a>'s
        <code>mime</code>.
 * @param startTime Describes a change in a <a href='#type-DownloadItem'>DownloadItem</a>'s
        <code>startTime</code>.
 * @param endTime Describes a change in a <a href='#type-DownloadItem'>DownloadItem</a>'s
        <code>endTime</code>.
 * @param state Describes a change in a <a href='#type-DownloadItem'>DownloadItem</a>'s
        <code>state</code>.
 * @param paused Describes a change in a <a href='#type-DownloadItem'>DownloadItem</a>'s
        <code>paused</code>.
 * @param error Describes a change in a <a href='#type-DownloadItem'>DownloadItem</a>'s
        <code>error</code>.
 * @param totalBytes Describes a change in a <a href='#type-DownloadItem'>DownloadItem</a>'s
        <code>totalBytes</code>.
 * @param fileSize Describes a change in a <a href='#type-DownloadItem'>DownloadItem</a>'s
        <code>fileSize</code>.
 */
class DownloadDelta(
    var id: Int,
    var url: StringDelta? = null,
    var filename: StringDelta? = null,
    var danger: StringDelta? = null,
    var mime: StringDelta? = null,
    var startTime: StringDelta? = null,
    var endTime: StringDelta? = null,
    var state: StringDelta? = null,
    var canResume: BooleanDelta? = null,
    var paused: BooleanDelta? = null,
    var error: StringDelta? = null,
    var totalBytes: DoubleDelta? = null,
    var fileSize: DoubleDelta? = null,
    var exists: BooleanDelta? = null
)

external class DownloadsNamespace {
    /**
     * This event fires with the <a href='#type-DownloadItem'>DownloadItem</a> object when a
            download begins.
     *
     * @param downloadItem null */
    val onCreated: Event<(downloadItem: DownloadItem) -> Unit>

    /**
     * Fires with the <code>downloadId</code> when a download is erased from history.
     *
     * @param downloadId The <code>id</code> of the <a href='#type-DownloadItem'>DownloadItem</a>
            that was erased. */
    val onErased: Event<(downloadId: Int) -> Unit>

    /**
     * When any of a <a href='#type-DownloadItem'>DownloadItem</a>'s properties except
            <code>bytesReceived</code> changes, this event fires with the <code>downloadId</code>
            and an object containing the properties that changed.
     *
     * @param downloadDelta null */
    val onChanged: Event<(downloadDelta: DownloadDelta) -> Unit>

    /**
     * Download a URL. If the URL uses the HTTP[S] protocol, then the request will include all
            cookies currently set for its hostname. If both <code>filename</code> and
            <code>saveAs</code> are specified, then the Save As dialog will be displayed,
            pre-populated with the specified <code>filename</code>. If the download started
            successfully, <code>callback</code> will be called with the new <a
            href='#type-DownloadItem'>DownloadItem</a>'s <code>downloadId</code>. If there was an
            error starting the download, then <code>callback</code> will be called with
            <code>downloadId=undefined</code> and <a
            href='extension.html#property-lastError'>chrome.extension.lastError</a> will contain a
            descriptive string. The error strings are not guaranteed to remain backwards compatible
            between releases. You must not parse it.
     */
    fun download(options: Options): Promise<Int>

    /**
     * Find <a href='#type-DownloadItem'>DownloadItems</a>. Set <code>query</code> to the empty
            object to get all <a href='#type-DownloadItem'>DownloadItems</a>. To get a specific <a
            href='#type-DownloadItem'>DownloadItem</a>, set only the <code>id</code> field.
     */
    fun search(query: DownloadQuery): Promise<Array<DownloadItem>>

    /**
     * Pause the download. If the request was successful the download is in a paused state.
            Otherwise <a href='extension.html#property-lastError'>chrome.extension.lastError</a>
            contains an error message. The request will fail if the download is not active.
     */
    fun pause(downloadId: Int): Promise<Any>

    /**
     * Resume a paused download. If the request was successful the download is in progress and
            unpaused. Otherwise <a
            href='extension.html#property-lastError'>chrome.extension.lastError</a> contains an
            error message. The request will fail if the download is not active.
     */
    fun resume(downloadId: Int): Promise<Any>

    /**
     * Cancel a download. When <code>callback</code> is run, the download is cancelled, completed,
            interrupted or doesn't exist anymore.
     */
    fun cancel(downloadId: Int): Promise<Any>

    /**
     * Retrieve an icon for the specified download. For new downloads, file icons are available
            after the <a href='#event-onCreated'>onCreated</a> event has been received. The image
            returned by this function while a download is in progress may be different from the
            image returned after the download is complete. Icon retrieval is done by querying the
            underlying operating system or toolkit depending on the platform. The icon that is
            returned will therefore depend on a number of factors including state of the download,
            platform, registered file types and visual theme. If a file icon cannot be determined,
            <a href='extension.html#property-lastError'>chrome.extension.lastError</a> will contain
            an error message.
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
