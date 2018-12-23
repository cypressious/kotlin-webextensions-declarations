package webRequest

import kotlin.Suppress
import kotlin.js.Promise
import webextensions.Event

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
 * @param urls A list of URLs or URL patterns. Requests that cannot match any of the URLs will be
        filtered out.
 * @param types A list of request types. Requests that cannot match any of the types will be
        filtered out.
 */
class RequestFilter(
    var urls: Array<String>,
    var types: Array<ResourceType>? = null,
    var tabId: Int? = null,
    var windowId: Int? = null
)

/**
 * An array of HTTP headers. Each header is represented as a dictionary containing the keys
        <code>name</code> and either <code>value</code> or <code>binaryValue</code>. */
typealias HttpHeaders = Array<HttpHeaders2>

/**
 * Returns value for event handlers that have the 'blocking' extraInfoSpec applied. Allows the event
        handler to modify network requests.
 * @param cancel If true, the request is cancelled. Used in onBeforeRequest, this prevents the
        request from being sent.
 * @param redirectUrl Only used as a response to the onBeforeRequest and onHeadersReceived events.
        If set, the original request is prevented from being sent/completed and is instead
        redirected to the given URL. Redirections to non-HTTP schemes such as data: are allowed.
        Redirects initiated by a redirect action use the original request method for the redirect,
        with one exception: If the redirect is initiated at the onHeadersReceived stage, then the
        redirect will be issued using the GET method.
 * @param upgradeToSecure Only used as a response to the onBeforeRequest event. If set, the original
        request is prevented from being sent/completed and is instead upgraded to a secure request. 
        If any extension returns <code>redirectUrl</code> during onBeforeRequest,
        <code>upgradeToSecure</code> will have no affect.
 * @param requestHeaders Only used as a response to the onBeforeSendHeaders event. If set, the
        request is made with these request headers instead.
 * @param responseHeaders Only used as a response to the onHeadersReceived event. If set, the server
        is assumed to have responded with these response headers instead. Only return
        <code>responseHeaders</code> if you really want to modify the headers in order to limit the
        number of conflicts (only one extension may modify <code>responseHeaders</code> for each
        request).
 * @param authCredentials Only used as a response to the onAuthRequired event. If set, the request
        is made using the supplied credentials.
 */
class BlockingResponse(
    var cancel: Boolean? = null,
    var redirectUrl: String? = null,
    var upgradeToSecure: Boolean? = null,
    var requestHeaders: HttpHeaders? = null,
    var responseHeaders: HttpHeaders? = null,
    var authCredentials: AuthCredentials? = null
)

/**
 * Contains the certificate properties of the request if it is a secure request.
 * @param validity Contains start and end timestamps.
 */
class CertificateInfo(
    var subject: String,
    var issuer: String,
    var validity: Validity,
    var fingerprint: Fingerprint,
    var serialNumber: String,
    var isBuiltInRoot: Boolean,
    var subjectPublicKeyInfoDigest: SubjectPublicKeyInfoDigest,
    var rawDER: Array<Int>? = null
)

typealias CertificateTransparencyStatus = String

typealias TransportWeaknessReasons = String

/**
 * Contains the security properties of the request (ie. SSL/TLS information).
 * @param errorMessage Error message if state is "broken"
 * @param protocolVersion Protocol version if state is "secure"
 * @param cipherSuite The cipher suite used in this request if state is "secure".
 * @param keaGroupName The key exchange algorithm used in this request if state is "secure".
 * @param signatureSchemeName The signature scheme used in this request if state is "secure".
 * @param certificates Certificate data if state is "secure".  Will only contain one entry unless
        <code>certificateChain</code> is passed as an option.
 * @param isDomainMismatch The domain name does not match the certificate domain.
 * @param isNotValidAtThisTime The certificate is either expired or is not yet valid.  See
        <code>CertificateInfo.validity</code> for start and end dates.
 * @param certificateTransparencyStatus Certificate transparency compliance per RFC 6962.  See
        <code>https://www.certificate-transparency.org/what-is-ct</code> for more information.
 * @param hsts True if host uses Strict Transport Security and state is "secure".
 * @param hpkp True if host uses Public Key Pinning and state is "secure".
 * @param weaknessReasons list of reasons that cause the request to be considered weak, if state is
        "weak"
 */
