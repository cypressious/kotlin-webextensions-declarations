package runtime

import events.Event
import kotlin.Any
import kotlin.js.Promise
import tabs.Tab

typealias GetBackgroundPageBackgroundPage = Any

class ConnectConnectInfo(/**
 * Will be passed into onConnect for processes that are listening for the connection event.
 */
val name: String?, /**
 * Whether the TLS channel ID will be passed into onConnectExternal for processes that are listening for the connection event.
 */
val includeTlsChannelId: Boolean?)

class SendMessageOptions(/**
 * If true, the message will be directed to the extension's proxy sandbox.
 */
val toProxyScript: Boolean?)

/**
 * An object which allows two way communication with other pages.
 */
external class Port {
  val name: String

  val disconnect: Any

  val onDisconnect: Event

  val onMessage: Event

  val postMessage: Any

  /**
   * This property will <b>only</b> be present on ports passed to onConnect/onConnectExternal listeners.
   */
  val sender: MessageSender
}

/**
 * An object containing information about the script context that sent a message or request.
 */
external class MessageSender {
  /**
   * The $(ref:tabs.Tab) which opened the connection, if any. This property will <strong>only</strong> be present when the connection was opened from a tab (including content scripts), and <strong>only</strong> if the receiver is an extension, not an app.
   */
  val tab: Tab

  /**
   * The $(topic:frame_ids)[frame] that opened the connection. 0 for top-level frames, positive for child frames. This will only be set when <code>tab</code> is set.
   */
  val frameId: Int?

  /**
   * The ID of the extension or app that opened the connection, if any.
   */
  val id: String?

  /**
   * The URL of the page or frame that opened the connection. If the sender is in an iframe, it will be iframe's URL not the URL of the page which hosts it.
   */
  val url: String?
}

typealias PlatformOs = String

typealias PlatformArch = String

/**
 * An object containing information about the current platform.
 */
external class PlatformInfo {
  /**
   * The operating system the browser is running on.
   */
  val os: PlatformOs

  /**
   * The machine's processor architecture.
   */
  val arch: PlatformArch
}

/**
 * An object containing information about the current browser.
 */
external class BrowserInfo {
  /**
   * The name of the browser, for example 'Firefox'.
   */
  val name: String

  /**
   * The name of the browser vendor, for example 'Mozilla'.
   */
  val vendor: String

  /**
   * The browser's version, for example '42.0.0' or '0.8.1pre'.
   */
  val version: String

  /**
   * The browser's build ID/date, for example '20160101'.
   */
  val buildID: String
}

typealias RequestUpdateCheckStatus = String

typealias OnInstalledReason = String

typealias OnRestartRequiredReason = String

external class Runtime {
  /**
   * Retrieves the JavaScript 'window' object for the background page running inside the current extension/app. If the background page is an event page, the system will ensure it is loaded before calling the callback. If there is no background page, an error is set.
   */
  fun getBackgroundPage(): Promise<GetBackgroundPageBackgroundPage?>

  /**
   * <p>Open your Extension's options page, if possible.</p><p>The precise behavior may depend on your manifest's <code>$(topic:optionsV2)[options_ui]</code> or <code>$(topic:options)[options_page]</code> key, or what the browser happens to support at the time.</p><p>If your Extension does not declare an options page, or the browser failed to create one for some other reason, the callback will set $(ref:lastError).</p>
   */
  fun openOptionsPage(): Promise<Any>

  /**
   * Returns details about the app or extension from the manifest. The object returned is a serialization of the full $(topic:manifest)[manifest file].
   */
  fun getManifest()

  /**
   * Converts a relative path within an app/extension install directory to a fully-qualified URL.
   */
  fun getURL(path: String)

  /**
   * Sets the URL to be visited upon uninstallation. This may be used to clean up server-side data, do analytics, and implement surveys. Maximum 255 characters.
   */
  fun setUninstallURL(url: String): Promise<Any>

  /**
   * Reloads the app or extension.
   */
  fun reload()

  /**
   * Attempts to connect to connect listeners within an extension/app (such as the background page), or other extensions/apps. This is useful for content scripts connecting to their extension processes, inter-app/extension communication, and $(topic:manifest/externally_connectable)[web messaging]. Note that this does not connect to any listeners in a content script. Extensions may connect to content scripts embedded in tabs via $(ref:tabs.connect).
   */
  fun connect(extensionId: String?, connectInfo: ConnectConnectInfo?)

  /**
   * Connects to a native application in the host machine.
   */
  fun connectNative(application: String)

  /**
   * Sends a single message to event listeners within your extension/app or a different extension/app. Similar to $(ref:runtime.connect) but only sends a single message, with an optional response. If sending to your extension, the $(ref:runtime.onMessage) event will be fired in each page, or $(ref:runtime.onMessageExternal), if a different extension. Note that extensions cannot send messages to content scripts using this method. To send messages to content scripts, use $(ref:tabs.sendMessage).
   */
  fun sendMessage(
      extensionId: String?,
      message: Any,
      options: SendMessageOptions?
  ): Promise<Any>

  /**
   * Send a single message to a native application.
   */
  fun sendNativeMessage(application: String, message: Any): Promise<Any>

  /**
   * Returns information about the current browser.
   */
  fun getBrowserInfo(): Promise<BrowserInfo>

  /**
   * Returns information about the current platform.
   */
  fun getPlatformInfo(): Promise<PlatformInfo>
}
