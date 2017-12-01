package identity

import kotlin.js.Promise

/**
 * An object encapsulating an OAuth account id.
 */
external class AccountInfo {
    /**
     * A unique identifier for the account. This ID will not change for the lifetime of the account. 
     */
    var id: String
}

external class Details {
    var interactive: Boolean?

    var account: AccountInfo?

    var scopes: Array<String>
}

external class Userinfo {
    var email: String

    var id: String
}

external class Details2 {
    var token: String
}

external class Userinfo2 {
    var email: String

    var id: String
}

external class Details3 {
    var url: String

    var interactive: Boolean?
}

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