class SecurityInfo(
    var state: String,
    var errorMessage: String? = null,
    var protocolVersion: String? = null,
    var cipherSuite: String? = null,
    var keaGroupName: String? = null,
    var signatureSchemeName: String? = null,
    var certificates: Array<CertificateInfo>,
    var isDomainMismatch: Boolean? = null,
    var isExtendedValidation: Boolean? = null,
    var isNotValidAtThisTime: Boolean? = null,
    var isUntrusted: Boolean? = null,
    var certificateTransparencyStatus: CertificateTransparencyStatus? = null,
    var hsts: Boolean? = null,
    var hpkp: String? = null,
    var weaknessReasons: Array<TransportWeaknessReasons>? = null
)

/**
 * Contains data uploaded in a URL request.
 * @param bytes An ArrayBuffer with a copy of the data.
 * @param file A string with the file's path and name.
 */
class UploadData(
    var bytes: dynamic = null,
    var file: String? = null
)

/**
 * @param name Name of the HTTP header.
 * @param value Value of the HTTP header if it can be represented by UTF-8.
 * @param binaryValue Value of the HTTP header if it cannot be represented by UTF-8, stored as
        individual byte values (0..255).
 */
class HttpHeaders2(
    var name: String,
    var value: String? = null,
    var binaryValue: Array<Int>? = null
)

/**
 * Only used as a response to the onAuthRequired event. If set, the request is made using the
        supplied credentials.
 */
class AuthCredentials(var username: String, var password: String)

/**
 * Contains start and end timestamps.
 */
class Validity(var start: Int, var end: Int)

class Fingerprint(var sha1: String, var sha256: String)

class SubjectPublicKeyInfoDigest(var sha256: String)

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class FilterResponseDataResult() {
    inline operator fun get(key: String): dynamic = asDynamic()[key]
    inline operator fun set(key: String, value: dynamic) {
        asDynamic()[key] = value
    }
}

/**
 * @param certificateChain Include the entire certificate chain.
 * @param rawDER Include raw certificate data for processing by the extension.
 */
class Options(
    var certificateChain: Boolean? = null,
    var rawDER: Boolean? = null
)

/**
 * If the request method is POST and the body is a sequence of key-value pairs encoded in UTF8,
        encoded as either multipart/form-data, or application/x-www-form-urlencoded, this dictionary
        is present and for each key contains the list of all values for that key. If the data is of
        another media type, or if it is malformed, the dictionary is not present. An example value
        of this dictionary is {'key': ['value1', 'value2']}.
 */
@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class FormData() {
    inline operator fun get(key: String): Array<String> = asDynamic()[key]
    inline operator fun set(key: String, value: Array<String>) {
        asDynamic()[key] = value
    }
}

/**
 * Contains the HTTP request body data. Only provided if extraInfoSpec contains 'requestBody'.
 * @param error Errors when obtaining request body data.
 * @param formData If the request method is POST and the body is a sequence of key-value pairs
        encoded in UTF8, encoded as either multipart/form-data, or
        application/x-www-form-urlencoded, this dictionary is present and for each key contains the
        list of all values for that key. If the data is of another media type, or if it is
        malformed, the dictionary is not present. An example value of this dictionary is {'key':
        ['value1', 'value2']}.
 * @param raw If the request method is PUT or POST, and the body is not already parsed in formData,
        then the unparsed request body elements are contained in this array.
 */
class RequestBody(
    var error: String? = null,
    var formData: FormData? = null,
    var raw: Array<UploadData>? = null
)

