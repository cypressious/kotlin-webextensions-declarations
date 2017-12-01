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
    var urls: Array<String>

    /**
     * A list of request types. Requests that cannot match any of the types will be filtered out.
     */
    var types: Array<ResourceType>

    var tabId: Int?

    var windowId: Int?
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
    var cancel: Boolean?

    /**
     * Only used as a response to the onBeforeRequest and onHeadersReceived events. If set, the original request is prevented from being sent/completed and is instead redirected to the given URL. Redirections to non-HTTP schemes such as data: are allowed. Redirects initiated by a redirect action use the original request method for the redirect, with one exception: If the redirect is initiated at the onHeadersReceived stage, then the redirect will be issued using the GET method.
     */
    var redirectUrl: String?

    /**
     * Only used as a response to the onBeforeSendHeaders event. If set, the request is made with these request headers instead.
     */
    var requestHeaders: HttpHeaders?

    /**
     * Only used as a response to the onHeadersReceived event. If set, the server is assumed to have responded with these response headers instead. Only return <code>responseHeaders</code> if you really want to modify the headers in order to limit the number of conflicts (only one extension may modify <code>responseHeaders</code> for each request).
     */
    var responseHeaders: HttpHeaders?

    /**
     * Only used as a response to the onAuthRequired event. If set, the request is made using the supplied credentials.
     */
    var authCredentials: AuthCredentials?
}

/**
 * Contains data uploaded in a URL request.
 */
external class UploadData {
    /**
     * An ArrayBuffer with a copy of the data.
     */
    var bytes: dynamic

    /**
     * A string with the file's path and name.
     */
    var file: String?
}

external class HttpHeaders2 {
    /**
     * Name of the HTTP header.
     */
    var name: String

    /**
     * Value of the HTTP header if it can be represented by UTF-8.
     */
    var value: String?

    /**
     * Value of the HTTP header if it cannot be represented by UTF-8, stored as individual byte values (0..255).
     */
    var binaryValue: Array<Int>
}

/**
 * Only used as a response to the onAuthRequired event. If set, the request is made using the supplied credentials.
 */
external class AuthCredentials {
    var username: String

    var password: String
}

typealias FilterResponseDataResult = Any

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
    var error: String?

    /**
     * If the request method is POST and the body is a sequence of key-value pairs encoded in UTF8, encoded as either multipart/form-data, or application/x-www-form-urlencoded, this dictionary is present and for each key contains the list of all values for that key. If the data is of another media type, or if it is malformed, the dictionary is not present. An example value of this dictionary is {'key': ['value1', 'value2']}.
     */
    var formData: FormData?

    /**
     * If the request method is PUT or POST, and the body is not already parsed in formData, then the unparsed request body elements are contained in this array.
     */
    var raw: Array<UploadData>
}

external class Details {
    /**
     * The ID of the request. Request IDs are unique within a browser session. As a result, they could be used to relate different events of the same request.
     */
    var requestId: String

    var url: String

    /**
     * Standard HTTP method.
     */
    var method: String

    /**
     * The value 0 indicates that the request happens in the main frame; a positive value indicates the ID of a subframe in which the request happens. If the document of a (sub-)frame is loaded (<code>type</code> is <code>main_frame</code> or <code>sub_frame</code>), <code>frameId</code> indicates the ID of this frame, not the ID of the outer frame. Frame IDs are unique within a tab.
     */
    var frameId: Int

    /**
     * ID of frame that wraps the frame which sent the request. Set to -1 if no parent frame exists.
     */
    var parentFrameId: Int

    /**
     * URL of the resource that triggered this request.
     */
    var originUrl: String?

    /**
     * URL of the page into which the requested resource will be loaded.
     */
    var documentUrl: String?

    /**
     * Contains the HTTP request body data. Only provided if extraInfoSpec contains 'requestBody'.
     */
    var requestBody: RequestBody?

    /**
     * The ID of the tab in which the request takes place. Set to -1 if the request isn't related to a tab.
     */
    var tabId: Int

    /**
     * How the requested resource will be used.
     */
    var type: ResourceType

    /**
     * The time when this signal is triggered, in milliseconds since the epoch.
     */
    var timeStamp: Int
}

