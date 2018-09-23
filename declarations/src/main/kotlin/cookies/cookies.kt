package cookies

import kotlin.js.Promise
import webextensions.Event

/**
 * A cookie's 'SameSite' state (https://tools.ietf.org/html/draft-west-first-party-cookies). 'no_restriction' corresponds to a cookie set without a 'SameSite' attribute, 'lax' to 'SameSite=Lax', and 'strict' to 'SameSite=Strict'. */
typealias SameSiteStatus = String

/**
 * Represents information about an HTTP cookie.
 * @param name The name of the cookie.
 * @param value The value of the cookie.
 * @param domain The domain of the cookie (e.g. "www.google.com", "example.com").
 * @param hostOnly True if the cookie is a host-only cookie (i.e. a request's host must exactly match the domain of the cookie).
 * @param path The path of the cookie.
 * @param secure True if the cookie is marked as Secure (i.e. its scope is limited to secure channels, typically HTTPS).
 * @param httpOnly True if the cookie is marked as HttpOnly (i.e. the cookie is inaccessible to client-side scripts).
 * @param sameSite The cookie's same-site status (i.e. whether the cookie is sent with cross-site requests).
 * @param session True if the cookie is a session cookie, as opposed to a persistent cookie with an expiration date.
 * @param expirationDate The expiration date of the cookie as the number of seconds since the UNIX epoch. Not provided for session cookies.
 * @param storeId The ID of the cookie store containing this cookie, as provided in getAllCookieStores().
 * @param firstPartyDomain The first-party domain of the cookie.
 */
class Cookie(
    var name: String,
    var value: String,
    var domain: String,
    var hostOnly: Boolean,
    var path: String,
    var secure: Boolean,
    var httpOnly: Boolean,
    var sameSite: SameSiteStatus,
    var session: Boolean,
    var expirationDate: Float? = null,
    var storeId: String,
    var firstPartyDomain: String
)

/**
 * Represents a cookie store in the browser. An incognito mode window, for instance, uses a separate cookie store from a non-incognito window.
 * @param id The unique identifier for the cookie store.
 * @param tabIds Identifiers of all the browser tabs that share this cookie store.
 * @param incognito Indicates if this is an incognito cookie store
 */
class CookieStore(
    var id: String,
    var tabIds: Array<Int>,
    var incognito: Boolean
)

/**
 * The underlying reason behind the cookie's change. If a cookie was inserted, or removed via an explicit call to $(ref:cookies.remove), "cause" will be "explicit". If a cookie was automatically removed due to expiry, "cause" will be "expired". If a cookie was removed due to being overwritten with an already-expired expiration date, "cause" will be set to "expired_overwrite".  If a cookie was automatically removed due to garbage collection, "cause" will be "evicted".  If a cookie was automatically removed due to a "set" call that overwrote it, "cause" will be "overwrite". Plan your response accordingly. */
typealias OnChangedCause = String

/**
 * Details to identify the cookie being retrieved.
 * @param url The URL with which the cookie to retrieve is associated. This argument may be a full URL, in which case any data following the URL path (e.g. the query string) is simply ignored. If host permissions for this URL are not specified in the manifest file, the API call will fail.
 * @param name The name of the cookie to retrieve.
 * @param storeId The ID of the cookie store in which to look for the cookie. By default, the current execution context's cookie store will be used.
 * @param firstPartyDomain The first-party domain which the cookie to retrieve is associated. This attribute is required if First-Party Isolation is enabled.
 */
class Details(
    var url: String,
    var name: String,
    var storeId: String? = null,
    var firstPartyDomain: String? = null
)

/**
 * Information to filter the cookies being retrieved.
 * @param url Restricts the retrieved cookies to those that would match the given URL.
 * @param name Filters the cookies by name.
 * @param domain Restricts the retrieved cookies to those whose domains match or are subdomains of this one.
 * @param path Restricts the retrieved cookies to those whose path exactly matches this string.
 * @param secure Filters the cookies by their Secure property.
 * @param session Filters out session vs. persistent cookies.
 * @param storeId The cookie store to retrieve cookies from. If omitted, the current execution context's cookie store will be used.
 * @param firstPartyDomain Restricts the retrieved cookies to those whose first-party domains match this one. This attribute is required if First-Party Isolation is enabled. To not filter by a specific first-party domain, use `null` or `undefined`.
 */
