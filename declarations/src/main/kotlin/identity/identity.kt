package identity

import kotlin.js.Promise

class GetAuthTokenDetails(
    val interactive: Boolean,
    val account: AccountInfo,
    val scopes: Array<String>
)

class GetProfileUserInfoUserinfo(val email: String, val id: String)

class RemoveCachedAuthTokenDetails(val token: String)

class RemoveCachedAuthTokenUserinfo(val email: String, val id: String)

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
   * Retrieves a list of AccountInfo objects describing the accounts present on the profile.
   */
  fun getAccounts(): Promise<Array<AccountInfo>>

  /**
   * Gets an OAuth2 access token using the client ID and scopes specified in the oauth2 section of manifest.json.
   */
  fun getAuthToken(details: GetAuthTokenDetails): Promise<Array<AccountInfo>>

  /**
   * Retrieves email address and obfuscated gaia id of the user signed into a profile.
   */
  fun getProfileUserInfo(): Promise<GetProfileUserInfoUserinfo>

  /**
   * Removes an OAuth2 access token from the Identity API's token cache.
   */
  fun removeCachedAuthToken(details: RemoveCachedAuthTokenDetails): Promise<RemoveCachedAuthTokenUserinfo>

  /**
   * Starts an auth flow at the specified URL.
   */
  fun launchWebAuthFlow(details: LaunchWebAuthFlowDetails): Promise<String>

  /**
   * Generates a redirect URL to be used in |launchWebAuthFlow|.
   */
  fun getRedirectURL( path: String)
}
