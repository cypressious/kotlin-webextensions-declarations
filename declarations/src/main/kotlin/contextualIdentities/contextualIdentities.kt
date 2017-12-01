package contextualIdentities

import browser.Event
import kotlin.js.Promise

/**
 * Represents information about a contextual identity.
 */
external class ContextualIdentity {
    /**
     * The name of the contextual identity.
     */
    var name: String

    /**
     * The icon name of the contextual identity.
     */
    var icon: String

    /**
     * The icon url of the contextual identity.
     */
    var iconUrl: String

    /**
     * The color name of the contextual identity.
     */
    var color: String

    /**
     * The color hash of the contextual identity.
     */
    var colorCode: String

    /**
     * The cookie store ID of the contextual identity.
     */
    var cookieStoreId: String
}

/**
 * Information to filter the contextual identities being retrieved.
 */
external class Details {
    /**
     * Filters the contextual identity by name.
     */
    var name: String?
}

/**
 * Details about the contextual identity being created.
 */
external class Details2 {
    /**
     * The name of the contextual identity.
     */
    var name: String

    /**
     * The color of the contextual identity.
     */
    var color: String

    /**
     * The icon of the contextual identity.
     */
    var icon: String
}

/**
 * Details about the contextual identity being created.
 */
external class Details3 {
    /**
     * The name of the contextual identity.
     */
    var name: String?

    /**
     * The color of the contextual identity.
     */
    var color: String?

    /**
     * The icon of the contextual identity.
     */
    var icon: String?
}

external class ChangeInfo {
    /**
     * Contextual identity that has been updated
     */
    var contextualIdentity: ContextualIdentity
}

external class ChangeInfo2 {
    /**
     * Contextual identity that has been created
     */
    var contextualIdentity: ContextualIdentity
}

external class ChangeInfo3 {
    /**
     * Contextual identity that has been removed
     */
    var contextualIdentity: ContextualIdentity
}

external class ContextualIdentitiesNamespace {
    val onUpdated: Event<(changeInfo: ChangeInfo) -> Unit>

    val onCreated: Event<(changeInfo: ChangeInfo2) -> Unit>

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
