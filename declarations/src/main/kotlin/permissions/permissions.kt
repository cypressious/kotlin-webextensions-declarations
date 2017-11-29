package permissions

import kotlin.js.Promise

external class Permissions {
  val permissions: Array<manifest.OptionalPermission>?

  val origins: Array<manifest.MatchPattern>?
}

external class AnyPermissions {
  val permissions: Array<manifest.Permission>?

  val origins: Array<manifest.MatchPatternInternal>?
}

external class PermissionsNamespace {
  /**
   * Get a list of all the extension's permissions.
   */
  fun getAll(): Promise<AnyPermissions>

  /**
   * Check if the extension has the given permissions.
   */
  fun contains(permissions: AnyPermissions): Promise<Boolean>

  /**
   * Request the given permissions.
   */
  fun request(permissions: Permissions): Promise<Boolean>

  /**
   * Relinquish the given permissions.
   */
  fun remove(permissions: Permissions): Promise<Any>
}
