package browsingData

import extensionTypes.Date
import kotlin.js.Promise

/**
 * Options that determine exactly what data will be removed.
 */
external class RemovalOptions {
    /**
     * Remove data accumulated on or after this date, represented in milliseconds since the epoch (accessible via the <code>getTime</code> method of the JavaScript <code>Date</code> object). If absent, defaults to 0 (which would remove all browsing data).
     */
    var since: Date?

    /**
     * Only remove data associated with these hostnames (only applies to cookies and localStorage).
     */
    var hostnames: Array<String>

    /**
     * An object whose properties specify which origin types ought to be cleared. If this object isn't specified, it defaults to clearing only "unprotected" origins. Please ensure that you <em>really</em> want to remove application data before adding 'protectedWeb' or 'extensions'.
     */
    var originTypes: OriginTypes?
}

/**
 * A set of data types. Missing data types are interpreted as <code>false</code>.
 */
external class DataTypeSet {
    /**
     * The browser's cache. Note: when removing data, this clears the <em>entire</em> cache: it is not limited to the range you specify.
     */
    var cache: Boolean?

    /**
     * The browser's cookies.
     */
    var cookies: Boolean?

    /**
     * The browser's download list.
     */
    var downloads: Boolean?

    /**
     * The browser's stored form data.
     */
    var formData: Boolean?

    /**
     * The browser's history.
     */
    var history: Boolean?

    /**
     * Websites' IndexedDB data.
     */
    var indexedDB: Boolean?

    /**
     * Websites' local storage data.
     */
    var localStorage: Boolean?

    /**
     * Server-bound certificates.
     */
    var serverBoundCertificates: Boolean?

    /**
     * Stored passwords.
     */
    var passwords: Boolean?

    /**
     * Plugins' data.
     */
    var pluginData: Boolean?

    /**
     * Service Workers.
     */
    var serviceWorkers: Boolean?
}

/**
 * An object whose properties specify which origin types ought to be cleared. If this object isn't specified, it defaults to clearing only "unprotected" origins. Please ensure that you <em>really</em> want to remove application data before adding 'protectedWeb' or 'extensions'.
 */
external class OriginTypes {
    /**
     * Normal websites.
     */
    var unprotectedWeb: Boolean?

    /**
     * Websites that have been installed as hosted applications (be careful!).
     */
    var protectedWeb: Boolean?

    /**
     * Extensions and packaged applications a user has installed (be _really_ careful!).
     */
    var extension: Boolean?
}

external class Result {
    var options: RemovalOptions

    /**
     * All of the types will be present in the result, with values of <code>true</code> if they are both selected to be removed and permitted to be removed, otherwise <code>false</code>.
     */
    var dataToRemove: DataTypeSet

    /**
     * All of the types will be present in the result, with values of <code>true</code> if they are permitted to be removed (e.g., by enterprise policy) and <code>false</code> if not.
     */
    var dataRemovalPermitted: DataTypeSet
}

external class BrowsingDataNamespace {
    /**
     * Reports which types of data are currently selected in the 'Clear browsing data' settings UI.  Note: some of the data types included in this API are not available in the settings UI, and some UI settings control more than one data type listed here.
     */
    fun settings(): Promise<Result>

    /**
     * Clears various types of browsing data stored in a user's profile.
     */
    fun remove(options: RemovalOptions, dataToRemove: DataTypeSet): Promise<Any>

    /**
     * Clears the browser's cache.
     */
    fun removeCache(options: RemovalOptions): Promise<Any>

    /**
     * Clears the browser's cookies and server-bound certificates modified within a particular timeframe.
     */
    fun removeCookies(options: RemovalOptions): Promise<Any>

    /**
     * Clears the browser's list of downloaded files (<em>not</em> the downloaded files themselves).
     */
    fun removeDownloads(options: RemovalOptions): Promise<Any>

    /**
     * Clears the browser's stored form data (autofill).
     */
    fun removeFormData(options: RemovalOptions): Promise<Any>

    /**
     * Clears the browser's history.
     */
    fun removeHistory(options: RemovalOptions): Promise<Any>

    /**
     * Clears websites' local storage data.
     */
    fun removeLocalStorage(options: RemovalOptions): Promise<Any>

    /**
     * Clears plugins' data.
     */
    fun removePluginData(options: RemovalOptions): Promise<Any>

    /**
     * Clears the browser's stored passwords.
     */
    fun removePasswords(options: RemovalOptions): Promise<Any>
}
