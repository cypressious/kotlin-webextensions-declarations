package storage

import browser.Event

external class StorageChange {
    /**
     * The old value of the item, if there was an old value.
     */
    var oldValue: Any?

    /**
     * The new value of the item, if there is a new value.
     */
    var newValue: Any?
}

typealias StorageArea = Any

/**
 * Object mapping each key that changed to its corresponding $(ref:storage.StorageChange) for that item. */
typealias Changes = Any

external class StorageNamespace {
    val onChanged: Event<(changes: Changes, areaName: String) -> Unit>
}