class Details2(
    var url: String? = null,
    var name: String? = null,
    var domain: String? = null,
    var path: String? = null,
    var secure: Boolean? = null,
    var session: Boolean? = null,
    var storeId: String? = null,
    var firstPartyDomain: String
)

/**
 * Details about the cookie being set.
 * @param url The request-URI to associate with the setting of the cookie. This value can affect the default domain and path values of the created cookie. If host permissions for this URL are not specified in the manifest file, the API call will fail.
 * @param name The name of the cookie. Empty by default if omitted.
 * @param value The value of the cookie. Empty by default if omitted.
 * @param domain The domain of the cookie. If omitted, the cookie becomes a host-only cookie.
 * @param path The path of the cookie. Defaults to the path portion of the url parameter.
 * @param secure Whether the cookie should be marked as Secure. Defaults to false.
 * @param httpOnly Whether the cookie should be marked as HttpOnly. Defaults to false.
 * @param sameSite The cookie's same-site status.
 * @param expirationDate The expiration date of the cookie as the number of seconds since the UNIX epoch. If omitted, the cookie becomes a session cookie.
 * @param storeId The ID of the cookie store in which to set the cookie. By default, the cookie is set in the current execution context's cookie store.
 * @param firstPartyDomain The first-party domain of the cookie. This attribute is required if First-Party Isolation is enabled.
 */
class Details3(
    var url: String,
    var name: String? = null,
    var value: String? = null,
    var domain: String? = null,
    var path: String? = null,
    var secure: Boolean? = null,
    var httpOnly: Boolean? = null,
    var sameSite: SameSiteStatus? = null,
    var expirationDate: Float? = null,
    var storeId: String? = null,
    var firstPartyDomain: String? = null
)

/**
 * Information to identify the cookie to remove.
 * @param url The URL associated with the cookie. If host permissions for this URL are not specified in the manifest file, the API call will fail.
 * @param name The name of the cookie to remove.
 * @param storeId The ID of the cookie store to look in for the cookie. If unspecified, the cookie is looked for by default in the current execution context's cookie store.
 * @param firstPartyDomain The first-party domain associated with the cookie. This attribute is required if First-Party Isolation is enabled.
 */
class Details4(
    var url: String,
    var name: String,
    var storeId: String? = null,
    var firstPartyDomain: String? = null
)

/**
 * Contains details about the cookie that's been removed.  If removal failed for any reason, this will be "null", and $(ref:runtime.lastError) will be set.
 * @param url The URL associated with the cookie that's been removed.
 * @param name The name of the cookie that's been removed.
 * @param storeId The ID of the cookie store from which the cookie was removed.
 * @param firstPartyDomain The first-party domain associated with the cookie that's been removed.
 */
class Details5(
    var url: String,
    var name: String,
    var storeId: String,
    var firstPartyDomain: String
)

/**
 * @param removed True if a cookie was removed.
 * @param cookie Information about the cookie that was set or removed.
 * @param cause The underlying reason behind the cookie's change.
 */
class ChangeInfo(
    var removed: Boolean,
    var cookie: Cookie,
    var cause: OnChangedCause
)

external class CookiesNamespace {
    /**
     * Fired when a cookie is set or removed. As a special case, note that updating a cookie's properties is implemented as a two step process: the cookie to be updated is first removed entirely, generating a notification with "cause" of "overwrite" .  Afterwards, a new cookie is written with the updated values, generating a second notification with "cause" "explicit".
     *
     * @param changeInfo null */
    val onChanged: Event<(changeInfo: ChangeInfo) -> Unit>

    /**
     * Retrieves information about a single cookie. If more than one cookie of the same name exists for the given URL, the one with the longest path will be returned. For cookies with the same path length, the cookie with the earliest creation time will be returned.
     */
    fun get(details: Details): Promise<Cookie?>

    /**
     * Retrieves all cookies from a single cookie store that match the given information.  The cookies returned will be sorted, with those with the longest path first.  If multiple cookies have the same path length, those with the earliest creation time will be first.
     */
    fun getAll(details: Details2): Promise<Array<Cookie>>

    /**
     * Sets a cookie with the given cookie data; may overwrite equivalent cookies if they exist.
     */
    fun set(details: Details3): Promise<Cookie?>

    /**
     * Deletes a cookie by name.
     */
    fun remove(details: Details4): Promise<Details5?>

    /**
     * Lists all existing cookie stores.
     */
    fun getAllCookieStores(): Promise<Array<CookieStore>>
}