external class Details2 {
    /**
     * The ID of the request. Request IDs are unique within a browser session. As a result, they could be used to relate different events of the same request.
     */
    var requestId: String

    var url: String

    /**
     * Standard HTTP method.
     */
    var method: String

    /**
     * The value 0 indicates that the request happens in the main frame; a positive value indicates the ID of a subframe in which the request happens. If the document of a (sub-)frame is loaded (<code>type</code> is <code>main_frame</code> or <code>sub_frame</code>), <code>frameId</code> indicates the ID of this frame, not the ID of the outer frame. Frame IDs are unique within a tab.
     */
    var frameId: Int

    /**
     * ID of frame that wraps the frame which sent the request. Set to -1 if no parent frame exists.
     */
    var parentFrameId: Int

    /**
     * URL of the resource that triggered this request.
     */
    var originUrl: String?

    /**
     * URL of the page into which the requested resource will be loaded.
     */
    var documentUrl: String?

    /**
     * The ID of the tab in which the request takes place. Set to -1 if the request isn't related to a tab.
     */
    var tabId: Int

    /**
     * How the requested resource will be used.
     */
    var type: ResourceType

    /**
     * The time when this signal is triggered, in milliseconds since the epoch.
     */
    var timeStamp: Int

    /**
     * The HTTP request headers that are going to be sent out with this request.
     */
    var requestHeaders: HttpHeaders?
}

external class Details3 {
    /**
     * The ID of the request. Request IDs are unique within a browser session. As a result, they could be used to relate different events of the same request.
     */
    var requestId: String

    var url: String

    /**
     * Standard HTTP method.
     */
    var method: String

    /**
     * The value 0 indicates that the request happens in the main frame; a positive value indicates the ID of a subframe in which the request happens. If the document of a (sub-)frame is loaded (<code>type</code> is <code>main_frame</code> or <code>sub_frame</code>), <code>frameId</code> indicates the ID of this frame, not the ID of the outer frame. Frame IDs are unique within a tab.
     */
    var frameId: Int

    /**
     * ID of frame that wraps the frame which sent the request. Set to -1 if no parent frame exists.
     */
    var parentFrameId: Int

    /**
     * URL of the resource that triggered this request.
     */
    var originUrl: String?

    /**
     * URL of the page into which the requested resource will be loaded.
     */
    var documentUrl: String?

    /**
     * The ID of the tab in which the request takes place. Set to -1 if the request isn't related to a tab.
     */
    var tabId: Int

    /**
     * How the requested resource will be used.
     */
    var type: ResourceType

    /**
     * The time when this signal is triggered, in milliseconds since the epoch.
     */
    var timeStamp: Int

    /**
     * The HTTP request headers that have been sent out with this request.
     */
    var requestHeaders: HttpHeaders?
}

external class Details4 {
    /**
     * The ID of the request. Request IDs are unique within a browser session. As a result, they could be used to relate different events of the same request.
     */
    var requestId: String

    var url: String

    /**
     * Standard HTTP method.
     */
    var method: String

    /**
     * The value 0 indicates that the request happens in the main frame; a positive value indicates the ID of a subframe in which the request happens. If the document of a (sub-)frame is loaded (<code>type</code> is <code>main_frame</code> or <code>sub_frame</code>), <code>frameId</code> indicates the ID of this frame, not the ID of the outer frame. Frame IDs are unique within a tab.
     */
    var frameId: Int

    /**
     * ID of frame that wraps the frame which sent the request. Set to -1 if no parent frame exists.
     */
    var parentFrameId: Int

    /**
     * URL of the resource that triggered this request.
     */
    var originUrl: String?

    /**
     * URL of the page into which the requested resource will be loaded.
     */
    var documentUrl: String?

    /**
     * The ID of the tab in which the request takes place. Set to -1 if the request isn't related to a tab.
     */
    var tabId: Int

    /**
     * How the requested resource will be used.
     */
    var type: ResourceType

    /**
     * The time when this signal is triggered, in milliseconds since the epoch.
     */
    var timeStamp: Int

    /**
     * HTTP status line of the response or the 'HTTP/0.9 200 OK' string for HTTP/0.9 responses (i.e., responses that lack a status line).
     */
    var statusLine: String

    /**
     * The HTTP response headers that have been received with this response.
     */
    var responseHeaders: HttpHeaders?

    /**
     * Standard HTTP status code returned by the server.
     */
    var statusCode: Int
}

/**
 * The server requesting authentication.
 */
external class Challenger {
    var host: String

    var port: Int
}

