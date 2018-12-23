package devtools

import manifest.ExtensionURL
import webextensions.Event
import kotlin.js.Promise

/**
 * A resource within the inspected page, such as a document, a script, or an image.
 * @param url The URL of the resource.
 */
class Resource(
    var url: String
)

/**
 * The options parameter can contain one or more options.
 */
class Options()

/**
 * An object providing details if an exception occurred while evaluating the expression.
 * @param isError Set if the error occurred on the DevTools side before the expression is evaluated.
 * @param code Set if the error occurred on the DevTools side before the expression is evaluated.
 * @param description Set if the error occurred on the DevTools side before the expression is
        evaluated.
 * @param details Set if the error occurred on the DevTools side before the expression is evaluated,
        contains the array of the values that may be substituted into the description string to
        provide more information about the cause of the error.
 * @param isException Set if the evaluated code produces an unhandled exception.
 * @param value Set if the evaluated code produces an unhandled exception.
 */
class ExceptionInfo(
    var isError: Boolean,
    var code: String,
    var description: String,
    var details: Array<dynamic>,
    var isException: Boolean,
    var value: String
)

/**
 * @param ignoreCache When true, the loader will bypass the cache for all inspected page resources
        loaded before the <code>load</code> event is fired. The effect is similar to pressing
        Ctrl+Shift+R in the inspected window or within the Developer Tools window.
 * @param userAgent If specified, the string will override the value of the <code>User-Agent</code>
        HTTP header that's sent while loading the resources of the inspected page. The string will
        also override the value of the <code>navigator.userAgent</code> property that's returned to
        any scripts that are running within the inspected page.
 * @param injectedScript If specified, the script will be injected into every frame of the inspected
        page immediately upon load, before any of the frame's scripts. The script will not be
        injected after subsequent reloads&mdash;for example, if the user presses Ctrl+R.
 */
class ReloadOptions(
    var ignoreCache: Boolean? = null,
    var userAgent: String? = null,
    var injectedScript: String? = null
)

external class InspectedWindowNamespace {
    /**
     * Evaluates a JavaScript expression in the context of the main frame of the inspected page. The
            expression must evaluate to a JSON-compliant object, otherwise an exception is thrown.
            The eval function can report either a DevTools-side error or a JavaScript exception that
            occurs during evaluation. In either case, the <code>result</code> parameter of the
            callback is <code>undefined</code>. In the case of a DevTools-side error, the
            <code>isException</code> parameter is non-null and has <code>isError</code> set to true
            and <code>code</code> set to an error code. In the case of a JavaScript error,
            <code>isException</code> is set to true and <code>value</code> is set to the string
            value of thrown object.
     */
    fun eval(expression: String, options: Options? = definedExternally): Promise<dynamic>

    /**
     * Reloads the inspected page.
     */
    fun reload(reloadOptions: ReloadOptions? = definedExternally)
}

/**
 * Represents a network request for a document resource (script, image and so on). See HAR
        Specification for reference. */
typealias Request = Any

/**
 * A HAR log. See HAR specification for details.
 */
@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class HarLog() {
    inline operator fun get(key: String): dynamic = asDynamic()[key]
    inline operator fun set(key: String, value: dynamic) {
        asDynamic()[key] = value
    }
}

external class NetworkNamespace {
    /**
     * Fired when a network request is finished and all request data are available.
     *
     * @param request Description of a network request in the form of a HAR entry. See HAR
            specification for details. */
    val onRequestFinished: Event<(request: Request) -> Unit>

    /**
     * Fired when the inspected window navigates to a new page.
     *
     * @param url URL of the new page. */
    val onNavigated: Event<(url: String) -> Unit>

    /**
     * Returns HAR log that contains all known network requests.
     */
    fun getHAR(): Promise<HarLog>
}

/**
 * Represents the Elements panel. */
typealias ElementsPanel = Any

/**
 * Represents the Sources panel. */
typealias SourcesPanel = Any

/**
 * Represents a panel created by extension. */
typealias ExtensionPanel = Any

/**
 * A sidebar created by the extension. */
typealias ExtensionSidebarPane = Any

/**
 * A button created by the extension. */
typealias Button = Any

/**
 * Path of the panel's icon relative to the extension directory, or an empty string to use the
        default extension icon as the panel icon. */
typealias IconPath = Any

external class PanelsNamespace {
    /**
     * Fired when the devtools theme changes.
     *
     * @param themeName The name of the current devtools theme. */
    val onThemeChanged: Event<(themeName: String) -> Unit>

//    /**
//     * Creates an extension panel.
//     */
//    fun create(
//        title: String,
//        iconPath: String,
//        pagePath: ExtensionURL
//    ): Promise<ExtensionPanel>

    /**
     * Creates an extension panel.
     */
    fun create(
        title: String,
        iconPath: ExtensionURL,
        pagePath: ExtensionURL
    ): Promise<ExtensionPanel>
}

external class DevtoolsNamespace {
    val inspectedWindow: InspectedWindowNamespace

    val network: NetworkNamespace

    val panels: PanelsNamespace
}
