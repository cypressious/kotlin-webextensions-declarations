package topSites

import kotlin.js.Promise

class GetOptions(/**
 * Which providers to get top sites from. Possible values are "places" and "activityStream".
 */
val providers: Array<String>?)

/**
 * An object encapsulating a most visited URL, such as the URLs on the new tab page.
 */
external class MostVisitedURL {
  /**
   * The most visited URL.
   */
  val url: String

  /**
   * The title of the page.
   */
  val title: String?
}

external class TopSites {
  /**
   * Gets a list of top sites.
   */
  fun get(options: GetOptions?): Promise<Array<MostVisitedURL>>
}