external class Details5 {
    /**
     * The ID of the request. Request IDs are unique within a browser session. As a result, they could be used to relate different events of the same request.
     */
    var requestId: String

    var url: String

    /**
     * Standard HTTP method.
     */
    var method: String

    /**
     * The value 0 indicates that the request happens in the main frame; a positive value indicates the ID of a subframe in which the request happens. If the document of a (sub-)frame is loaded (<code>type</code> is <code>main_frame</code> or <code>sub_frame</code>), <code>frameId</code> indicates the ID of this frame, not the ID of the outer frame. Frame IDs are unique within a tab.
     */
    var frameId: Int

    /**
     * ID of frame that wraps the frame which sent the request. Set to -1 if no parent frame exists.
     */
    var parentFrameId: Int

    /**
     * URL of the resource that triggered this request.
     */
    var originUrl: String?

    /**
     * URL of the page into which the requested resource will be loaded.
     */
    var documentUrl: String?

    /**
     * The ID of the tab in which the request takes place. Set to -1 if the request isn't related to a tab.
     */
    var tabId: Int

    /**
     * How the requested resource will be used.
     */
    var type: ResourceType

    /**
     * The time when this signal is triggered, in milliseconds since the epoch.
     */
    var timeStamp: Int

    /**
     * The authentication scheme, e.g. Basic or Digest.
     */
    var scheme: String

    /**
     * The authentication realm provided by the server, if there is one.
     */
    var realm: String?

    /**
     * The server requesting authentication.
     */
    var challenger: Challenger

    /**
     * True for Proxy-Authenticate, false for WWW-Authenticate.
     */
    var isProxy: Boolean

    /**
     * The HTTP response headers that were received along with this response.
     */
    var responseHeaders: HttpHeaders?

    /**
     * HTTP status line of the response or the 'HTTP/0.9 200 OK' string for HTTP/0.9 responses (i.e., responses that lack a status line) or an empty string if there are no headers.
     */
    var statusLine: String

    /**
     * Standard HTTP status code returned by the server.
     */
    var statusCode: Int
}

typealias Callback = Any

external class Details6 {
    /**
     * The ID of the request. Request IDs are unique within a browser session. As a result, they could be used to relate different events of the same request.
     */
    var requestId: String

    var url: String

    /**
     * Standard HTTP method.
     */
    var method: String

    /**
     * The value 0 indicates that the request happens in the main frame; a positive value indicates the ID of a subframe in which the request happens. If the document of a (sub-)frame is loaded (<code>type</code> is <code>main_frame</code> or <code>sub_frame</code>), <code>frameId</code> indicates the ID of this frame, not the ID of the outer frame. Frame IDs are unique within a tab.
     */
    var frameId: Int

    /**
     * ID of frame that wraps the frame which sent the request. Set to -1 if no parent frame exists.
     */
    var parentFrameId: Int

    /**
     * URL of the resource that triggered this request.
     */
    var originUrl: String?

    /**
     * URL of the page into which the requested resource will be loaded.
     */
    var documentUrl: String?

    /**
     * The ID of the tab in which the request takes place. Set to -1 if the request isn't related to a tab.
     */
    var tabId: Int

    /**
     * How the requested resource will be used.
     */
    var type: ResourceType

    /**
     * The time when this signal is triggered, in milliseconds since the epoch.
     */
    var timeStamp: Int

    /**
     * The server IP address that the request was actually sent to. Note that it may be a literal IPv6 address.
     */
    var ip: String?

    /**
     * Indicates if this response was fetched from disk cache.
     */
    var fromCache: Boolean

    /**
     * Standard HTTP status code returned by the server.
     */
    var statusCode: Int

    /**
     * The HTTP response headers that were received along with this response.
     */
    var responseHeaders: HttpHeaders?

    /**
     * HTTP status line of the response or the 'HTTP/0.9 200 OK' string for HTTP/0.9 responses (i.e., responses that lack a status line) or an empty string if there are no headers.
     */
    var statusLine: String
}

external class Details7 {
    /**
     * The ID of the request. Request IDs are unique within a browser session. As a result, they could be used to relate different events of the same request.
     */
    var requestId: String

    var url: String

    /**
     * Standard HTTP method.
     */
    var method: String

    /**
     * The value 0 indicates that the request happens in the main frame; a positive value indicates the ID of a subframe in which the request happens. If the document of a (sub-)frame is loaded (<code>type</code> is <code>main_frame</code> or <code>sub_frame</code>), <code>frameId</code> indicates the ID of this frame, not the ID of the outer frame. Frame IDs are unique within a tab.
     */
    var frameId: Int

    /**
     * ID of frame that wraps the frame which sent the request. Set to -1 if no parent frame exists.
     */
    var parentFrameId: Int

    /**
     * URL of the resource that triggered this request.
     */
    var originUrl: String?

    /**
     * URL of the page into which the requested resource will be loaded.
     */
    var documentUrl: String?

    /**
     * The ID of the tab in which the request takes place. Set to -1 if the request isn't related to a tab.
     */
    var tabId: Int

    /**
     * How the requested resource will be used.
     */
    var type: ResourceType

    /**
     * The time when this signal is triggered, in milliseconds since the epoch.
     */
    var timeStamp: Int

    /**
     * The server IP address that the request was actually sent to. Note that it may be a literal IPv6 address.
     */
    var ip: String?

    /**
     * Indicates if this response was fetched from disk cache.
     */
    var fromCache: Boolean

    /**
     * Standard HTTP status code returned by the server.
     */
    var statusCode: Int

    /**
     * The new URL.
     */
    var redirectUrl: String

    /**
     * The HTTP response headers that were received along with this redirect.
     */
    var responseHeaders: HttpHeaders?

    /**
     * HTTP status line of the response or the 'HTTP/0.9 200 OK' string for HTTP/0.9 responses (i.e., responses that lack a status line) or an empty string if there are no headers.
     */
    var statusLine: String
}

