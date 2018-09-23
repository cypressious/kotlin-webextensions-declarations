package contextualIdentities

import kotlin.js.Promise
import webextensions.Event

/**
 * Represents information about a contextual identity.
 * @param name The name of the contextual identity.
 * @param icon The icon name of the contextual identity.
 * @param iconUrl The icon url of the contextual identity.
 * @param color The color name of the contextual identity.
 * @param colorCode The color hash of the contextual identity.
 * @param cookieStoreId The cookie store ID of the contextual identity.
 */
class ContextualIdentity(
    var name: String,
    var icon: String,
    var iconUrl: String,
    var color: String,
    var colorCode: String,
    var cookieStoreId: String
)

/**
 * Information to filter the contextual identities being retrieved.
 * @param name Filters the contextual identity by name.
 */
class Details(
    var name: String? = null
)

/**
 * Details about the contextual identity being created.
 * @param name The name of the contextual identity.
 * @param color The color of the contextual identity.
 * @param icon The icon of the contextual identity.
 */
class Details2(
    var name: String,
    var color: String,
    var icon: String
)

/**
 * Details about the contextual identity being created.
 * @param name The name of the contextual identity.
 * @param color The color of the contextual identity.
 * @param icon The icon of the contextual identity.
 */
class Details3(
    var name: String? = null,
    var color: String? = null,
    var icon: String? = null
)

/**
 * @param contextualIdentity Contextual identity that has been updated
 */
class ChangeInfo(
    var contextualIdentity: ContextualIdentity
)

/**
 * @param contextualIdentity Contextual identity that has been created
 */
class ChangeInfo2(
    var contextualIdentity: ContextualIdentity
)

/**
 * @param contextualIdentity Contextual identity that has been removed
 */
class ChangeInfo3(
    var contextualIdentity: ContextualIdentity
)

external class ContextualIdentitiesNamespace {
    /**
     * Fired when a container is updated.
     *
     * @param changeInfo null */
    val onUpdated: Event<(changeInfo: ChangeInfo) -> Unit>

    /**
     * Fired when a new container is created.
     *
     * @param changeInfo null */
    val onCreated: Event<(changeInfo: ChangeInfo2) -> Unit>

    /**
     * Fired when a container is removed.
     *
     * @param changeInfo null */
    val onRemoved: Event<(changeInfo: ChangeInfo3) -> Unit>

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
