package runtime

import events.Event
import kotlin.js.Promise
import tabs.Tab

/**
 * An object which allows two way communication with other pages.
 */
external class Port {
    var name: String

    var disconnect: () -> Unit

    var onDisconnect: Event

    var onMessage: Event

    var postMessage: (Any) -> Unit

    /**
     * This property will <b>only</b> be present on ports passed to onConnect/onConnectExternal listeners.
     */
    var sender: MessageSender?
}

/**
 * An object containing information about the script context that sent a message or request.
 */
external class MessageSender {
    /**
     * The $(ref:tabs.Tab) which opened the connection, if any. This property will <strong>only</strong> be present when the connection was opened from a tab (including content scripts), and <strong>only</strong> if the receiver is an extension, not an app.
     */
    var tab: Tab?

    /**
     * The $(topic:frame_ids)[frame] that opened the connection. 0 for top-level frames, positive for child frames. This will only be set when <code>tab</code> is set.
     */
    var frameId: Int?

    /**
     * The ID of the extension or app that opened the connection, if any.
     */
    var id: String?

    /**
     * The URL of the page or frame that opened the connection. If the sender is in an iframe, it will be iframe's URL not the URL of the page which hosts it.
     */
    var url: String?
}

/**
 * The operating system the browser is running on. */
typealias PlatformOs = String

/**
 * The machine's processor architecture. */
typealias PlatformArch = String

/**
 * An object containing information about the current platform.
 */
external class PlatformInfo {
    /**
     * The operating system the browser is running on.
     */
    var os: PlatformOs

    /**
     * The machine's processor architecture.
     */
    var arch: PlatformArch
}

/**
 * An object containing information about the current browser.
 */
external class BrowserInfo {
    /**
     * The name of the browser, for example 'Firefox'.
     */
    var name: String

    /**
     * The name of the browser vendor, for example 'Mozilla'.
     */
    var vendor: String

    /**
     * The browser's version, for example '42.0.0' or '0.8.1pre'.
     */
    var version: String

    /**
     * The browser's build ID/date, for example '20160101'.
     */
    var buildID: String
}

/**
 * Result of the update check. */
typealias RequestUpdateCheckStatus = String

/**
 * The reason that this event is being dispatched. */
typealias OnInstalledReason = String

/**
 * The reason that the event is being dispatched. 'app_update' is used when the restart is needed because the application is updated to a newer version. 'os_update' is used when the restart is needed because the browser/OS is updated to a newer version. 'periodic' is used when the system runs for more than the permitted uptime set in the enterprise policy. */
typealias OnRestartRequiredReason = String

/**
 * This will be defined during an API method callback if there was an error
 */
external class LastError {
    /**
     * Details about the error which occurred.
     */
    var message: String?
}

/**
 * The JavaScript 'window' object for the background page. */
typealias BackgroundPage = Any

/**
 * The manifest details.
 */
external class GetManifestResult

/**
 * If an update is available, this contains more information about the available update.
 */
external class Details {
    /**
     * The version of the available update.
     */
    var version: String
}

external class ConnectInfo {
    /**
     * Will be passed into onConnect for processes that are listening for the connection event.
     */
    var name: String?

    /**
     * Whether the TLS channel ID will be passed into onConnectExternal for processes that are listening for the connection event.
     */
    var includeTlsChannelId: Boolean?
}

external class Options {
    /**
     * If true, the message will be directed to the extension's proxy sandbox.
     */
    var toProxyScript: Boolean?
}

typealias DirectoryEntry = Any

external class Details2 {
    /**
     * The reason that this event is being dispatched.
     */
    var reason: OnInstalledReason

    /**
     * Indicates the previous version of the extension, which has just been updated. This is present only if 'reason' is 'update'.
     */
    var previousVersion: String?

    /**
     * Indicates whether the addon is installed as a temporary extension.
     */
    var temporary: Boolean
}

/**
 * The manifest details of the available update.
 */
external class Details3 {
    /**
     * The version number of the available update.
     */
    var version: String
}

external class RuntimeNamespace {
    val onStartup: browser.Event<() -> Unit>

    val onInstalled: browser.Event<(details: Details2) -> Unit>

    val onUpdateAvailable: browser.Event<(details: Details3) -> Unit>

    val onConnect: browser.Event<(port: Port) -> Unit>

    val onConnectExternal: browser.Event<(port: Port) -> Unit>

    val onMessage: browser.Event<(
            message: dynamic,
            sender: MessageSender,
            sendResponse: () -> Unit
    ) -> Unit>

    val onMessageExternal: browser.Event<(
            message: dynamic,
            sender: MessageSender,
            sendResponse: () -> Unit
    ) -> Unit>

    /**
     * Retrieves the JavaScript 'window' object for the background page running inside the current extension/app. If the background page is an event page, the system will ensure it is loaded before calling the callback. If there is no background page, an error is set.
     */
    fun getBackgroundPage(): Promise<BackgroundPage?>

    /**
     * <p>Open your Extension's options page, if possible.</p><p>The precise behavior may depend on your manifest's <code>$(topic:optionsV2)[options_ui]</code> or <code>$(topic:options)[options_page]</code> key, or what the browser happens to support at the time.</p><p>If your Extension does not declare an options page, or the browser failed to create one for some other reason, the callback will set $(ref:lastError).</p>
     */
    fun openOptionsPage(): Promise<Any>

    /**
     * Returns details about the app or extension from the manifest. The object returned is a serialization of the full $(topic:manifest)[manifest file].
     */
    fun getManifest(): GetManifestResult

    /**
     * Converts a relative path within an app/extension install directory to a fully-qualified URL.
     */
    fun getURL(path: String): String

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
    fun connect(extensionId: String? = definedExternally, connectInfo: ConnectInfo? = definedExternally): Port

    /**
     * Connects to a native application in the host machine.
     */
    fun connectNative(application: String): Port

    /**
     * Sends a single message to event listeners within your extension/app or a different extension/app. Similar to $(ref:runtime.connect) but only sends a single message, with an optional response. If sending to your extension, the $(ref:runtime.onMessage) event will be fired in each page, or $(ref:runtime.onMessageExternal), if a different extension. Note that extensions cannot send messages to content scripts using this method. To send messages to content scripts, use $(ref:tabs.sendMessage).
     */
    fun sendMessage(
            extensionId: String? = definedExternally,
            message: dynamic,
            options: Options? = definedExternally
    ): Promise<dynamic>

    /**
     * Send a single message to a native application.
     */
    fun sendNativeMessage(application: String, message: dynamic): Promise<dynamic>

    /**
     * Returns information about the current browser.
     */
    fun getBrowserInfo(): Promise<BrowserInfo>

    /**
     * Returns information about the current platform.
     */
    fun getPlatformInfo(): Promise<PlatformInfo>
}
