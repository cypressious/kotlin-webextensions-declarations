package contentScripts

import extensionTypes.RunAt
import kotlin.Suppress
import kotlin.js.Promise
import manifest.MatchPattern
import manifest.UnrecognizedProperty

typealias ExtensionFileOrCode = Any

/**
 * Details of a content script registered programmatically
 */
@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class RegisteredContentScriptOptions(
        var matches: Array<MatchPattern>,
        var excludeMatches: Array<MatchPattern>? = null,
        var includeGlobs: Array<String>? = null,
        var excludeGlobs: Array<String>? = null,
        /**
         * The list of CSS files to inject
         */
        var css: Array<ExtensionFileOrCode>? = null,
        /**
         * The list of JS files to inject
         */
        var js: Array<ExtensionFileOrCode>? = null,
        /**
         * If allFrames is <code>true</code>, implies that the JavaScript or CSS should be injected into all frames of current page. By default, it's <code>false</code> and is only injected into the top frame.
         */
        var allFrames: Boolean? = null,
        /**
         * If matchAboutBlank is true, then the code is also injected in about:blank and about:srcdoc frames if your extension has access to its parent document. Code cannot be inserted in top-level about:-frames. By default it is <code>false</code>.
         */
        var matchAboutBlank: Boolean? = null,
        /**
         * The soonest that the JavaScript or CSS will be injected into the tab. Defaults to "document_idle".
         */
        var runAt: RunAt? = null
) {
    inline operator fun get(key: String): UnrecognizedProperty = asDynamic()[key]
    inline operator fun set(key: String, value: UnrecognizedProperty) {
        asDynamic()[key] = value
    }
}

/**
 * An object that represents a content script registered programmatically */
typealias RegisteredContentScript = Any

external class ContentScriptsNamespace {
    /**
     * Register a content script programmatically
     */
    fun register(contentScriptOptions: RegisteredContentScriptOptions): Promise<Any>
}
