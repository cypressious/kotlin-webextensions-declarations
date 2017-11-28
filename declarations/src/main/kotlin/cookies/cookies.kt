package cookies

import kotlin.js.Promise

class GetDetails(
    /**
     * The URL with which the cookie to retrieve is associated. This argument may be a full URL, in which case any data following the URL path (e.g. the query string) is simply ignored. If host permissions for this URL are not specified in the manifest file, the API call will fail.
     */
    val url: String,
    /**
     * The name of the cookie to retrieve.
     */
    val name: String,
    /**
     * The ID of the cookie store in which to look for the cookie. By default, the current execution context's cookie store will be used.
     */
    val storeId: String
)

class GetAllDetails(
    /**
     * Restricts the retrieved cookies to those that would match the given URL.
     */
    val url: String,
    /**
     * Filters the cookies by name.
     */
    val name: String,
    /**
     * Restricts the retrieved cookies to those whose domains match or are subdomains of this one.
     */
    val domain: String,
    /**
     * Restricts the retrieved cookies to those whose path exactly matches this string.
     */
    val path: String,
    /**
     * Filters the cookies by their Secure property.
     */
    val secure: Boolean,
    /**
     * Filters out session vs. persistent cookies.
     */
    val session: Boolean,
    /**
     * The cookie store to retrieve cookies from. If omitted, the current execution context's cookie store will be used.
     */
    val storeId: String
)

class SetDetails(
    /**
     * The request-URI to associate with the setting of the cookie. This value can affect the default domain and path values of the created cookie. If host permissions for this URL are not specified in the manifest file, the API call will fail.
     */
    val url: String,
    /**
     * The name of the cookie. Empty by default if omitted.
     */
    val name: String,
    /**
     * The value of the cookie. Empty by default if omitted.
     */
    val value: String,
    /**
     * The domain of the cookie. If omitted, the cookie becomes a host-only cookie.
     */
    val domain: String,
    /**
     * The path of the cookie. Defaults to the path portion of the url parameter.
     */
    val path: String,
    /**
     * Whether the cookie should be marked as Secure. Defaults to false.
     */
    val secure: Boolean,
    /**
     * Whether the cookie should be marked as HttpOnly. Defaults to false.
     */
    val httpOnly: Boolean,
    /**
     * The expiration date of the cookie as the number of seconds since the UNIX epoch. If omitted, the cookie becomes a session cookie.
     */
    val expirationDate: Any,
    /**
     * The ID of the cookie store in which to set the cookie. By default, the cookie is set in the current execution context's cookie store.
     */
    val storeId: String
)

class RemoveDetails(
    /**
     * The URL associated with the cookie. If host permissions for this URL are not specified in the manifest file, the API call will fail.
     */
    val url: String,
    /**
     * The name of the cookie to remove.
     */
    val name: String,
    /**
     * The ID of the cookie store to look in for the cookie. If unspecified, the cookie is looked for by default in the current execution context's cookie store.
     */
    val storeId: String
)

class RemoveDetailsResult(
    /**
     * The URL associated with the cookie that's been removed.
     */
    val url: String,
    /**
     * The name of the cookie that's been removed.
     */
    val name: String,
    /**
     * The ID of the cookie store from which the cookie was removed.
     */
    val storeId: String
)

/**
 * Represents information about an HTTP cookie.
 */
external class Cookie {
  /**
   * The name of the cookie.
   */
  val name: String

  /**
   * The value of the cookie.
   */
  val value: String

  /**
   * The domain of the cookie (e.g. "www.google.com", "example.com").
   */
  val domain: String

  /**
   * True if the cookie is a host-only cookie (i.e. a request's host must exactly match the domain of the cookie).
   */
  val hostOnly: Boolean

  /**
   * The path of the cookie.
   */
  val path: String

  /**
   * True if the cookie is marked as Secure (i.e. its scope is limited to secure channels, typically HTTPS).
   */
  val secure: Boolean

  /**
   * True if the cookie is marked as HttpOnly (i.e. the cookie is inaccessible to client-side scripts).
   */
  val httpOnly: Boolean

  /**
   * True if the cookie is a session cookie, as opposed to a persistent cookie with an expiration date.
   */
  val session: Boolean

  /**
   * The expiration date of the cookie as the number of seconds since the UNIX epoch. Not provided for session cookies.
   */
  val expirationDate: Any

  /**
   * The ID of the cookie store containing this cookie, as provided in getAllCookieStores().
   */
  val storeId: String
}

/**
 * Represents a cookie store in the browser. An incognito mode window, for instance, uses a separate cookie store from a non-incognito window.
 */
external class CookieStore {
  /**
   * The unique identifier for the cookie store.
   */
  val id: String

  /**
   * Identifiers of all the browser tabs that share this cookie store.
   */
  val tabIds: Array<Int>

  /**
   * Indicates if this is an incognito cookie store
   */
  val incognito: Boolean
}

typealias OnChangedCause = String

external class Cookies {
  /**
   * Retrieves information about a single cookie. If more than one cookie of the same name exists for the given URL, the one with the longest path will be returned. For cookies with the same path length, the cookie with the earliest creation time will be returned.
   */
  fun get(details: GetDetails): Promise<Cookie>

  /**
   * Retrieves all cookies from a single cookie store that match the given information.  The cookies returned will be sorted, with those with the longest path first.  If multiple cookies have the same path length, those with the earliest creation time will be first.
   */
  fun getAll(details: GetAllDetails): Promise<Array<Cookie>>

  /**
   * Sets a cookie with the given cookie data; may overwrite equivalent cookies if they exist.
   */
  fun set(details: SetDetails): Promise<Cookie>

  /**
   * Deletes a cookie by name.
   */
  fun remove(details: RemoveDetails): Promise<RemoveDetailsResult>

  /**
   * Lists all existing cookie stores.
   */
  fun getAllCookieStores(): Promise<Array<CookieStore>>
}
