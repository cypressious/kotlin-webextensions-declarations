package browsingData

import extensionTypes.Date
import kotlin.js.Promise

/**
 * Options that determine exactly what data will be removed.
 * @param since Remove data accumulated on or after this date, represented in milliseconds since the
        epoch (accessible via the <code>getTime</code> method of the JavaScript <code>Date</code>
        object). If absent, defaults to 0 (which would remove all browsing data).
 * @param hostnames Only remove data associated with these hostnames (only applies to cookies and
        localStorage).
 * @param originTypes An object whose properties specify which origin types ought to be cleared. If
        this object isn't specified, it defaults to clearing only "unprotected" origins. Please
        ensure that you <em>really</em> want to remove application data before adding 'protectedWeb'
        or 'extensions'.
 */
class RemovalOptions(
    var since: Date? = null,
    var hostnames: Array<String>? = null,
    var originTypes: OriginTypes? = null
)

/**
 * A set of data types. Missing data types are interpreted as <code>false</code>.
 * @param cache The browser's cache. Note: when removing data, this clears the <em>entire</em>
        cache: it is not limited to the range you specify.
 * @param cookies The browser's cookies.
 * @param downloads The browser's download list.
 * @param formData The browser's stored form data.
 * @param history The browser's history.
 * @param indexedDB Websites' IndexedDB data.
 * @param localStorage Websites' local storage data.
 * @param serverBoundCertificates Server-bound certificates.
 * @param passwords Stored passwords.
 * @param pluginData Plugins' data.
 * @param serviceWorkers Service Workers.
 */
class DataTypeSet(
    var cache: Boolean? = null,
    var cookies: Boolean? = null,
    var downloads: Boolean? = null,
    var formData: Boolean? = null,
    var history: Boolean? = null,
    var indexedDB: Boolean? = null,
    var localStorage: Boolean? = null,
    var serverBoundCertificates: Boolean? = null,
    var passwords: Boolean? = null,
    var pluginData: Boolean? = null,
    var serviceWorkers: Boolean? = null
)

/**
 * An object whose properties specify which origin types ought to be cleared. If this object isn't
        specified, it defaults to clearing only "unprotected" origins. Please ensure that you
        <em>really</em> want to remove application data before adding 'protectedWeb' or
        'extensions'.
 * @param unprotectedWeb Normal websites.
 * @param protectedWeb Websites that have been installed as hosted applications (be careful!).
 * @param extension Extensions and packaged applications a user has installed (be _really_
        careful!).
 */
class OriginTypes(
    var unprotectedWeb: Boolean? = null,
    var protectedWeb: Boolean? = null,
    var extension: Boolean? = null
)

/**
 * @param dataToRemove All of the types will be present in the result, with values of
        <code>true</code> if they are both selected to be removed and permitted to be removed,
        otherwise <code>false</code>.
 * @param dataRemovalPermitted All of the types will be present in the result, with values of
        <code>true</code> if they are permitted to be removed (e.g., by enterprise policy) and
        <code>false</code> if not.
 */
class Result(
    var options: RemovalOptions,
    var dataToRemove: DataTypeSet,
    var dataRemovalPermitted: DataTypeSet
)

external class BrowsingDataNamespace {
    /**
     * Reports which types of data are currently selected in the 'Clear browsing data' settings UI. 
            Note: some of the data types included in this API are not available in the settings UI,
            and some UI settings control more than one data type listed here.
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
     * Clears the browser's cookies and server-bound certificates modified within a particular
            timeframe.
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
