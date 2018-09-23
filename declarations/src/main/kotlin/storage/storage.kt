package storage

import kotlin.Suppress
import webextensions.Event

/**
 * @param oldValue The old value of the item, if there was an old value.
 * @param newValue The new value of the item, if there is a new value.
 */
class StorageChange(
    var oldValue: dynamic = null,
    var newValue: dynamic = null
)

typealias StorageArea = Any

/**
 * Object mapping each key that changed to its corresponding $(ref:storage.StorageChange) for that item.
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
     * @param changes Object mapping each key that changed to its corresponding $(ref:storage.StorageChange) for that item.
     * @param areaName The name of the storage area (<code>"sync"</code>, <code>"local"</code> or <code>"managed"</code>) the changes are for. */
    val onChanged: Event<(changes: Changes, areaName: String) -> Unit>
}
