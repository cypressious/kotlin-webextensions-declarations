package userScripts

import extensionTypes.ExtensionFileOrCode
import extensionTypes.PlainJSONValue
import extensionTypes.RunAt
import manifest.MatchPattern
import webextensions.Event
import kotlin.js.Promise

/**
 * Details of a user script
 * @param js The list of JS files to inject
 * @param scriptMetadata An opaque user script metadata value
 * @param allFrames If allFrames is <code>true</code>, implies that the JavaScript should be
        injected into all frames of current page. By default, it's <code>false</code> and is only
        injected into the top frame.
 * @param matchAboutBlank If matchAboutBlank is true, then the code is also injected in about:blank
        and about:srcdoc frames if your extension has access to its parent document. Code cannot be
        inserted in top-level about:-frames. By default it is <code>false</code>.
 * @param runAt The soonest that the JavaScript will be injected into the tab. Defaults to
        "document_idle".
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
 * A plain object whose properties are exported as userScript globals */
typealias SourceObject = Any

/**
 * @param metadata The userScript metadata (as set in userScripts.register)
 * @param global The userScript global
 * @param defineGlobals Exports all the properties of a given plain object as userScript globals
 * @param export Convert a given value to make it accessible to the userScript code
 */
class UserScript(
    var metadata: dynamic,
    var global: dynamic,
    var defineGlobals: () -> Unit,
    var export: () -> Unit
)

external class UserScriptsNamespace {
    /**
     * Event called when a new userScript global has been created
     *
     * @param userScript null */
    val onBeforeScript: Event<(userScript: UserScript) -> Unit>

    /**
     * Register a user script programmatically given its $(ref:userScripts.UserScriptOptions), and
            resolves to a $(ref:userScripts.RegisteredUserScript) instance
     */
    fun register(userScriptOptions: UserScriptOptions): Promise<Any>
}
