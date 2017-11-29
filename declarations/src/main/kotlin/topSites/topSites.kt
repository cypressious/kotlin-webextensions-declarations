package topSites

import kotlin.js.Promise

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

external class Options {
  /**
   * Which providers to get top sites from. Possible values are "places" and "activityStream".
   */
  val providers: Array<String>?
}

external class TopSitesNamespace {
  /**
   * Gets a list of top sites.
   */
  fun get(options: Options): Promise<Array<MostVisitedURL>>
}
