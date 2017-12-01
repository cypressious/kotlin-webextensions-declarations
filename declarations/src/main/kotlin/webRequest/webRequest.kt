package webRequest

import browser.Event
import kotlin.js.Promise

typealias ResourceType = String

typealias OnBeforeRequestOptions = String

typealias OnBeforeSendHeadersOptions = String

typealias OnSendHeadersOptions = String

typealias OnHeadersReceivedOptions = String

typealias OnAuthRequiredOptions = String

typealias OnResponseStartedOptions = String

typealias OnBeforeRedirectOptions = String

typealias OnCompletedOptions = String

/**
 * An object describing filters to apply to webRequest events.
 */
external class RequestFilter {
    /**
     * A list of URLs or URL patterns. Requests that cannot match any of the URLs will be filtered out.
     */
    val urls: Array<String>

    /**
     * A list of request types. Requests that cannot match any of the types will be filtered out.
     */
    val types: Array<ResourceType>?

    val tabId: Int?

    val windowId: Int?
}

/**
 * An array of HTTP headers. Each header is represented as a dictionary containing the keys <code>name</code> and either <code>value</code> or <code>binaryValue</code>. */
typealias HttpHeaders = Array<HttpHeaders2>

/**
 * Returns value for event handlers that have the 'blocking' extraInfoSpec applied. Allows the event handler to modify network requests.
 */
external class BlockingResponse {
    /**
     * If true, the request is cancelled. Used in onBeforeRequest, this prevents the request from being sent.
     */
    val cancel: Boolean?

    /**
     * Only used as a response to the onBeforeRequest and onHeadersReceived events. If set, the original request is prevented from being sent/completed and is instead redirected to the given URL. Redirections to non-HTTP schemes such as data: are allowed. Redirects initiated by a redirect action use the original request method for the redirect, with one exception: If the redirect is initiated at the onHeadersReceived stage, then the redirect will be issued using the GET method.
     */
    val redirectUrl: String?

    /**
     * Only used as a response to the onBeforeSendHeaders event. If set, the request is made with these request headers instead.
     */
    val requestHeaders: HttpHeaders

    /**
     * Only used as a response to the onHeadersReceived event. If set, the server is assumed to have responded with these response headers instead. Only return <code>responseHeaders</code> if you really want to modify the headers in order to limit the number of conflicts (only one extension may modify <code>responseHeaders</code> for each request).
     */
    val responseHeaders: HttpHeaders

    /**
     * Only used as a response to the onAuthRequired event. If set, the request is made using the supplied credentials.
     */
    val authCredentials: AuthCredentials
}

/**
 * Contains data uploaded in a URL request.
 */
external class UploadData {
    /**
     * An ArrayBuffer with a copy of the data.
     */
    val bytes: Any?

    /**
     * A string with the file's path and name.
     */
    val file: String?
}

external class HttpHeaders2 {
    /**
     * Name of the HTTP header.
     */
    val name: String

    /**
     * Value of the HTTP header if it can be represented by UTF-8.
     */
    val value: String?

    /**
     * Value of the HTTP header if it cannot be represented by UTF-8, stored as individual byte values (0..255).
     */
    val binaryValue: Array<Int>?
}

/**
 * Only used as a response to the onAuthRequired event. If set, the request is made using the supplied credentials.
 */
external class AuthCredentials {
    val username: String

    val password: String
}

/**
 * If the request method is POST and the body is a sequence of key-value pairs encoded in UTF8, encoded as either multipart/form-data, or application/x-www-form-urlencoded, this dictionary is present and for each key contains the list of all values for that key. If the data is of another media type, or if it is malformed, the dictionary is not present. An example value of this dictionary is {'key': ['value1', 'value2']}.
 */
external class FormData

/**
 * Contains the HTTP request body data. Only provided if extraInfoSpec contains 'requestBody'.
 */
external class RequestBody {
    /**
     * Errors when obtaining request body data.
     */
    val error: String?

    /**
     * If the request method is POST and the body is a sequence of key-value pairs encoded in UTF8, encoded as either multipart/form-data, or application/x-www-form-urlencoded, this dictionary is present and for each key contains the list of all values for that key. If the data is of another media type, or if it is malformed, the dictionary is not present. An example value of this dictionary is {'key': ['value1', 'value2']}.
     */
    val formData: FormData

    /**
     * If the request method is PUT or POST, and the body is not already parsed in formData, then the unparsed request body elements are contained in this array.
     */
    val raw: Array<UploadData>?
}

