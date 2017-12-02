package notifications

import browser.Event
import kotlin.Suppress
import kotlin.js.Promise

typealias TemplateType = String

typealias PermissionLevel = String

class NotificationItem(/**
 * Title of one item of a list notification.
 */
var title: String, /**
 * Additional details about this item.
 */
var message: String)

class CreateNotificationOptions(
        /**
         * Which type of notification to display.
         */
        var type: TemplateType,
        /**
         * A URL to the sender's avatar, app icon, or a thumbnail for image notifications.
         */
        var iconUrl: String? = null,
        /**
         * A URL to the app icon mask.
         */
        var appIconMaskUrl: String? = null,
        /**
         * Title of the notification (e.g. sender name for email).
         */
        var title: String,
        /**
         * Main notification content.
         */
        var message: String,
        /**
         * Alternate notification content with a lower-weight font.
         */
        var contextMessage: String? = null,
        /**
         * Priority ranges from -2 to 2. -2 is lowest priority. 2 is highest. Zero is default.
         */
        var priority: Int? = null,
        /**
         * A timestamp associated with the notification, in milliseconds past the epoch.
         */
        var eventTime: Int? = null,
        /**
         * A URL to the image thumbnail for image-type notifications.
         */
        var imageUrl: String? = null,
        /**
         * Items for multi-item notifications.
         */
        var items: Array<NotificationItem>? = null,
        /**
         * Current progress ranges from 0 to 100.
         */
        var progress: Int? = null,
        /**
         * Whether to show UI indicating that the app will visibly respond to clicks on the body of a notification.
         */
        var isClickable: Boolean? = null
)

class UpdateNotificationOptions(
        /**
         * Which type of notification to display.
         */
        var type: TemplateType? = null,
        /**
         * A URL to the sender's avatar, app icon, or a thumbnail for image notifications.
         */
        var iconUrl: String? = null,
        /**
         * A URL to the app icon mask.
         */
        var appIconMaskUrl: String? = null,
        /**
         * Title of the notification (e.g. sender name for email).
         */
        var title: String? = null,
        /**
         * Main notification content.
         */
        var message: String? = null,
        /**
         * Alternate notification content with a lower-weight font.
         */
        var contextMessage: String? = null,
        /**
         * Priority ranges from -2 to 2. -2 is lowest priority. 2 is highest. Zero is default.
         */
        var priority: Int? = null,
        /**
         * A timestamp associated with the notification, in milliseconds past the epoch.
         */
        var eventTime: Int? = null,
        /**
         * A URL to the image thumbnail for image-type notifications.
         */
        var imageUrl: String? = null,
        /**
         * Items for multi-item notifications.
         */
        var items: Array<NotificationItem>? = null,
        /**
         * Current progress ranges from 0 to 100.
         */
        var progress: Int? = null,
        /**
         * Whether to show UI indicating that the app will visibly respond to clicks on the body of a notification.
         */
        var isClickable: Boolean? = null
)

class Buttons(var title: String, var iconUrl: String? = null)

class Buttons2(var title: String, var iconUrl: String? = null)

/**
 * The set of notifications currently in the system.
 */
@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Notifications() {
    inline operator fun get(key: String): CreateNotificationOptions = asDynamic()[key]
    inline operator fun set(key: String, value: CreateNotificationOptions) {
        asDynamic()[key] = value
    }
}

external class NotificationsNamespace {
    val onClosed: Event<(notificationId: String, byUser: Boolean) -> Unit>

    val onClicked: Event<(notificationId: String) -> Unit>

    val onButtonClicked: Event<(notificationId: String, buttonIndex: Int) -> Unit>

    val onShown: Event<(notificationId: String) -> Unit>

    /**
     * Creates and displays a notification.
     */
    fun create(notificationId: String? = definedExternally, options: CreateNotificationOptions): Promise<String>

    /**
     * Clears an existing notification.
     */
    fun clear(notificationId: String): Promise<Boolean>

    /**
     * Retrieves all the notifications.
     */
    fun getAll(): Promise<Notifications>
}