/**
 * @param requestId The ID of the request. Request IDs are unique within a browser session. As a
        result, they could be used to relate different events of the same request.
 * @param method Standard HTTP method.
 * @param frameId The value 0 indicates that the request happens in the main frame; a positive value
        indicates the ID of a subframe in which the request happens. If the document of a
        (sub-)frame is loaded (<code>type</code> is <code>main_frame</code> or
        <code>sub_frame</code>), <code>frameId</code> indicates the ID of this frame, not the ID of
        the outer frame. Frame IDs are unique within a tab.
 * @param parentFrameId ID of frame that wraps the frame which sent the request. Set to -1 if no
        parent frame exists.
 * @param originUrl URL of the resource that triggered this request.
 * @param documentUrl URL of the page into which the requested resource will be loaded.
 * @param requestBody Contains the HTTP request body data. Only provided if extraInfoSpec contains
        'requestBody'.
 * @param tabId The ID of the tab in which the request takes place. Set to -1 if the request isn't
        related to a tab.
 * @param type How the requested resource will be used.
 * @param timeStamp The time when this signal is triggered, in milliseconds since the epoch.
 */
class Details(
    var requestId: String,
    var url: String,
    var method: String,
    var frameId: Int,
    var parentFrameId: Int,
    var originUrl: String? = null,
    var documentUrl: String? = null,
    var requestBody: RequestBody? = null,
    var tabId: Int,
    var type: ResourceType,
    var timeStamp: Float
)

/**
 * @param requestId The ID of the request. Request IDs are unique within a browser session. As a
        result, they could be used to relate different events of the same request.
 * @param method Standard HTTP method.
 * @param frameId The value 0 indicates that the request happens in the main frame; a positive value
        indicates the ID of a subframe in which the request happens. If the document of a
        (sub-)frame is loaded (<code>type</code> is <code>main_frame</code> or
        <code>sub_frame</code>), <code>frameId</code> indicates the ID of this frame, not the ID of
        the outer frame. Frame IDs are unique within a tab.
 * @param parentFrameId ID of frame that wraps the frame which sent the request. Set to -1 if no
        parent frame exists.
 * @param originUrl URL of the resource that triggered this request.
 * @param documentUrl URL of the page into which the requested resource will be loaded.
 * @param tabId The ID of the tab in which the request takes place. Set to -1 if the request isn't
        related to a tab.
 * @param type How the requested resource will be used.
 * @param timeStamp The time when this signal is triggered, in milliseconds since the epoch.
 * @param requestHeaders The HTTP request headers that are going to be sent out with this request.
 */
class Details2(
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
    var requestHeaders: HttpHeaders? = null
)

/**
 * @param requestId The ID of the request. Request IDs are unique within a browser session. As a
        result, they could be used to relate different events of the same request.
 * @param method Standard HTTP method.
 * @param frameId The value 0 indicates that the request happens in the main frame; a positive value
        indicates the ID of a subframe in which the request happens. If the document of a
        (sub-)frame is loaded (<code>type</code> is <code>main_frame</code> or
        <code>sub_frame</code>), <code>frameId</code> indicates the ID of this frame, not the ID of
        the outer frame. Frame IDs are unique within a tab.
 * @param parentFrameId ID of frame that wraps the frame which sent the request. Set to -1 if no
        parent frame exists.
 * @param originUrl URL of the resource that triggered this request.
 * @param documentUrl URL of the page into which the requested resource will be loaded.
 * @param tabId The ID of the tab in which the request takes place. Set to -1 if the request isn't
        related to a tab.
 * @param type How the requested resource will be used.
 * @param timeStamp The time when this signal is triggered, in milliseconds since the epoch.
 * @param requestHeaders The HTTP request headers that have been sent out with this request.
 */
class Details3(
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
    var requestHeaders: HttpHeaders? = null
)

