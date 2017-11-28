package extension

import kotlin.js.Promise

class GetViewsFetchProperties(
    /**
     * The type of view to get. If omitted, returns all views (including background pages and tabs). Valid values: 'tab', 'popup', 'sidebar'.
     */
    val type: ViewType,
    /**
     * The window to restrict the search to. If omitted, returns all views.
     */
    val windowId: Int,
    /**
     * Find a view according to a tab id. If this field is omitted, returns all views.
     */
    val tabId: Int
)

typealias ViewType = String

external class Extension {
  /**
   * Converts a relative path within an extension install directory to a fully-qualified URL.
   */
  fun getURL(path: String)

  /**
   * Returns an array of the JavaScript 'window' objects for each of the pages running inside the current extension.
   */
  fun getViews(fetchProperties: GetViewsFetchProperties)

  /**
   * Returns the JavaScript 'window' object for the background page running inside the current extension. Returns null if the extension has no background page.
   */
  fun getBackgroundPage()

  /**
   * Retrieves the state of the extension's access to Incognito-mode (as determined by the user-controlled 'Allowed in Incognito' checkbox.
   */
  fun isAllowedIncognitoAccess(): Promise<Boolean>

  /**
   * Retrieves the state of the extension's access to the 'file://' scheme (as determined by the user-controlled 'Allow access to File URLs' checkbox.
   */
  fun isAllowedFileSchemeAccess(): Promise<Boolean>
}
