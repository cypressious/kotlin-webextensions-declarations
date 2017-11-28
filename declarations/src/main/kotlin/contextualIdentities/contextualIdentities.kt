package contextualIdentities

import kotlin.js.Promise

class QueryDetails(/**
 * Filters the contextual identity by name.
 */
val name: String)

class CreateDetails(
    /**
     * The name of the contextual identity.
     */
    val name: String,
    /**
     * The color of the contextual identity.
     */
    val color: String,
    /**
     * The icon of the contextual identity.
     */
    val icon: String
)

class UpdateDetails(
    /**
     * The name of the contextual identity.
     */
    val name: String,
    /**
     * The color of the contextual identity.
     */
    val color: String,
    /**
     * The icon of the contextual identity.
     */
    val icon: String
)

/**
 * Represents information about a contextual identity.
 */
external class ContextualIdentity {
  /**
   * The name of the contextual identity.
   */
  val name: String

  /**
   * The icon name of the contextual identity.
   */
  val icon: String

  /**
   * The icon url of the contextual identity.
   */
  val iconUrl: String

  /**
   * The color name of the contextual identity.
   */
  val color: String

  /**
   * The color hash of the contextual identity.
   */
  val colorCode: String

  /**
   * The cookie store ID of the contextual identity.
   */
  val cookieStoreId: String
}

external class ContextualIdentities {
  /**
   * Retrieves information about a single contextual identity.
   */
  fun get(cookieStoreId: String): Promise<Any>

  /**
   * Retrieves all contextual identities
   */
  fun query(details: QueryDetails): Promise<Any>

  /**
   * Creates a contextual identity with the given data.
   */
  fun create(details: CreateDetails): Promise<Any>

  /**
   * Updates a contextual identity with the given data.
   */
  fun update(cookieStoreId: String, details: UpdateDetails): Promise<Any>

  /**
   * Deletes a contetual identity by its cookie Store ID.
   */
  fun remove(cookieStoreId: String): Promise<Any>
}