/**
 * @param requestId The ID of the request. Request IDs are unique within a browser session. As a
        result, they could be used to relate different events of the same request.
 * @param method Standard HTTP method.
 * @param frameId The value 0 indicates that the request happens in the main frame; a positive value
        indicates the ID of a subframe in which the request happens. If the document of a
        (sub-)frame is loaded (<code>type</code> is <code>main_frame</code> or
        <code>sub_frame</code>), <code>frameId</code> indicates the ID of this frame, not the ID of
        the outer frame. Frame IDs are unique within a tab.
 * @param parentFrameId ID of frame that wraps the frame which sent the request. Set to -1 if no
        parent frame exists.
 * @param originUrl URL of the resource that triggered this request.
 * @param documentUrl URL of the page into which the requested resource will be loaded.
 * @param tabId The ID of the tab in which the request takes place. Set to -1 if the request isn't
        related to a tab.
 * @param type How the requested resource will be used.
 * @param timeStamp The time when this signal is triggered, in milliseconds since the epoch.
 * @param statusLine HTTP status line of the response or the 'HTTP/0.9 200 OK' string for HTTP/0.9
        responses (i.e., responses that lack a status line).
 * @param responseHeaders The HTTP response headers that have been received with this response.
 * @param statusCode Standard HTTP status code returned by the server.
 */
class Details4(
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
    var statusLine: String,
    var responseHeaders: HttpHeaders? = null,
    var statusCode: Int
)

/**
 * The server requesting authentication.
 */
class Challenger(var host: String, var port: Int)

/**
 * @param requestId The ID of the request. Request IDs are unique within a browser session. As a
        result, they could be used to relate different events of the same request.
 * @param method Standard HTTP method.
 * @param frameId The value 0 indicates that the request happens in the main frame; a positive value
        indicates the ID of a subframe in which the request happens. If the document of a
        (sub-)frame is loaded (<code>type</code> is <code>main_frame</code> or
        <code>sub_frame</code>), <code>frameId</code> indicates the ID of this frame, not the ID of
        the outer frame. Frame IDs are unique within a tab.
 * @param parentFrameId ID of frame that wraps the frame which sent the request. Set to -1 if no
        parent frame exists.
 * @param originUrl URL of the resource that triggered this request.
 * @param documentUrl URL of the page into which the requested resource will be loaded.
 * @param tabId The ID of the tab in which the request takes place. Set to -1 if the request isn't
        related to a tab.
 * @param type How the requested resource will be used.
 * @param timeStamp The time when this signal is triggered, in milliseconds since the epoch.
 * @param scheme The authentication scheme, e.g. Basic or Digest.
 * @param realm The authentication realm provided by the server, if there is one.
 * @param challenger The server requesting authentication.
 * @param isProxy True for Proxy-Authenticate, false for WWW-Authenticate.
 * @param responseHeaders The HTTP response headers that were received along with this response.
 * @param statusLine HTTP status line of the response or the 'HTTP/0.9 200 OK' string for HTTP/0.9
        responses (i.e., responses that lack a status line) or an empty string if there are no
        headers.
 * @param statusCode Standard HTTP status code returned by the server.
 */
class Details5(
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
    var scheme: String,
    var realm: String? = null,
    var challenger: Challenger,
    var isProxy: Boolean,
    var responseHeaders: HttpHeaders? = null,
    var statusLine: String,
    var statusCode: Int
)

/**
 * @param requestId The ID of the request. Request IDs are unique within a browser session. As a
        result, they could be used to relate different events of the same request.
 * @param method Standard HTTP method.
 * @param frameId The value 0 indicates that the request happens in the main frame; a positive value
        indicates the ID of a subframe in which the request happens. If the document of a
        (sub-)frame is loaded (<code>type</code> is <code>main_frame</code> or
        <code>sub_frame</code>), <code>frameId</code> indicates the ID of this frame, not the ID of
        the outer frame. Frame IDs are unique within a tab.
 * @param parentFrameId ID of frame that wraps the frame which sent the request. Set to -1 if no
        parent frame exists.
 * @param originUrl URL of the resource that triggered this request.
 * @param documentUrl URL of the page into which the requested resource will be loaded.
 * @param tabId The ID of the tab in which the request takes place. Set to -1 if the request isn't
        related to a tab.
 * @param type How the requested resource will be used.
 * @param timeStamp The time when this signal is triggered, in milliseconds since the epoch.
 * @param ip The server IP address that the request was actually sent to. Note that it may be a
        literal IPv6 address.
 * @param fromCache Indicates if this response was fetched from disk cache.
 * @param statusCode Standard HTTP status code returned by the server.
 * @param responseHeaders The HTTP response headers that were received along with this response.
 * @param statusLine HTTP status line of the response or the 'HTTP/0.9 200 OK' string for HTTP/0.9
        responses (i.e., responses that lack a status line) or an empty string if there are no
        headers.
 */
