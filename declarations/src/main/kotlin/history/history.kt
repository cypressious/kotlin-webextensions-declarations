package history

import extensionTypes.Date
import kotlin.js.Promise

typealias TransitionType = String

/**
 * An object encapsulating one result of a history query.
 */
external class HistoryItem {
  /**
   * The unique identifier for the item.
   */
  val id: String

  /**
   * The URL navigated to by a user.
   */
  val url: String?

  /**
   * The title of the page when it was last loaded.
   */
  val title: String?

  /**
   * When this page was last loaded, represented in milliseconds since the epoch.
   */
  val lastVisitTime: Any?

  /**
   * The number of times the user has navigated to this page.
   */
  val visitCount: Int?

  /**
   * The number of times the user has navigated to this page by typing in the address.
   */
  val typedCount: Int?
}

/**
 * An object encapsulating one visit to a URL.
 */
external class VisitItem {
  /**
   * The unique identifier for the item.
   */
  val id: String

  /**
   * The unique identifier for this visit.
   */
  val visitId: String

  /**
   * When this visit occurred, represented in milliseconds since the epoch.
   */
  val visitTime: Any?

  /**
   * The visit ID of the referrer.
   */
  val referringVisitId: String

  /**
   * The $(topic:transition-types)[transition type] for this visit from its referrer.
   */
  val transition: TransitionType
}

class Query(
    /**
     * A free-text query to the history service.  Leave empty to retrieve all pages.
     */
    val text: String,
    /**
     * Limit results to those visited after this date. If not specified, this defaults to 24 hours in the past.
     */
    val startTime: Date,
    /**
     * Limit results to those visited before this date.
     */
    val endTime: Date,
    /**
     * The maximum number of results to retrieve.  Defaults to 100.
     */
    val maxResults: Int?
)

class Details(/**
 * The URL for which to retrieve visit information.  It must be in the format as returned from a call to history.search.
 */
val url: String)

class Details2(
    /**
     * The URL to add. Must be a valid URL that can be added to history.
     */
    val url: String,
    /**
     * The title of the page.
     */
    val title: String?,
    /**
     * The $(topic:transition-types)[transition type] for this visit from its referrer.
     */
    val transition: TransitionType,
    /**
     * The date when this visit occurred.
     */
    val visitTime: Date
)

class Details3(/**
 * The URL to remove.
 */
val url: String)

class Range(/**
 * Items added to history after this date.
 */
val startTime: Date, /**
 * Items added to history before this date.
 */
val endTime: Date)

external class HistoryNamespace {
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
