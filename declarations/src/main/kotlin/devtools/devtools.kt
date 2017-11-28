package devtools

import devtools.inspectedWindow.Resource
import kotlin.js.Promise

class EvalOptions()

class ReloadReloadOptions(
    /**
     * When true, the loader will bypass the cache for all inspected page resources loaded before the <code>load</code> event is fired. The effect is similar to pressing Ctrl+Shift+R in the inspected window or within the Developer Tools window.
     */
    val ignoreCache: Boolean,
    /**
     * If specified, the string will override the value of the <code>User-Agent</code> HTTP header that's sent while loading the resources of the inspected page. The string will also override the value of the <code>navigator.userAgent</code> property that's returned to any scripts that are running within the inspected page.
     */
    val userAgent: String,
    /**
     * If specified, the script will be injected into every frame of the inspected page immediately upon load, before any of the frame's scripts. The script will not be injected after subsequent reloads&mdash;for example, if the user presses Ctrl+R.
     */
    val injectedScript: String
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
  fun eval(expression: String, options: EvalOptions): Promise<Any>

  /**
   * Reloads the inspected page.
   */
  fun reload(reloadOptions: ReloadReloadOptions)

  /**
   * Retrieves the list of resources from the inspected page.
   */
  fun getResources(): Promise<Array<Resource>>
}

class GetHARHarLog()

/**
 * Represents a network request for a document resource (script, image and so on). See HAR Specification for reference.
 */
external class Request

external class Network {
  /**
   * Returns HAR log that contains all known network requests.
   */
  fun getHAR(): Promise<GetHARHarLog>
}

/**
 * Represents the Elements panel.
 */
external class ElementsPanel

/**
 * Represents the Sources panel.
 */
external class SourcesPanel

/**
 * Represents a panel created by extension.
 */
external class ExtensionPanel

/**
 * A sidebar created by the extension.
 */
external class ExtensionSidebarPane

/**
 * A button created by the extension.
 */
external class Button

external class Panels {
  /**
   * Creates an extension panel.
   */
  fun create(
      title: String,
      iconPath: String,
      pagePath: String
  ): Promise<ExtensionPanel>

  /**
   * Specifies the function to be called when the user clicks a resource link in the Developer Tools window. To unset the handler, either call the method with no parameters or pass null as the parameter.
   */
  fun setOpenResourceHandler(): Promise<Resource>

  /**
   * Requests DevTools to open a URL in a Developer Tools panel.
   */
  fun openResource(url: String, lineNumber: Int): Promise<Any>
}

external class Devtools {
  val inspectedWindow: InspectedWindow

  val network: Network

  val panels: Panels
}