external class Details {
    /**
     * The ID of the request. Request IDs are unique within a browser session. As a result, they could be used to relate different events of the same request.
     */
    val requestId: String

    val url: String

    /**
     * Standard HTTP method.
     */
    val method: String

    /**
     * The value 0 indicates that the request happens in the main frame; a positive value indicates the ID of a subframe in which the request happens. If the document of a (sub-)frame is loaded (<code>type</code> is <code>main_frame</code> or <code>sub_frame</code>), <code>frameId</code> indicates the ID of this frame, not the ID of the outer frame. Frame IDs are unique within a tab.
     */
    val frameId: Int

    /**
     * ID of frame that wraps the frame which sent the request. Set to -1 if no parent frame exists.
     */
    val parentFrameId: Int

    /**
     * URL of the resource that triggered this request.
     */
    val originUrl: String?

    /**
     * URL of the page into which the requested resource will be loaded.
     */
    val documentUrl: String?

    /**
     * Contains the HTTP request body data. Only provided if extraInfoSpec contains 'requestBody'.
     */
    val requestBody: RequestBody

    /**
     * The ID of the tab in which the request takes place. Set to -1 if the request isn't related to a tab.
     */
    val tabId: Int

    /**
     * How the requested resource will be used.
     */
    val type: ResourceType

    /**
     * The time when this signal is triggered, in milliseconds since the epoch.
     */
    val timeStamp: Int
}

external class Details2 {
    /**
     * The ID of the request. Request IDs are unique within a browser session. As a result, they could be used to relate different events of the same request.
     */
    val requestId: String

    val url: String

    /**
     * Standard HTTP method.
     */
    val method: String

    /**
     * The value 0 indicates that the request happens in the main frame; a positive value indicates the ID of a subframe in which the request happens. If the document of a (sub-)frame is loaded (<code>type</code> is <code>main_frame</code> or <code>sub_frame</code>), <code>frameId</code> indicates the ID of this frame, not the ID of the outer frame. Frame IDs are unique within a tab.
     */
    val frameId: Int

    /**
     * ID of frame that wraps the frame which sent the request. Set to -1 if no parent frame exists.
     */
    val parentFrameId: Int

    /**
     * URL of the resource that triggered this request.
     */
    val originUrl: String?

    /**
     * URL of the page into which the requested resource will be loaded.
     */
    val documentUrl: String?

    /**
     * The ID of the tab in which the request takes place. Set to -1 if the request isn't related to a tab.
     */
    val tabId: Int

    /**
     * How the requested resource will be used.
     */
    val type: ResourceType

    /**
     * The time when this signal is triggered, in milliseconds since the epoch.
     */
    val timeStamp: Int

    /**
     * The HTTP request headers that are going to be sent out with this request.
     */
    val requestHeaders: HttpHeaders
}

external class Details3 {
    /**
     * The ID of the request. Request IDs are unique within a browser session. As a result, they could be used to relate different events of the same request.
     */
    val requestId: String

    val url: String

    /**
     * Standard HTTP method.
     */
    val method: String

    /**
     * The value 0 indicates that the request happens in the main frame; a positive value indicates the ID of a subframe in which the request happens. If the document of a (sub-)frame is loaded (<code>type</code> is <code>main_frame</code> or <code>sub_frame</code>), <code>frameId</code> indicates the ID of this frame, not the ID of the outer frame. Frame IDs are unique within a tab.
     */
    val frameId: Int

    /**
     * ID of frame that wraps the frame which sent the request. Set to -1 if no parent frame exists.
     */
    val parentFrameId: Int

    /**
     * URL of the resource that triggered this request.
     */
    val originUrl: String?

    /**
     * URL of the page into which the requested resource will be loaded.
     */
    val documentUrl: String?

    /**
     * The ID of the tab in which the request takes place. Set to -1 if the request isn't related to a tab.
     */
    val tabId: Int

    /**
     * How the requested resource will be used.
     */
    val type: ResourceType

    /**
     * The time when this signal is triggered, in milliseconds since the epoch.
     */
    val timeStamp: Int

    /**
     * The HTTP request headers that have been sent out with this request.
     */
    val requestHeaders: HttpHeaders
}

