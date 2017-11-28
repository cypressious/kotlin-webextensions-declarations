package identity

import kotlin.js.Promise

class LaunchWebAuthFlowDetails(val url: String, val interactive: Boolean)

/**
 * An object encapsulating an OAuth account id.
 */
external class AccountInfo {
  /**
   * A unique identifier for the account. This ID will not change for the lifetime of the account. 
   */
  val id: String
}

external class Identity {
  /**
   * Starts an auth flow at the specified URL.
   */
  fun launchWebAuthFlow(details: LaunchWebAuthFlowDetails): Promise<String>

  /**
   * Generates a redirect URL to be used in |launchWebAuthFlow|.
   */
  fun getRedirectURL( path: String)
}
