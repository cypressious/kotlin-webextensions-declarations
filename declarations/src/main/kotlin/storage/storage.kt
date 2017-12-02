package storage

import browser.Event
import kotlin.Suppress

class StorageChange(/**
 * The old value of the item, if there was an old value.
 */
var oldValue: dynamic = null, /**
 * The new value of the item, if there is a new value.
 */
var newValue: dynamic = null)

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
    val onChanged: Event<(changes: Changes, areaName: String) -> Unit>
}