external class Details4 {
    /**
     * The ID of the request. Request IDs are unique within a browser session. As a result, they could be used to relate different events of the same request.
     */
    val requestId: String

    val url: String

    /**
     * Standard HTTP method.
     */
    val method: String

    /**
     * The value 0 indicates that the request happens in the main frame; a positive value indicates the ID of a subframe in which the request happens. If the document of a (sub-)frame is loaded (<code>type</code> is <code>main_frame</code> or <code>sub_frame</code>), <code>frameId</code> indicates the ID of this frame, not the ID of the outer frame. Frame IDs are unique within a tab.
     */
    val frameId: Int

    /**
     * ID of frame that wraps the frame which sent the request. Set to -1 if no parent frame exists.
     */
    val parentFrameId: Int

    /**
     * URL of the resource that triggered this request.
     */
    val originUrl: String?

    /**
     * URL of the page into which the requested resource will be loaded.
     */
    val documentUrl: String?

    /**
     * The ID of the tab in which the request takes place. Set to -1 if the request isn't related to a tab.
     */
    val tabId: Int

    /**
     * How the requested resource will be used.
     */
    val type: ResourceType

    /**
     * The time when this signal is triggered, in milliseconds since the epoch.
     */
    val timeStamp: Int

    /**
     * HTTP status line of the response or the 'HTTP/0.9 200 OK' string for HTTP/0.9 responses (i.e., responses that lack a status line).
     */
    val statusLine: String

    /**
     * The HTTP response headers that have been received with this response.
     */
    val responseHeaders: HttpHeaders

    /**
     * Standard HTTP status code returned by the server.
     */
    val statusCode: Int
}

/**
 * The server requesting authentication.
 */
external class Challenger {
    val host: String

    val port: Int
}

external class Details5 {
    /**
     * The ID of the request. Request IDs are unique within a browser session. As a result, they could be used to relate different events of the same request.
     */
    val requestId: String

    val url: String

    /**
     * Standard HTTP method.
     */
    val method: String

    /**
     * The value 0 indicates that the request happens in the main frame; a positive value indicates the ID of a subframe in which the request happens. If the document of a (sub-)frame is loaded (<code>type</code> is <code>main_frame</code> or <code>sub_frame</code>), <code>frameId</code> indicates the ID of this frame, not the ID of the outer frame. Frame IDs are unique within a tab.
     */
    val frameId: Int

    /**
     * ID of frame that wraps the frame which sent the request. Set to -1 if no parent frame exists.
     */
    val parentFrameId: Int

    /**
     * URL of the resource that triggered this request.
     */
    val originUrl: String?

    /**
     * URL of the page into which the requested resource will be loaded.
     */
    val documentUrl: String?

    /**
     * The ID of the tab in which the request takes place. Set to -1 if the request isn't related to a tab.
     */
    val tabId: Int

    /**
     * How the requested resource will be used.
     */
    val type: ResourceType

    /**
     * The time when this signal is triggered, in milliseconds since the epoch.
     */
    val timeStamp: Int

    /**
     * The authentication scheme, e.g. Basic or Digest.
     */
    val scheme: String

    /**
     * The authentication realm provided by the server, if there is one.
     */
    val realm: String?

    /**
     * The server requesting authentication.
     */
    val challenger: Challenger

    /**
     * True for Proxy-Authenticate, false for WWW-Authenticate.
     */
    val isProxy: Boolean

    /**
     * The HTTP response headers that were received along with this response.
     */
    val responseHeaders: HttpHeaders

    /**
     * HTTP status line of the response or the 'HTTP/0.9 200 OK' string for HTTP/0.9 responses (i.e., responses that lack a status line) or an empty string if there are no headers.
     */
    val statusLine: String

    /**
     * Standard HTTP status code returned by the server.
     */
    val statusCode: Int
}

typealias Callback = Any

