package topSites

import kotlin.js.Promise

/**
 * An object encapsulating a most visited URL, such as the URLs on the new tab page.
 */
external class MostVisitedURL {
    /**
     * The most visited URL.
     */
    var url: String

    /**
     * The title of the page.
     */
    var title: String?
}

class Options(/**
 * Which providers to get top sites from. Possible values are "places" and "activityStream".
 */
var providers: Array<String>?)

external class TopSitesNamespace {
    /**
     * Gets a list of top sites.
     */
    fun get(options: Options): Promise<Array<MostVisitedURL>>
}
