package permissions

import kotlin.js.Promise

external class Permissions {
    var permissions: Array<manifest.OptionalPermission>?

    var origins: Array<manifest.MatchPattern>?
}

external class AnyPermissions {
    var permissions: Array<manifest.Permission>?

    var origins: Array<manifest.MatchPatternInternal>?
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
