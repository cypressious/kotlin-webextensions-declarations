package notifications

import kotlin.Suppress
import kotlin.js.Promise
import webextensions.Event

typealias TemplateType = String

typealias PermissionLevel = String

/**
 * @param title Title of one item of a list notification.
 * @param message Additional details about this item.
 */
class NotificationItem(
    var title: String,
    var message: String
)

/**
 * @param type Which type of notification to display.
 * @param iconUrl A URL to the sender's avatar, app icon, or a thumbnail for image notifications.
 * @param appIconMaskUrl A URL to the app icon mask.
 * @param title Title of the notification (e.g. sender name for email).
 * @param message Main notification content.
 * @param contextMessage Alternate notification content with a lower-weight font.
 * @param priority Priority ranges from -2 to 2. -2 is lowest priority. 2 is highest. Zero is
        default.
 * @param eventTime A timestamp associated with the notification, in milliseconds past the epoch.
 * @param imageUrl A URL to the image thumbnail for image-type notifications.
 * @param items Items for multi-item notifications.
 * @param progress Current progress ranges from 0 to 100.
 * @param isClickable Whether to show UI indicating that the app will visibly respond to clicks on
        the body of a notification.
 */
class CreateNotificationOptions(
    var type: TemplateType,
    var iconUrl: String? = null,
    var appIconMaskUrl: String? = null,
    var title: String,
    var message: String,
    var contextMessage: String? = null,
    var priority: Int? = null,
    var eventTime: Float? = null,
    var imageUrl: String? = null,
    var items: Array<NotificationItem>? = null,
    var progress: Int? = null,
    var isClickable: Boolean? = null
)

/**
 * @param type Which type of notification to display.
 * @param iconUrl A URL to the sender's avatar, app icon, or a thumbnail for image notifications.
 * @param appIconMaskUrl A URL to the app icon mask.
 * @param title Title of the notification (e.g. sender name for email).
 * @param message Main notification content.
 * @param contextMessage Alternate notification content with a lower-weight font.
 * @param priority Priority ranges from -2 to 2. -2 is lowest priority. 2 is highest. Zero is
        default.
 * @param eventTime A timestamp associated with the notification, in milliseconds past the epoch.
 * @param imageUrl A URL to the image thumbnail for image-type notifications.
 * @param items Items for multi-item notifications.
 * @param progress Current progress ranges from 0 to 100.
 * @param isClickable Whether to show UI indicating that the app will visibly respond to clicks on
        the body of a notification.
 */
class UpdateNotificationOptions(
    var type: TemplateType? = null,
    var iconUrl: String? = null,
    var appIconMaskUrl: String? = null,
    var title: String? = null,
    var message: String? = null,
    var contextMessage: String? = null,
    var priority: Int? = null,
    var eventTime: Float? = null,
    var imageUrl: String? = null,
    var items: Array<NotificationItem>? = null,
    var progress: Int? = null,
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
    /**
     * Fired when the notification closed, either by the system or by user action.
     *
     * @param notificationId The notificationId of the closed notification.
     * @param byUser True if the notification was closed by the user. */
    val onClosed: Event<(notificationId: String, byUser: Boolean) -> Unit>

    /**
     * Fired when the user clicked in a non-button area of the notification.
     *
     * @param notificationId The notificationId of the clicked notification. */
    val onClicked: Event<(notificationId: String) -> Unit>

    /**
     * Fired when the  user pressed a button in the notification.
     *
     * @param notificationId The notificationId of the clicked notification.
     * @param buttonIndex The index of the button clicked by the user. */
    val onButtonClicked: Event<(notificationId: String, buttonIndex: Float) -> Unit>

    /**
     * Fired when the notification is shown.
     *
     * @param notificationId The notificationId of the shown notification. */
    val onShown: Event<(notificationId: String) -> Unit>

    /**
     * Creates and displays a notification.
     */
    fun create(notificationId: String? = definedExternally, options: CreateNotificationOptions):
            Promise<String>

    /**
     * Clears an existing notification.
     */
    fun clear(notificationId: String): Promise<Boolean>

    /**
     * Retrieves all the notifications.
     */
    fun getAll(): Promise<Notifications>
}
