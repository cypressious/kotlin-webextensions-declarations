package find

import kotlin.js.Promise

/**
 * Search parameters.
 */
class Params(
        /**
         * Tab to query. Defaults to the active tab.
         */
        var tabId: Int? = null,
        /**
         * Find only ranges with case sensitive match.
         */
        var caseSensitive: Boolean? = null,
        /**
         * Find only ranges that match entire word.
         */
        var entireWord: Boolean? = null,
        /**
         * Return rectangle data which describes visual position of search results.
         */
        var includeRectData: Boolean? = null,
        /**
         * Return range data which provides range data in a serializable form.
         */
        var includeRangeData: Boolean? = null
)

/**
 * highlightResults parameters
 */
class Params2(
        /**
         * Found range to be highlighted. Default highlights all ranges.
         */
        var rangeIndex: Int? = null,
        /**
         * Tab to highlight. Defaults to the active tab.
         */
        var tabId: Int? = null,
        /**
         * Don't scroll to highlighted item.
         */
        var noScroll: Boolean? = null
)

external class FindNamespace {
    /**
     * Search for text in document and store found ranges in array, in document order.
     */
    fun find(queryphrase: String, params: Params? = definedExternally): Promise<Any>

    /**
     * Highlight a range
     */
    fun highlightResults(params: Params2? = definedExternally): Promise<Any>

    /**
     * Remove all highlighting from previous searches.
     */
    fun removeHighlighting(tabId: Int? = definedExternally): Promise<Any>
}
