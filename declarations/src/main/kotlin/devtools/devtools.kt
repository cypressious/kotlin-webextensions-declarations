package devtools

import kotlin.Any
import kotlin.js.Promise

class EvalOptions()

class ReloadReloadOptions(
    /**
     * When true, the loader will bypass the cache for all inspected page resources loaded before the <code>load</code> event is fired. The effect is similar to pressing Ctrl+Shift+R in the inspected window or within the Developer Tools window.
     */
    val ignoreCache: Boolean?,
    /**
     * If specified, the string will override the value of the <code>User-Agent</code> HTTP header that's sent while loading the resources of the inspected page. The string will also override the value of the <code>navigator.userAgent</code> property that's returned to any scripts that are running within the inspected page.
     */
    val userAgent: String?,
    /**
     * If specified, the script will be injected into every frame of the inspected page immediately upon load, before any of the frame's scripts. The script will not be injected after subsequent reloads&mdash;for example, if the user presses Ctrl+R.
     */
    val injectedScript: String?
)

/**
 * A resource within the inspected page, such as a document, a script, or an image.
 */
external class Resource {
  /**
   * The URL of the resource.
   */
  val url: String
}

external class InspectedWindow {
  /**
   * Evaluates a JavaScript expression in the context of the main frame of the inspected page. The expression must evaluate to a JSON-compliant object, otherwise an exception is thrown. The eval function can report either a DevTools-side error or a JavaScript exception that occurs during evaluation. In either case, the <code>result</code> parameter of the callback is <code>undefined</code>. In the case of a DevTools-side error, the <code>isException</code> parameter is non-null and has <code>isError</code> set to true and <code>code</code> set to an error code. In the case of a JavaScript error, <code>isException</code> is set to true and <code>value</code> is set to the string value of thrown object.
   */
  fun eval(expression: String, options: EvalOptions?): Promise<Any>

  /**
   * Reloads the inspected page.
   */
  fun reload(reloadOptions: ReloadReloadOptions?)
}

typealias Request = Any

external class Network

typealias ElementsPanel = Any

typealias SourcesPanel = Any

typealias ExtensionPanel = Any

typealias ExtensionSidebarPane = Any

typealias Button = Any

external class Panels {
  /**
   * Creates an extension panel.
   */
  fun create(
      title: String,
      iconPath: String,
      pagePath: String
  ): Promise<ExtensionPanel>
}

external class Devtools {
  val inspectedWindow: InspectedWindow

  val network: Network

  val panels: Panels
}
