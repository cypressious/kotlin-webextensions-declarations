package storage

import kotlin.Any

external class StorageChange {
  /**
   * The old value of the item, if there was an old value.
   */
  val oldValue: Any

  /**
   * The new value of the item, if there is a new value.
   */
  val newValue: Any
}

typealias StorageArea = Any

external class Storage
