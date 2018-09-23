package userScripts

import extensionTypes.ExtensionFileOrCode
import extensionTypes.PlainJSONValue
import extensionTypes.RunAt
import manifest.MatchPattern
import kotlin.js.Promise

/**
 * Details of a user script
 * @param js The list of JS files to inject
 * @param scriptMetadata An opaque user script metadata value
 * @param allFrames If allFrames is <code>true</code>, implies that the JavaScript should be injected into all frames of current page. By default, it's <code>false</code> and is only injected into the top frame.
 * @param matchAboutBlank If matchAboutBlank is true, then the code is also injected in about:blank and about:srcdoc frames if your extension has access to its parent document. Code cannot be inserted in top-level about:-frames. By default it is <code>false</code>.
 * @param runAt The soonest that the JavaScript will be injected into the tab. Defaults to "document_idle".
 */
class UserScriptOptions(
    var js: Array<ExtensionFileOrCode>? = null,
    var scriptMetadata: PlainJSONValue? = null,
    var matches: Array<MatchPattern>,
    var excludeMatches: Array<MatchPattern>? = null,
    var includeGlobs: Array<String>? = null,
    var excludeGlobs: Array<String>? = null,
    var allFrames: Boolean? = null,
    var matchAboutBlank: Boolean? = null,
    var runAt: RunAt? = null
)

/**
 * An object that represents a user script registered programmatically */
typealias RegisteredUserScript = Any

/**
 * A set of API methods provided by the extensions to its userScripts
 */
@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class ExportedAPIMethods() {
    inline operator fun get(key: String): () -> Unit = asDynamic()[key]
    inline operator fun set(key: String, noinline value: () -> Unit) {
        asDynamic()[key] = value
    }
}

external class UserScriptsNamespace {
    /**
     * Register a user script programmatically given its $(ref:userScripts.UserScriptOptions), and resolves to a $(ref:userScripts.RegisteredUserScript) instance
     */
    fun register(userScriptOptions: UserScriptOptions): Promise<Any>

    /**
     * Provides a set of custom API methods available to the registered userScripts
     */
    fun setScriptAPIs(exportedAPIMethods: ExportedAPIMethods)
}
