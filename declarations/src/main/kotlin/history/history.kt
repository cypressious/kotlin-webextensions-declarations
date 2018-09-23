package history

import extensionTypes.Date
import kotlin.js.Promise
import webextensions.Event

/**
 * The $(topic:transition-types)[transition type] for this visit from its referrer. */
typealias TransitionType = String

/**
 * An object encapsulating one result of a history query.
 * @param id The unique identifier for the item.
 * @param url The URL navigated to by a user.
 * @param title The title of the page when it was last loaded.
 * @param lastVisitTime When this page was last loaded, represented in milliseconds since the epoch.
 * @param visitCount The number of times the user has navigated to this page.
 * @param typedCount The number of times the user has navigated to this page by typing in the address.
 */
class HistoryItem(
    var id: String,
    var url: String? = null,
    var title: String? = null,
    var lastVisitTime: Float? = null,
    var visitCount: Int? = null,
    var typedCount: Int? = null
)

/**
 * An object encapsulating one visit to a URL.
 * @param id The unique identifier for the item.
 * @param visitId The unique identifier for this visit.
 * @param visitTime When this visit occurred, represented in milliseconds since the epoch.
 * @param referringVisitId The visit ID of the referrer.
 * @param transition The $(topic:transition-types)[transition type] for this visit from its referrer.
 */
class VisitItem(
    var id: String,
    var visitId: String,
    var visitTime: Float? = null,
    var referringVisitId: String,
    var transition: TransitionType
)

/**
 * @param text A free-text query to the history service.  Leave empty to retrieve all pages.
 * @param startTime Limit results to those visited after this date. If not specified, this defaults to 24 hours in the past.
 * @param endTime Limit results to those visited before this date.
 * @param maxResults The maximum number of results to retrieve.  Defaults to 100.
 */
class Query(
    var text: String,
    var startTime: Date? = null,
    var endTime: Date? = null,
    var maxResults: Int? = null
)

/**
 * @param url The URL for which to retrieve visit information.  It must be in the format as returned from a call to history.search.
 */
class Details(
    var url: String
)

/**
 * @param url The URL to add. Must be a valid URL that can be added to history.
 * @param title The title of the page.
 * @param transition The $(topic:transition-types)[transition type] for this visit from its referrer.
 * @param visitTime The date when this visit occurred.
 */
class Details2(
    var url: String,
    var title: String? = null,
    var transition: TransitionType? = null,
    var visitTime: Date? = null
)

/**
 * @param url The URL to remove.
 */
class Details3(
    var url: String
)

/**
 * @param startTime Items added to history after this date.
 * @param endTime Items added to history before this date.
 */
class Range(
    var startTime: Date,
    var endTime: Date
)

/**
 * @param allHistory True if all history was removed.  If true, then urls will be empty.
 */
class Removed(
    var allHistory: Boolean,
    var urls: Array<String>
)

/**
 * @param url The URL for which the title has changed
 * @param title The new title for the URL.
 */
class Changed(
    var url: String,
    var title: String
)

external class HistoryNamespace {
    /**
     * Fired when a URL is visited, providing the HistoryItem data for that URL.  This event fires before the page has loaded.
     *
     * @param result null */
    val onVisited: Event<(result: HistoryItem) -> Unit>

    /**
     * Fired when one or more URLs are removed from the history service.  When all visits have been removed the URL is purged from history.
     *
     * @param removed null */
    val onVisitRemoved: Event<(removed: Removed) -> Unit>

    /**
     * Fired when the title of a URL is changed in the browser history.
     *
     * @param changed null */
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
