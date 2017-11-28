package proxy

import kotlin.Deprecated
import kotlin.js.Promise

external class Proxy {
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
