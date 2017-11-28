package notifications

import kotlin.js.Promise

class GetAllNotifications()

typealias TemplateType = String

typealias PermissionLevel = String

external class NotificationItem {
  /**
   * Title of one item of a list notification.
   */
  val title: String

  /**
   * Additional details about this item.
   */
  val message: String
}

external class CreateNotificationOptions {
  /**
   * Which type of notification to display.
   */
  val type: TemplateType

  /**
   * A URL to the sender's avatar, app icon, or a thumbnail for image notifications.
   */
  val iconUrl: String

  /**
   * A URL to the app icon mask.
   */
  val appIconMaskUrl: String

  /**
   * Title of the notification (e.g. sender name for email).
   */
  val title: String

  /**
   * Main notification content.
   */
  val message: String

  /**
   * Alternate notification content with a lower-weight font.
   */
  val contextMessage: String

  /**
   * Priority ranges from -2 to 2. -2 is lowest priority. 2 is highest. Zero is default.
   */
  val priority: Int

  /**
   * A timestamp associated with the notification, in milliseconds past the epoch.
   */
  val eventTime: Any

  /**
   * A URL to the image thumbnail for image-type notifications.
   */
  val imageUrl: String

  /**
   * Items for multi-item notifications.
   */
  val items: Array<NotificationItem>

  /**
   * Current progress ranges from 0 to 100.
   */
  val progress: Int

  /**
   * Whether to show UI indicating that the app will visibly respond to clicks on the body of a notification.
   */
  val isClickable: Boolean
}

external class UpdateNotificationOptions {
  /**
   * Which type of notification to display.
   */
  val type: TemplateType

  /**
   * A URL to the sender's avatar, app icon, or a thumbnail for image notifications.
   */
  val iconUrl: String

  /**
   * A URL to the app icon mask.
   */
  val appIconMaskUrl: String

  /**
   * Title of the notification (e.g. sender name for email).
   */
  val title: String

  /**
   * Main notification content.
   */
  val message: String

  /**
   * Alternate notification content with a lower-weight font.
   */
  val contextMessage: String

  /**
   * Priority ranges from -2 to 2. -2 is lowest priority. 2 is highest. Zero is default.
   */
  val priority: Int

  /**
   * A timestamp associated with the notification, in milliseconds past the epoch.
   */
  val eventTime: Any

  /**
   * A URL to the image thumbnail for image-type notifications.
   */
  val imageUrl: String

  /**
   * Items for multi-item notifications.
   */
  val items: Array<NotificationItem>

  /**
   * Current progress ranges from 0 to 100.
   */
  val progress: Int

  /**
   * Whether to show UI indicating that the app will visibly respond to clicks on the body of a notification.
   */
  val isClickable: Boolean
}

external class Notifications {
  /**
   * Creates and displays a notification.
   */
  fun create(notificationId: String, options: CreateNotificationOptions): Promise<String>

  /**
   * Clears an existing notification.
   */
  fun clear(notificationId: String): Promise<Boolean>

  /**
   * Retrieves all the notifications.
   */
  fun getAll(): Promise<GetAllNotifications>
}
