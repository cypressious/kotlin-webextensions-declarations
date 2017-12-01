package contextualIdentities

import kotlin.js.Promise

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

/**
 * Information to filter the contextual identities being retrieved.
 */
class Details(/**
 * Filters the contextual identity by name.
 */
val name: String?)

/**
 * Details about the contextual identity being created.
 */
class Details2(
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
 * Details about the contextual identity being created.
 */
class Details3(
        /**
         * The name of the contextual identity.
         */
        val name: String?,
        /**
         * The color of the contextual identity.
         */
        val color: String?,
        /**
         * The icon of the contextual identity.
         */
        val icon: String?
)

external class ContextualIdentitiesNamespace {
    /**
     * Retrieves information about a single contextual identity.
     */
    fun get(cookieStoreId: String): Promise<Any>

    /**
     * Retrieves all contextual identities
     */
    fun query(details: Details): Promise<Any>

    /**
     * Creates a contextual identity with the given data.
     */
    fun create(details: Details2): Promise<Any>

    /**
     * Updates a contextual identity with the given data.
     */
    fun update(cookieStoreId: String, details: Details3): Promise<Any>

    /**
     * Deletes a contetual identity by its cookie Store ID.
     */
    fun remove(cookieStoreId: String): Promise<Any>
}