class Details6(
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
    var statusCode: Int,
    var responseHeaders: HttpHeaders? = null,
    var statusLine: String
)

/**
 * @param requestId The ID of the request. Request IDs are unique within a browser session. As a
        result, they could be used to relate different events of the same request.
 * @param method Standard HTTP method.
 * @param frameId The value 0 indicates that the request happens in the main frame; a positive value
        indicates the ID of a subframe in which the request happens. If the document of a
        (sub-)frame is loaded (<code>type</code> is <code>main_frame</code> or
        <code>sub_frame</code>), <code>frameId</code> indicates the ID of this frame, not the ID of
        the outer frame. Frame IDs are unique within a tab.
 * @param parentFrameId ID of frame that wraps the frame which sent the request. Set to -1 if no
        parent frame exists.
 * @param originUrl URL of the resource that triggered this request.
 * @param documentUrl URL of the page into which the requested resource will be loaded.
 * @param tabId The ID of the tab in which the request takes place. Set to -1 if the request isn't
        related to a tab.
 * @param type How the requested resource will be used.
 * @param timeStamp The time when this signal is triggered, in milliseconds since the epoch.
 * @param ip The server IP address that the request was actually sent to. Note that it may be a
        literal IPv6 address.
 * @param fromCache Indicates if this response was fetched from disk cache.
 * @param statusCode Standard HTTP status code returned by the server.
 * @param redirectUrl The new URL.
 * @param responseHeaders The HTTP response headers that were received along with this redirect.
 * @param statusLine HTTP status line of the response or the 'HTTP/0.9 200 OK' string for HTTP/0.9
        responses (i.e., responses that lack a status line) or an empty string if there are no
        headers.
 */
class Details7(
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
    var statusCode: Int,
    var redirectUrl: String,
    var responseHeaders: HttpHeaders? = null,
    var statusLine: String
)

/**
 * @param requestId The ID of the request. Request IDs are unique within a browser session. As a
        result, they could be used to relate different events of the same request.
 * @param method Standard HTTP method.
 * @param frameId The value 0 indicates that the request happens in the main frame; a positive value
        indicates the ID of a subframe in which the request happens. If the document of a
        (sub-)frame is loaded (<code>type</code> is <code>main_frame</code> or
        <code>sub_frame</code>), <code>frameId</code> indicates the ID of this frame, not the ID of
        the outer frame. Frame IDs are unique within a tab.
 * @param parentFrameId ID of frame that wraps the frame which sent the request. Set to -1 if no
        parent frame exists.
 * @param originUrl URL of the resource that triggered this request.
 * @param documentUrl URL of the page into which the requested resource will be loaded.
 * @param tabId The ID of the tab in which the request takes place. Set to -1 if the request isn't
        related to a tab.
 * @param type How the requested resource will be used.
 * @param timeStamp The time when this signal is triggered, in milliseconds since the epoch.
 * @param ip The server IP address that the request was actually sent to. Note that it may be a
        literal IPv6 address.
 * @param fromCache Indicates if this response was fetched from disk cache.
 * @param statusCode Standard HTTP status code returned by the server.
 * @param responseHeaders The HTTP response headers that were received along with this response.
 * @param statusLine HTTP status line of the response or the 'HTTP/0.9 200 OK' string for HTTP/0.9
        responses (i.e., responses that lack a status line) or an empty string if there are no
        headers.
 */
