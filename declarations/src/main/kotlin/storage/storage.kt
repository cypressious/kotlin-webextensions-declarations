package storage

import kotlin.Suppress
import kotlin.js.Promise
import webextensions.Event

/**
 * Storage items to return in the callback, where the values are replaced with those from storage if
        they exist.
 */
@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Keys() {
    inline operator fun get(key: String): dynamic = asDynamic()[key]
    inline operator fun set(key: String, value: dynamic) {
        asDynamic()[key] = value
    }
}

/**
 * A single key to get, list of keys to get, or a dictionary specifying default values (see
        description of the object).  An empty list or object will return an empty result object. 
        Pass in <code>null</code> to get the entire contents of storage. */
typealias Keys2 = Any

/**
 * Object with items in their key-value mappings.
 */
@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Items() {
    inline operator fun get(key: String): dynamic = asDynamic()[key]
    inline operator fun set(key: String, value: dynamic) {
        asDynamic()[key] = value
    }
}

/**
 * A single key or list of keys to get the total usage for. An empty list will return 0. Pass in
        <code>null</code> to get the total usage of all of storage. */
typealias Keys3 = Any

/**
 * <p>An object which gives each key/value pair to update storage with. Any other key/value pairs in
        storage will not be affected.</p><p>Primitive values such as numbers will serialize as
        expected. Values with a <code>typeof</code> <code>"object"</code> and
        <code>"function"</code> will typically serialize to <code>{}</code>, with the exception of
        <code>Array</code> (serializes as expected), <code>Date</code>, and <code>Regex</code>
        (serialize using their <code>String</code> representation).</p>
 */
@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Items2() {
    inline operator fun get(key: String): dynamic = asDynamic()[key]
    inline operator fun set(key: String, value: dynamic) {
        asDynamic()[key] = value
    }
}

/**
 * A single key or a list of keys for items to remove. */
typealias Keys4 = Any

/**
 * @param oldValue The old value of the item, if there was an old value.
 * @param newValue The new value of the item, if there is a new value.
 */
class StorageChange(
    var oldValue: dynamic = null,
    var newValue: dynamic = null
)

external class StorageArea {
    /**
     * Gets one or more items from storage.
     */
    fun get(keys: String): Promise<Items>

    /**
     * Gets one or more items from storage.
     */
    fun get(keys: Array<String>): Promise<Items>

    /**
     * Gets one or more items from storage.
     */
    fun get(keys: Keys): Promise<Items>

    /**
     * Gets the amount of space (in bytes) being used by one or more items.
     */
    fun getBytesInUse(keys: String): Promise<Int>

    /**
     * Gets the amount of space (in bytes) being used by one or more items.
     */
    fun getBytesInUse(keys: Array<String>): Promise<Int>

    /**
     * Sets multiple items.
     */
    fun set(items: Items2): Promise<Any>

    /**
     * Removes one or more items from storage.
     */
    fun remove(keys: String): Promise<Any>

    /**
     * Removes one or more items from storage.
     */
    fun remove(keys: Array<String>): Promise<Any>

    /**
     * Removes all items from storage.
     */
    fun clear(): Promise<Any>
}

/**
 * Object mapping each key that changed to its corresponding $(ref:storage.StorageChange) for that
        item.
 */
@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Changes() {
    inline operator fun get(key: String): StorageChange = asDynamic()[key]
    inline operator fun set(key: String, value: StorageChange) {
        asDynamic()[key] = value
    }
}

external class StorageNamespace {
    /**
     * Fired when one or more items change.
     *
     * @param changes Object mapping each key that changed to its corresponding
            $(ref:storage.StorageChange) for that item.
     * @param areaName The name of the storage area (<code>"sync"</code>, <code>"local"</code> or
            <code>"managed"</code>) the changes are for. */
    val onChanged: Event<(changes: Changes, areaName: String) -> Unit>

    /**
     * Items in the <code>sync</code> storage area are synced by the browser.
     */
    var sync: StorageArea

    /**
     * Items in the <code>local</code> storage area are local to each machine.
     */
    var local: StorageArea

    /**
     * Items in the <code>managed</code> storage area are set by administrators or native
            applications, and are read-only for the extension; trying to modify this namespace
            results in an error.
     */
    var managed: StorageArea
}
