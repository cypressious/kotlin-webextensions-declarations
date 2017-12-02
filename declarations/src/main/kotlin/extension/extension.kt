package extension

import kotlin.Suppress
import kotlin.js.Promise

/**
 * The type of extension view. */
typealias ViewType = String

/**
 * Set for the lifetime of a callback if an ansychronous extension api has resulted in an error. If no error has occured lastError will be <var>undefined</var>.
 */
@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class LastError(/**
 * Description of the error that has taken place.
 */
var message: String) {
    inline operator fun get(key: String): dynamic = asDynamic()[key]
    inline operator fun set(key: String, value: dynamic) {
        asDynamic()[key] = value
    }
}

class FetchProperties(
        /**
         * The type of view to get. If omitted, returns all views (including background pages and tabs). Valid values: 'tab', 'popup', 'sidebar'.
         */
        var type: ViewType? = null,
        /**
         * The window to restrict the search to. If omitted, returns all views.
         */
        var windowId: Int? = null,
        /**
         * Find a view according to a tab id. If this field is omitted, returns all views.
         */
        var tabId: Int? = null
)

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class GetViewsResult() {
    inline operator fun get(key: String): dynamic = asDynamic()[key]
    inline operator fun set(key: String, value: dynamic) {
        asDynamic()[key] = value
    }
}

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class GetBackgroundPageResult() {
    inline operator fun get(key: String): dynamic = asDynamic()[key]
    inline operator fun set(key: String, value: dynamic) {
        asDynamic()[key] = value
    }
}

external class ExtensionNamespace {
    /**
     * Converts a relative path within an extension install directory to a fully-qualified URL.
     */
    fun getURL(path: String): String

    /**
     * Returns an array of the JavaScript 'window' objects for each of the pages running inside the current extension.
     */
    fun getViews(fetchProperties: FetchProperties? = definedExternally): Array<GetViewsResult>

    /**
     * Returns the JavaScript 'window' object for the background page running inside the current extension. Returns null if the extension has no background page.
     */
    fun getBackgroundPage(): GetBackgroundPageResult?

    /**
     * Retrieves the state of the extension's access to Incognito-mode (as determined by the user-controlled 'Allowed in Incognito' checkbox.
     */
    fun isAllowedIncognitoAccess(): Promise<Boolean>

    /**
     * Retrieves the state of the extension's access to the 'file://' scheme (as determined by the user-controlled 'Allow access to File URLs' checkbox.
     */
    fun isAllowedFileSchemeAccess(): Promise<Boolean>
}