external class Details6 {
    /**
     * The ID of the request. Request IDs are unique within a browser session. As a result, they could be used to relate different events of the same request.
     */
    val requestId: String

    val url: String

    /**
     * Standard HTTP method.
     */
    val method: String

    /**
     * The value 0 indicates that the request happens in the main frame; a positive value indicates the ID of a subframe in which the request happens. If the document of a (sub-)frame is loaded (<code>type</code> is <code>main_frame</code> or <code>sub_frame</code>), <code>frameId</code> indicates the ID of this frame, not the ID of the outer frame. Frame IDs are unique within a tab.
     */
    val frameId: Int

    /**
     * ID of frame that wraps the frame which sent the request. Set to -1 if no parent frame exists.
     */
    val parentFrameId: Int

    /**
     * URL of the resource that triggered this request.
     */
    val originUrl: String?

    /**
     * URL of the page into which the requested resource will be loaded.
     */
    val documentUrl: String?

    /**
     * The ID of the tab in which the request takes place. Set to -1 if the request isn't related to a tab.
     */
    val tabId: Int

    /**
     * How the requested resource will be used.
     */
    val type: ResourceType

    /**
     * The time when this signal is triggered, in milliseconds since the epoch.
     */
    val timeStamp: Int

    /**
     * The server IP address that the request was actually sent to. Note that it may be a literal IPv6 address.
     */
    val ip: String?

    /**
     * Indicates if this response was fetched from disk cache.
     */
    val fromCache: Boolean

    /**
     * Standard HTTP status code returned by the server.
     */
    val statusCode: Int

    /**
     * The HTTP response headers that were received along with this response.
     */
    val responseHeaders: HttpHeaders

    /**
     * HTTP status line of the response or the 'HTTP/0.9 200 OK' string for HTTP/0.9 responses (i.e., responses that lack a status line) or an empty string if there are no headers.
     */
    val statusLine: String
}

external class Details7 {
    /**
     * The ID of the request. Request IDs are unique within a browser session. As a result, they could be used to relate different events of the same request.
     */
    val requestId: String

    val url: String

    /**
     * Standard HTTP method.
     */
    val method: String

    /**
     * The value 0 indicates that the request happens in the main frame; a positive value indicates the ID of a subframe in which the request happens. If the document of a (sub-)frame is loaded (<code>type</code> is <code>main_frame</code> or <code>sub_frame</code>), <code>frameId</code> indicates the ID of this frame, not the ID of the outer frame. Frame IDs are unique within a tab.
     */
    val frameId: Int

    /**
     * ID of frame that wraps the frame which sent the request. Set to -1 if no parent frame exists.
     */
    val parentFrameId: Int

    /**
     * URL of the resource that triggered this request.
     */
    val originUrl: String?

    /**
     * URL of the page into which the requested resource will be loaded.
     */
    val documentUrl: String?

    /**
     * The ID of the tab in which the request takes place. Set to -1 if the request isn't related to a tab.
     */
    val tabId: Int

    /**
     * How the requested resource will be used.
     */
    val type: ResourceType

    /**
     * The time when this signal is triggered, in milliseconds since the epoch.
     */
    val timeStamp: Int

    /**
     * The server IP address that the request was actually sent to. Note that it may be a literal IPv6 address.
     */
    val ip: String?

    /**
     * Indicates if this response was fetched from disk cache.
     */
    val fromCache: Boolean

    /**
     * Standard HTTP status code returned by the server.
     */
    val statusCode: Int

    /**
     * The new URL.
     */
    val redirectUrl: String

    /**
     * The HTTP response headers that were received along with this redirect.
     */
    val responseHeaders: HttpHeaders

    /**
     * HTTP status line of the response or the 'HTTP/0.9 200 OK' string for HTTP/0.9 responses (i.e., responses that lack a status line) or an empty string if there are no headers.
     */
    val statusLine: String
}

