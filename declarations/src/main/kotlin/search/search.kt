package search

import kotlin.js.Promise

/**
 * An object encapsulating a search engine
 */
class SearchEngine(
    var name: String,
    var isDefault: Boolean,
    var alias: String? = null,
    var favIconUrl: String? = null
)

/**
 * @param query Terms to search for.
 * @param engine Search engine to use. Uses the default if not specified.
 * @param tabId The ID of the tab for the search results. If not specified, a new tab is created.
 */
class SearchProperties(
    var query: String,
    var engine: String? = null,
    var tabId: Int? = null
)

external class SearchNamespace {
    /**
     * Gets a list of search engines.
     */
    fun get(): Promise<Any>

    /**
     * Perform a search.
     */
    fun search(searchProperties: SearchProperties): Promise<Any>
}