class Details8(
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
    var statusCode: Int,
    var responseHeaders: HttpHeaders? = null,
    var statusLine: String
)

/**
 * @param requestId The ID of the request. Request IDs are unique within a browser session. As a
        result, they could be used to relate different events of the same request.
 * @param method Standard HTTP method.
 * @param frameId The value 0 indicates that the request happens in the main frame; a positive value
        indicates the ID of a subframe in which the request happens. If the document of a
        (sub-)frame is loaded (<code>type</code> is <code>main_frame</code> or
        <code>sub_frame</code>), <code>frameId</code> indicates the ID of this frame, not the ID of
        the outer frame. Frame IDs are unique within a tab.
 * @param parentFrameId ID of frame that wraps the frame which sent the request. Set to -1 if no
        parent frame exists.
 * @param originUrl URL of the resource that triggered this request.
 * @param documentUrl URL of the page into which the requested resource will be loaded.
 * @param tabId The ID of the tab in which the request takes place. Set to -1 if the request isn't
        related to a tab.
 * @param type How the requested resource will be used.
 * @param timeStamp The time when this signal is triggered, in milliseconds since the epoch.
 * @param ip The server IP address that the request was actually sent to. Note that it may be a
        literal IPv6 address.
 * @param fromCache Indicates if this response was fetched from disk cache.
 * @param error The error description. This string is <em>not</em> guaranteed to remain backwards
        compatible between releases. You must not parse and act based upon its content.
 */
class Details9(
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
    var error: String
)

external class WebRequestNamespace {
    /**
     * Fired when a request is about to occur.
     *
     * @param details null */
    val onBeforeRequest: Event<(details: Details) -> Unit>

    /**
     * Fired before sending an HTTP request, once the request headers are available. This may occur
            after a TCP connection is made to the server, but before any HTTP data is sent. 
     *
     * @param details null */
    val onBeforeSendHeaders: Event<(details: Details2) -> Unit>

    /**
     * Fired just before a request is going to be sent to the server (modifications of previous
            onBeforeSendHeaders callbacks are visible by the time onSendHeaders is fired).
     *
     * @param details null */
    val onSendHeaders: Event<(details: Details3) -> Unit>

    /**
     * Fired when HTTP response headers of a request have been received.
     *
     * @param details null */
    val onHeadersReceived: Event<(details: Details4) -> Unit>

    /**
     * Fired when an authentication failure is received. The listener has three options: it can
            provide authentication credentials, it can cancel the request and display the error
            page, or it can take no action on the challenge. If bad user credentials are provided,
            this may be called multiple times for the same request.
     *
     * @param details null
     * @param callback null */
    val onAuthRequired: Event<(details: Details5, callback: (() -> Unit)?) -> Unit>

    /**
     * Fired when the first byte of the response body is received. For HTTP requests, this means
            that the status line and response headers are available.
     *
     * @param details null */
    val onResponseStarted: Event<(details: Details6) -> Unit>

    /**
     * Fired when a server-initiated redirect is about to occur.
     *
     * @param details null */
    val onBeforeRedirect: Event<(details: Details7) -> Unit>

    /**
     * Fired when a request is completed.
     *
     * @param details null */
    val onCompleted: Event<(details: Details8) -> Unit>

    /**
     * Fired when an error occurs.
     *
     * @param details null */
    val onErrorOccurred: Event<(details: Details9) -> Unit>

    /**
     * Needs to be called when the behavior of the webRequest handlers has changed to prevent
            incorrect handling due to caching. This function call is expensive. Don't call it often.
     */
    fun handlerBehaviorChanged(): Promise<Any>

    /**
     * ...
     */
    fun filterResponseData(requestId: String): FilterResponseDataResult

    /**
     * Retrieves the security information for the request.  Returns a promise that will resolve to a
            SecurityInfo object.
     */
    fun getSecurityInfo(requestId: String, options: Options? = definedExternally): Promise<Any>
}
