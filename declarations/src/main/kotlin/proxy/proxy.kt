package proxy

import kotlin.Deprecated
import kotlin.js.Promise
import webRequest.HttpHeaders
import webRequest.ResourceType
import webextensions.Event

/**
 * An object which describes proxy settings.
 * @param proxyType The type of proxy to use.
 * @param http The address of the http proxy, can include a port.
 * @param httpProxyAll Use the http proxy server for all protocols.
 * @param ftp The address of the ftp proxy, can include a port.
 * @param ssl The address of the ssl proxy, can include a port.
 * @param socks The address of the socks proxy, can include a port.
 * @param socksVersion The version of the socks proxy.
 * @param passthrough A list of hosts which should not be proxied.
 * @param autoConfigUrl A URL to use to configure the proxy.
 * @param autoLogin Do not prompt for authentication if password is saved.
 * @param proxyDNS Proxy DNS when using SOCKS v5.
 */
class ProxyConfig(
    var proxyType: String? = null,
    var http: String? = null,
    var httpProxyAll: Boolean? = null,
    var ftp: String? = null,
    var ssl: String? = null,
    var socks: String? = null,
    var socksVersion: Int? = null,
    var passthrough: String? = null,
    var autoConfigUrl: String? = null,
    var autoLogin: Boolean? = null,
    var proxyDNS: Boolean? = null
)

/**
 * @param requestId The ID of the request. Request IDs are unique within a browser session. As a result, they could be used to relate different events of the same request.
 * @param method Standard HTTP method.
 * @param frameId The value 0 indicates that the request happens in the main frame; a positive value indicates the ID of a subframe in which the request happens. If the document of a (sub-)frame is loaded (<code>type</code> is <code>main_frame</code> or <code>sub_frame</code>), <code>frameId</code> indicates the ID of this frame, not the ID of the outer frame. Frame IDs are unique within a tab.
 * @param parentFrameId ID of frame that wraps the frame which sent the request. Set to -1 if no parent frame exists.
 * @param originUrl URL of the resource that triggered this request.
 * @param documentUrl URL of the page into which the requested resource will be loaded.
 * @param tabId The ID of the tab in which the request takes place. Set to -1 if the request isn't related to a tab.
 * @param type How the requested resource will be used.
 * @param timeStamp The time when this signal is triggered, in milliseconds since the epoch.
 * @param ip The server IP address that the request was actually sent to. Note that it may be a literal IPv6 address.
 * @param fromCache Indicates if this response was fetched from disk cache.
 * @param requestHeaders The HTTP request headers that are going to be sent out with this request.
 */
class Details(
    var requestId: String,
    var url: String,
    var method: String,
    var frameId: Int,
    var parentFrameId: Int,
    var originUrl: String? = null,
    var documentUrl: String? = null,
    var tabId: Int,
    var type: ResourceType,
    var timeStamp: Float,
    var ip: String? = null,
    var fromCache: Boolean,
    var requestHeaders: HttpHeaders? = null
)

typealias Error = Any

typealias Error2 = Any

external class ProxyNamespace {
    /**
     * Fired when proxy data is needed for a request.
     *
     * @param details null */
    val onRequest: Event<(details: Details) -> Unit>

    /**
     * Notifies about proxy script errors.
     *
     * @param error null */
    val onError: Event<(error: Error) -> Unit>

    /**
     * Please use $(ref:proxy.onError).
     *
     * @param error null */
    val onProxyError: Event<(error: Error2) -> Unit>

    /**
     * Registers the proxy script for the extension.
     */
    fun register(url: String): Promise<Any>

    /**
     * Unregisters the proxy script for the extension.
     */
    fun unregister(): Promise<Any>

    /**
     * Registers the proxy script for the extension.
     */
    @Deprecated("Please use $(ref:proxy.register)")
    fun registerProxyScript(url: String): Promise<Any>
}
