package storage

import browser.Event

external class StorageChange {
    /**
     * The old value of the item, if there was an old value.
     */
    var oldValue: dynamic

    /**
     * The new value of the item, if there is a new value.
     */
    var newValue: dynamic
}

typealias StorageArea = Any

/**
 * Object mapping each key that changed to its corresponding $(ref:storage.StorageChange) for that item.
 */
external class Changes {
    operator fun get(key: String): StorageChange

    operator fun set(key: String, value: StorageChange)
}

external class StorageNamespace {
    val onChanged: Event<(changes: Changes, areaName: String) -> Unit>
}
