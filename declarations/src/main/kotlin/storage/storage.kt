package storage

import browser.Event

external class StorageChange {
    /**
     * The old value of the item, if there was an old value.
     */
    val oldValue: Any?

    /**
     * The new value of the item, if there is a new value.
     */
    val newValue: Any?
}

typealias StorageArea = Any

/**
 * Object mapping each key that changed to its corresponding $(ref:storage.StorageChange) for that item. */
typealias Changes = Any

external class StorageNamespace {
    val onChanged: Event<(changes: Changes, areaName: String) -> Unit>
}
