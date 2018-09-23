package runtime

import events.Event
import kotlin.Suppress
import kotlin.js.Promise
import tabs.Tab

/**
 * An object which allows two way communication with other pages.
 * @param sender This property will <b>only</b> be present on ports passed to onConnect/onConnectExternal listeners.
 */
@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Port(
    var name: String,
    var disconnect: () -> Unit,
    var onDisconnect: Event,
    var onMessage: Event,
    var postMessage: (Any) -> Unit,
    var sender: MessageSender? = null
) {
    inline operator fun get(key: String): dynamic = asDynamic()[key]
    inline operator fun set(key: String, value: dynamic) {
        asDynamic()[key] = value
    }
}

/**
 * An object containing information about the script context that sent a message or request.
 * @param tab The $(ref:tabs.Tab) which opened the connection, if any. This property will <strong>only</strong> be present when the connection was opened from a tab (including content scripts), and <strong>only</strong> if the receiver is an extension, not an app.
 * @param frameId The $(topic:frame_ids)[frame] that opened the connection. 0 for top-level frames, positive for child frames. This will only be set when <code>tab</code> is set.
 * @param id The ID of the extension or app that opened the connection, if any.
 * @param url The URL of the page or frame that opened the connection. If the sender is in an iframe, it will be iframe's URL not the URL of the page which hosts it.
 */
class MessageSender(
    var tab: Tab? = null,
    var frameId: Int? = null,
    var id: String? = null,
    var url: String? = null
)

/**
 * The operating system the browser is running on. */
typealias PlatformOs = String

/**
 * The machine's processor architecture. */
typealias PlatformArch = String

/**
 * An object containing information about the current platform.
 * @param os The operating system the browser is running on.
 * @param arch The machine's processor architecture.
 */
class PlatformInfo(
    var os: PlatformOs,
    var arch: PlatformArch
)

/**
 * An object containing information about the current browser.
 * @param name The name of the browser, for example 'Firefox'.
 * @param vendor The name of the browser vendor, for example 'Mozilla'.
 * @param version The browser's version, for example '42.0.0' or '0.8.1pre'.
 * @param buildID The browser's build ID/date, for example '20160101'.
 */
class BrowserInfo(
    var name: String,
    var vendor: String,
    var version: String,
    var buildID: String
)

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
 * @param message Details about the error which occurred.
 */
@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class LastError(
    var message: String? = null
) {
    inline operator fun get(key: String): dynamic = asDynamic()[key]
    inline operator fun set(key: String, value: dynamic) {
        asDynamic()[key] = value
    }
}

/**
 * The JavaScript 'window' object for the background page.
 */
@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class BackgroundPage() {
    inline operator fun get(key: String): dynamic = asDynamic()[key]
    inline operator fun set(key: String, value: dynamic) {
        asDynamic()[key] = value
    }
}

/**
 * The manifest details.
 */
@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class GetManifestResult() {
    inline operator fun get(key: String): dynamic = asDynamic()[key]
    inline operator fun set(key: String, value: dynamic) {
        asDynamic()[key] = value
    }
}

/**
 * If an update is available, this contains more information about the available update.
 * @param version The version of the available update.
 */
class Details(
    var version: String
)

/**
 * @param name Will be passed into onConnect for processes that are listening for the connection event.
 * @param includeTlsChannelId Whether the TLS channel ID will be passed into onConnectExternal for processes that are listening for the connection event.
 */
class ConnectInfo(
    var name: String? = null,
    var includeTlsChannelId: Boolean? = null
)

/**
 * @param toProxyScript If true, the message will be directed to the extension's proxy sandbox.
 */
class Options(
    var toProxyScript: Boolean? = null
)

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class DirectoryEntry() {
    inline operator fun get(key: String): dynamic = asDynamic()[key]
    inline operator fun set(key: String, value: dynamic) {
        asDynamic()[key] = value
    }
}

/**
 * @param reason The reason that this event is being dispatched.
 * @param previousVersion Indicates the previous version of the extension, which has just been updated. This is present only if 'reason' is 'update'.
 * @param temporary Indicates whether the addon is installed as a temporary extension.
 */
class Details2(
    var reason: OnInstalledReason,
    var previousVersion: String? = null,
    var temporary: Boolean
)

/**
 * The manifest details of the available update.
 * @param version The version number of the available update.
 */
@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Details3(
    var version: String
) {
    inline operator fun get(key: String): dynamic = asDynamic()[key]
    inline operator fun set(key: String, value: dynamic) {
        asDynamic()[key] = value
    }
}

external class RuntimeNamespace {
    /**
     * Fired when a profile that has this extension installed first starts up. This event is not fired for incognito profiles.
     */
    val onStartup: webextensions.Event<() -> Unit>

    /**
     * Fired when the extension is first installed, when the extension is updated to a new version, and when the browser is updated to a new version.
     *
     * @param details null */
    val onInstalled: webextensions.Event<(details: Details2) -> Unit>

    /**
     * Fired when an update is available, but isn't installed immediately because the app is currently running. If you do nothing, the update will be installed the next time the background page gets unloaded, if you want it to be installed sooner you can explicitly call $(ref:runtime.reload). If your extension is using a persistent background page, the background page of course never gets unloaded, so unless you call $(ref:runtime.reload) manually in response to this event the update will not get installed until the next time the browser itself restarts. If no handlers are listening for this event, and your extension has a persistent background page, it behaves as if $(ref:runtime.reload) is called in response to this event.
     *
     * @param details The manifest details of the available update. */
    val onUpdateAvailable: webextensions.Event<(details: Details3) -> Unit>

    /**
     * Fired when a connection is made from either an extension process or a content script.
     *
     * @param port null */
    val onConnect: webextensions.Event<(port: Port) -> Unit>

    /**
     * Fired when a connection is made from another extension.
     *
     * @param port null */
    val onConnectExternal: webextensions.Event<(port: Port) -> Unit>

    /**
     * Fired when a message is sent from either an extension process or a content script.
     *
     * @param message The message sent by the calling script.
     * @param sender null
     * @param sendResponse Function to call (at most once) when you have a response. The argument should be any JSON-ifiable object. If you have more than one <code>onMessage</code> listener in the same document, then only one may send a response. This function becomes invalid when the event listener returns, unless you return true from the event listener to indicate you wish to send a response asynchronously (this will keep the message channel open to the other end until <code>sendResponse</code> is called). */
    val onMessage: webextensions.Event<(
        message: dynamic,
        sender: MessageSender,
        sendResponse: () -> Unit
    ) -> Unit>

    /**
     * Fired when a message is sent from another extension/app. Cannot be used in a content script.
     *
     * @param message The message sent by the calling script.
     * @param sender null
     * @param sendResponse Function to call (at most once) when you have a response. The argument should be any JSON-ifiable object. If you have more than one <code>onMessage</code> listener in the same document, then only one may send a response. This function becomes invalid when the event listener returns, unless you return true from the event listener to indicate you wish to send a response asynchronously (this will keep the message channel open to the other end until <code>sendResponse</code> is called). */
    val onMessageExternal: webextensions.Event<(
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
