package find

import kotlin.js.Promise

/**
 * Search parameters.
 * @param tabId Tab to query. Defaults to the active tab.
 * @param caseSensitive Find only ranges with case sensitive match.
 * @param entireWord Find only ranges that match entire word.
 * @param includeRectData Return rectangle data which describes visual position of search results.
 * @param includeRangeData Return range data which provides range data in a serializable form.
 */
class Params(
    var tabId: Int? = null,
    var caseSensitive: Boolean? = null,
    var entireWord: Boolean? = null,
    var includeRectData: Boolean? = null,
    var includeRangeData: Boolean? = null
)

/**
 * highlightResults parameters
 * @param rangeIndex Found range to be highlighted. Default highlights all ranges.
 * @param tabId Tab to highlight. Defaults to the active tab.
 * @param noScroll Don't scroll to highlighted item.
 */
class Params2(
    var rangeIndex: Int? = null,
    var tabId: Int? = null,
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