external class Details8 {
    /**
     * The ID of the request. Request IDs are unique within a browser session. As a result, they could be used to relate different events of the same request.
     */
    val requestId: String

    val url: String

    /**
     * Standard HTTP method.
     */
    val method: String

    /**
     * The value 0 indicates that the request happens in the main frame; a positive value indicates the ID of a subframe in which the request happens. If the document of a (sub-)frame is loaded (<code>type</code> is <code>main_frame</code> or <code>sub_frame</code>), <code>frameId</code> indicates the ID of this frame, not the ID of the outer frame. Frame IDs are unique within a tab.
     */
    val frameId: Int

    /**
     * ID of frame that wraps the frame which sent the request. Set to -1 if no parent frame exists.
     */
    val parentFrameId: Int

    /**
     * URL of the resource that triggered this request.
     */
    val originUrl: String?

    /**
     * URL of the page into which the requested resource will be loaded.
     */
    val documentUrl: String?

    /**
     * The ID of the tab in which the request takes place. Set to -1 if the request isn't related to a tab.
     */
    val tabId: Int

    /**
     * How the requested resource will be used.
     */
    val type: ResourceType

    /**
     * The time when this signal is triggered, in milliseconds since the epoch.
     */
    val timeStamp: Int

    /**
     * The server IP address that the request was actually sent to. Note that it may be a literal IPv6 address.
     */
    val ip: String?

    /**
     * Indicates if this response was fetched from disk cache.
     */
    val fromCache: Boolean

    /**
     * Standard HTTP status code returned by the server.
     */
    val statusCode: Int

    /**
     * The HTTP response headers that were received along with this response.
     */
    val responseHeaders: HttpHeaders

    /**
     * HTTP status line of the response or the 'HTTP/0.9 200 OK' string for HTTP/0.9 responses (i.e., responses that lack a status line) or an empty string if there are no headers.
     */
    val statusLine: String
}

external class Details9 {
    /**
     * The ID of the request. Request IDs are unique within a browser session. As a result, they could be used to relate different events of the same request.
     */
    val requestId: String

    val url: String

    /**
     * Standard HTTP method.
     */
    val method: String

    /**
     * The value 0 indicates that the request happens in the main frame; a positive value indicates the ID of a subframe in which the request happens. If the document of a (sub-)frame is loaded (<code>type</code> is <code>main_frame</code> or <code>sub_frame</code>), <code>frameId</code> indicates the ID of this frame, not the ID of the outer frame. Frame IDs are unique within a tab.
     */
    val frameId: Int

    /**
     * ID of frame that wraps the frame which sent the request. Set to -1 if no parent frame exists.
     */
    val parentFrameId: Int

    /**
     * URL of the resource that triggered this request.
     */
    val originUrl: String?

    /**
     * URL of the page into which the requested resource will be loaded.
     */
    val documentUrl: String?

    /**
     * The ID of the tab in which the request takes place. Set to -1 if the request isn't related to a tab.
     */
    val tabId: Int

    /**
     * How the requested resource will be used.
     */
    val type: ResourceType

    /**
     * The time when this signal is triggered, in milliseconds since the epoch.
     */
    val timeStamp: Int

    /**
     * The server IP address that the request was actually sent to. Note that it may be a literal IPv6 address.
     */
    val ip: String?

    /**
     * Indicates if this response was fetched from disk cache.
     */
    val fromCache: Boolean

    /**
     * The error description. This string is <em>not</em> guaranteed to remain backwards compatible between releases. You must not parse and act based upon its content.
     */
    val error: String
}

external class WebRequestNamespace {
    val onBeforeRequest: Event<(details: Details) -> Unit>

    val onBeforeSendHeaders: Event<(details: Details2) -> Unit>

    val onSendHeaders: Event<(details: Details3) -> Unit>

    val onHeadersReceived: Event<(details: Details4) -> Unit>

    val onAuthRequired: Event<(details: Details5, callback: Callback) -> Unit>

    val onResponseStarted: Event<(details: Details6) -> Unit>

    val onBeforeRedirect: Event<(details: Details7) -> Unit>

    val onCompleted: Event<(details: Details8) -> Unit>

    val onErrorOccurred: Event<(details: Details9) -> Unit>

    /**
     * Needs to be called when the behavior of the webRequest handlers has changed to prevent incorrect handling due to caching. This function call is expensive. Don't call it often.
     */
    fun handlerBehaviorChanged(): Promise<Any>

    /**
     * ...
     */
    fun filterResponseData(requestId: String)
}
