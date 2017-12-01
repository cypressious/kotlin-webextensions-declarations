package permissions

import kotlin.js.Promise
import manifest.MatchPattern
import manifest.MatchPatternInternal
import manifest.OptionalPermission
import manifest.Permission

external class Permissions {
    var permissions: Array<OptionalPermission>

    var origins: Array<MatchPattern>
}

external class AnyPermissions {
    var permissions: Array<Permission>

    var origins: Array<MatchPatternInternal>
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