external class Details8 {
    /**
     * The ID of the request. Request IDs are unique within a browser session. As a result, they could be used to relate different events of the same request.
     */
    var requestId: String

    var url: String

    /**
     * Standard HTTP method.
     */
    var method: String

    /**
     * The value 0 indicates that the request happens in the main frame; a positive value indicates the ID of a subframe in which the request happens. If the document of a (sub-)frame is loaded (<code>type</code> is <code>main_frame</code> or <code>sub_frame</code>), <code>frameId</code> indicates the ID of this frame, not the ID of the outer frame. Frame IDs are unique within a tab.
     */
    var frameId: Int

    /**
     * ID of frame that wraps the frame which sent the request. Set to -1 if no parent frame exists.
     */
    var parentFrameId: Int

    /**
     * URL of the resource that triggered this request.
     */
    var originUrl: String?

    /**
     * URL of the page into which the requested resource will be loaded.
     */
    var documentUrl: String?

    /**
     * The ID of the tab in which the request takes place. Set to -1 if the request isn't related to a tab.
     */
    var tabId: Int

    /**
     * How the requested resource will be used.
     */
    var type: ResourceType

    /**
     * The time when this signal is triggered, in milliseconds since the epoch.
     */
    var timeStamp: Int

    /**
     * The server IP address that the request was actually sent to. Note that it may be a literal IPv6 address.
     */
    var ip: String?

    /**
     * Indicates if this response was fetched from disk cache.
     */
    var fromCache: Boolean

    /**
     * Standard HTTP status code returned by the server.
     */
    var statusCode: Int

    /**
     * The HTTP response headers that were received along with this response.
     */
    var responseHeaders: HttpHeaders?

    /**
     * HTTP status line of the response or the 'HTTP/0.9 200 OK' string for HTTP/0.9 responses (i.e., responses that lack a status line) or an empty string if there are no headers.
     */
    var statusLine: String
}

external class Details9 {
    /**
     * The ID of the request. Request IDs are unique within a browser session. As a result, they could be used to relate different events of the same request.
     */
    var requestId: String

    var url: String

    /**
     * Standard HTTP method.
     */
    var method: String

    /**
     * The value 0 indicates that the request happens in the main frame; a positive value indicates the ID of a subframe in which the request happens. If the document of a (sub-)frame is loaded (<code>type</code> is <code>main_frame</code> or <code>sub_frame</code>), <code>frameId</code> indicates the ID of this frame, not the ID of the outer frame. Frame IDs are unique within a tab.
     */
    var frameId: Int

    /**
     * ID of frame that wraps the frame which sent the request. Set to -1 if no parent frame exists.
     */
    var parentFrameId: Int

    /**
     * URL of the resource that triggered this request.
     */
    var originUrl: String?

    /**
     * URL of the page into which the requested resource will be loaded.
     */
    var documentUrl: String?

    /**
     * The ID of the tab in which the request takes place. Set to -1 if the request isn't related to a tab.
     */
    var tabId: Int

    /**
     * How the requested resource will be used.
     */
    var type: ResourceType

    /**
     * The time when this signal is triggered, in milliseconds since the epoch.
     */
    var timeStamp: Int

    /**
     * The server IP address that the request was actually sent to. Note that it may be a literal IPv6 address.
     */
    var ip: String?

    /**
     * Indicates if this response was fetched from disk cache.
     */
    var fromCache: Boolean

    /**
     * The error description. This string is <em>not</em> guaranteed to remain backwards compatible between releases. You must not parse and act based upon its content.
     */
    var error: String
}

external class WebRequestNamespace {
    val onBeforeRequest: Event<(details: Details) -> Unit>

    val onBeforeSendHeaders: Event<(details: Details2) -> Unit>

    val onSendHeaders: Event<(details: Details3) -> Unit>

    val onHeadersReceived: Event<(details: Details4) -> Unit>

    val onAuthRequired: Event<(details: Details5, callback: Callback?) -> Unit>

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
    fun filterResponseData(requestId: String): FilterResponseDataResult
}
