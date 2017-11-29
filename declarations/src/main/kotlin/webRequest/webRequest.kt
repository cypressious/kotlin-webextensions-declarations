package webRequest

import kotlin.Any
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

typealias HttpHeaders = Any

class AuthCredentials(val username: String, val password: String)

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
  val authCredentials: AuthCredentials?
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

external class WebRequestNamespace {
  /**
   * Needs to be called when the behavior of the webRequest handlers has changed to prevent incorrect handling due to caching. This function call is expensive. Don't call it often.
   */
  fun handlerBehaviorChanged(): Promise<Any>

  /**
   * ...
   */
  fun filterResponseData(requestId: String)
}
