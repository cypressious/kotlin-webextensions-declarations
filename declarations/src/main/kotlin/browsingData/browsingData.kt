package browsingData

import extensionTypes.Date
import kotlin.js.Promise

class Result(
    val options: RemovalOptions,
    /**
     * All of the types will be present in the result, with values of <code>true</code> if they are both selected to be removed and permitted to be removed, otherwise <code>false</code>.
     */
    val dataToRemove: DataTypeSet,
    /**
     * All of the types will be present in the result, with values of <code>true</code> if they are permitted to be removed (e.g., by enterprise policy) and <code>false</code> if not.
     */
    val dataRemovalPermitted: DataTypeSet
)

/**
 * Options that determine exactly what data will be removed.
 */
external class RemovalOptions {
  /**
   * Remove data accumulated on or after this date, represented in milliseconds since the epoch (accessible via the <code>getTime</code> method of the JavaScript <code>Date</code> object). If absent, defaults to 0 (which would remove all browsing data).
   */
  val since: Date

  /**
   * Only remove data associated with these hostnames (only applies to cookies and localStorage).
   */
  val hostnames: Array<String>

  /**
   * An object whose properties specify which origin types ought to be cleared. If this object isn't specified, it defaults to clearing only "unprotected" origins. Please ensure that you <em>really</em> want to remove application data before adding 'protectedWeb' or 'extensions'.
   */
  val originTypes: OriginTypes
}

/**
 * A set of data types. Missing data types are interpreted as <code>false</code>.
 */
external class DataTypeSet {
  /**
   * The browser's cache. Note: when removing data, this clears the <em>entire</em> cache: it is not limited to the range you specify.
   */
  val cache: Boolean

  /**
   * The browser's cookies.
   */
  val cookies: Boolean

  /**
   * The browser's download list.
   */
  val downloads: Boolean

  /**
   * The browser's stored form data.
   */
  val formData: Boolean

  /**
   * The browser's history.
   */
  val history: Boolean

  /**
   * Websites' IndexedDB data.
   */
  val indexedDB: Boolean

  /**
   * Websites' local storage data.
   */
  val localStorage: Boolean

  /**
   * Server-bound certificates.
   */
  val serverBoundCertificates: Boolean

  /**
   * Stored passwords.
   */
  val passwords: Boolean

  /**
   * Plugins' data.
   */
  val pluginData: Boolean

  /**
   * Service Workers.
   */
  val serviceWorkers: Boolean
}

external class BrowsingData {
  /**
   * Reports which types of data are currently selected in the 'Clear browsing data' settings UI.  Note: some of the data types included in this API are not available in the settings UI, and some UI settings control more than one data type listed here.
   */
  fun settings(): Promise<Result> {
  }

  /**
   * Clears various types of browsing data stored in a user's profile.
   */
  fun remove(options: RemovalOptions, dataToRemove: DataTypeSet): Promise<Any> {
  }

  /**
   * Clears websites' appcache data.
   */
  fun removeAppcache(options: RemovalOptions): Promise<Any> {
  }

  /**
   * Clears the browser's cache.
   */
  fun removeCache(options: RemovalOptions): Promise<Any> {
  }

  /**
   * Clears the browser's cookies and server-bound certificates modified within a particular timeframe.
   */
  fun removeCookies(options: RemovalOptions): Promise<Any> {
  }

  /**
   * Clears the browser's list of downloaded files (<em>not</em> the downloaded files themselves).
   */
  fun removeDownloads(options: RemovalOptions): Promise<Any> {
  }

  /**
   * Clears websites' file system data.
   */
  fun removeFileSystems(options: RemovalOptions): Promise<Any> {
  }

  /**
   * Clears the browser's stored form data (autofill).
   */
  fun removeFormData(options: RemovalOptions): Promise<Any> {
  }

  /**
   * Clears the browser's history.
   */
  fun removeHistory(options: RemovalOptions): Promise<Any> {
  }

  /**
   * Clears websites' IndexedDB data.
   */
  fun removeIndexedDB(options: RemovalOptions): Promise<Any> {
  }

  /**
   * Clears websites' local storage data.
   */
  fun removeLocalStorage(options: RemovalOptions): Promise<Any> {
  }

  /**
   * Clears plugins' data.
   */
  fun removePluginData(options: RemovalOptions): Promise<Any> {
  }

  /**
   * Clears the browser's stored passwords.
   */
  fun removePasswords(options: RemovalOptions): Promise<Any> {
  }

  /**
   * Clears websites' WebSQL data.
   */
  fun removeWebSQL(options: RemovalOptions): Promise<Any> {
  }
}
