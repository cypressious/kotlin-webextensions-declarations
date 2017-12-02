package identity

import kotlin.js.Promise

/**
 * An object encapsulating an OAuth account id.
 */
class AccountInfo(/**
 * A unique identifier for the account. This ID will not change for the lifetime of the account. 
 */
var id: String)

class Details(
        var interactive: Boolean? = null,
        var account: AccountInfo? = null,
        var scopes: Array<String>? = null
)

class Userinfo(var email: String, var id: String)

class Details2(var token: String)

class Userinfo2(var email: String, var id: String)

class Details3(var url: String, var interactive: Boolean? = null)

external class IdentityNamespace {
    /**
     * Starts an auth flow at the specified URL.
     */
    fun launchWebAuthFlow(details: Details3): Promise<String?>

    /**
     * Generates a redirect URL to be used in |launchWebAuthFlow|.
     */
    fun getRedirectURL( path: String? = definedExternally): String
}
