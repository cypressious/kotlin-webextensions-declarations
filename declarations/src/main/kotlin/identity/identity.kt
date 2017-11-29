package identity

import kotlin.js.Promise

/**
 * An object encapsulating an OAuth account id.
 */
external class AccountInfo {
  /**
   * A unique identifier for the account. This ID will not change for the lifetime of the account. 
   */
  val id: String
}

external class Details {
  val interactive: Boolean?

  val account: AccountInfo

  val scopes: Array<String>?
}

external class Details2 {
  val token: String
}

external class Details3 {
  val url: String

  val interactive: Boolean?
}

external class IdentityNamespace {
  /**
   * Starts an auth flow at the specified URL.
   */
  fun launchWebAuthFlow(details: Details3): Promise<String?>

  /**
   * Generates a redirect URL to be used in |launchWebAuthFlow|.
   */
  fun getRedirectURL( path: String?)
}
