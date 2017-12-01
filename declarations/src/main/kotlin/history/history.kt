package history

import browser.Event
import extensionTypes.Date
import kotlin.js.Promise

/**
 * The $(topic:transition-types)[transition type] for this visit from its referrer. */
typealias TransitionType = String

/**
 * An object encapsulating one result of a history query.
 */
external class HistoryItem {
    /**
     * The unique identifier for the item.
     */
    var id: String

    /**
     * The URL navigated to by a user.
     */
    var url: String?

    /**
     * The title of the page when it was last loaded.
     */
    var title: String?

    /**
     * When this page was last loaded, represented in milliseconds since the epoch.
     */
    var lastVisitTime: Int?

    /**
     * The number of times the user has navigated to this page.
     */
    var visitCount: Int?

    /**
     * The number of times the user has navigated to this page by typing in the address.
     */
    var typedCount: Int?
}

/**
 * An object encapsulating one visit to a URL.
 */
external class VisitItem {
    /**
     * The unique identifier for the item.
     */
    var id: String

    /**
     * The unique identifier for this visit.
     */
    var visitId: String

    /**
     * When this visit occurred, represented in milliseconds since the epoch.
     */
    var visitTime: Int?

    /**
     * The visit ID of the referrer.
     */
    var referringVisitId: String

    /**
     * The $(topic:transition-types)[transition type] for this visit from its referrer.
     */
    var transition: TransitionType
}

class Query(
        /**
         * A free-text query to the history service.  Leave empty to retrieve all pages.
         */
        var text: String,
        /**
         * Limit results to those visited after this date. If not specified, this defaults to 24 hours in the past.
         */
        var startTime: Date,
        /**
         * Limit results to those visited before this date.
         */
        var endTime: Date,
        /**
         * The maximum number of results to retrieve.  Defaults to 100.
         */
        var maxResults: Int?
)

class Details(/**
 * The URL for which to retrieve visit information.  It must be in the format as returned from a call to history.search.
 */
var url: String)

class Details2(
        /**
         * The URL to add. Must be a valid URL that can be added to history.
         */
        var url: String,
        /**
         * The title of the page.
         */
        var title: String?,
        /**
         * The $(topic:transition-types)[transition type] for this visit from its referrer.
         */
        var transition: TransitionType,
        /**
         * The date when this visit occurred.
         */
        var visitTime: Date
)

class Details3(/**
 * The URL to remove.
 */
var url: String)

class Range(/**
 * Items added to history after this date.
 */
var startTime: Date, /**
 * Items added to history before this date.
 */
var endTime: Date)

external class Removed {
    /**
     * True if all history was removed.  If true, then urls will be empty.
     */
    var allHistory: Boolean

    var urls: Array<String>
}

external class Changed {
    /**
     * The URL for which the title has changed
     */
    var url: String

    /**
     * The new title for the URL.
     */
    var title: String
}

external class HistoryNamespace {
    val onVisited: Event<(result: HistoryItem) -> Unit>

    val onVisitRemoved: Event<(removed: Removed) -> Unit>

    val onTitleChanged: Event<(changed: Changed) -> Unit>

    /**
     * Searches the history for the last visit time of each page matching the query.
     */
    fun search(query: Query): Promise<Array<HistoryItem>>

    /**
     * Retrieves information about visits to a URL.
     */
    fun getVisits(details: Details): Promise<Array<VisitItem>>

    /**
     * Adds a URL to the history with a default visitTime of the current time and a default $(topic:transition-types)[transition type] of "link".
     */
    fun addUrl(details: Details2): Promise<Any>

    /**
     * Removes all occurrences of the given URL from the history.
     */
    fun deleteUrl(details: Details3): Promise<Any>

    /**
     * Removes all items within the specified date range from the history.  Pages will not be removed from the history unless all visits fall within the range.
     */
    fun deleteRange(range: Range): Promise<Any>

    /**
     * Deletes all items from the history.
     */
    fun deleteAll(): Promise<Any>
}
