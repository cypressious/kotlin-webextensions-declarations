package topSites

import kotlin.js.Promise

/**
 * An object encapsulating a most visited URL, such as the URLs on the new tab page.
 * @param url The most visited URL.
 * @param title The title of the page.
 * @param favicon Data URL for the favicon, if available.
 */
class MostVisitedURL(
    var url: String,
    var title: String? = null,
    var favicon: String? = null
)

/**
 * @param limit The number of top sites to return, defaults to the value used by Firefox
 * @param onePerDomain Limit the result to a single top site link per domain
 * @param includeBlocked Include sites that the user has blocked from appearing on the Firefox new tab.
 * @param includeFavicon Include sites favicon if available.
 */
class Options(
    var providers: Array<String>? = null,
    var limit: Int? = null,
    var onePerDomain: Boolean? = null,
    var includeBlocked: Boolean? = null,
    var includeFavicon: Boolean? = null
)

external class TopSitesNamespace {
    /**
     * Gets a list of top sites.
     */
    fun get(options: Options? = definedExternally): Promise<Array<MostVisitedURL>>
}
