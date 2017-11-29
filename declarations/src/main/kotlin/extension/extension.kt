package extension

import kotlin.js.Promise

typealias ViewType = String

/**
 * Set for the lifetime of a callback if an ansychronous extension api has resulted in an error. If no error has occured lastError will be <var>undefined</var>.
 */
external class LastError {
  /**
   * Description of the error that has taken place.
   */
  val message: String
}

external class FetchProperties {
  /**
   * The type of view to get. If omitted, returns all views (including background pages and tabs). Valid values: 'tab', 'popup', 'sidebar'.
   */
  val type: ViewType

  /**
   * The window to restrict the search to. If omitted, returns all views.
   */
  val windowId: Int?

  /**
   * Find a view according to a tab id. If this field is omitted, returns all views.
   */
  val tabId: Int?
}

external class ExtensionNamespace {
  /**
   * Converts a relative path within an extension install directory to a fully-qualified URL.
   */
  fun getURL(path: String)

  /**
   * Returns an array of the JavaScript 'window' objects for each of the pages running inside the current extension.
   */
  fun getViews(fetchProperties: FetchProperties)

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
