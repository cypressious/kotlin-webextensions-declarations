package contentScripts

import extensionTypes.ExtensionFileOrCode
import extensionTypes.RunAt
import kotlin.js.Promise
import manifest.MatchPattern

/**
 * Details of a content script registered programmatically
 * @param css The list of CSS files to inject
 * @param js The list of JS files to inject
 * @param allFrames If allFrames is <code>true</code>, implies that the JavaScript or CSS should be
        injected into all frames of current page. By default, it's <code>false</code> and is only
        injected into the top frame.
 * @param matchAboutBlank If matchAboutBlank is true, then the code is also injected in about:blank
        and about:srcdoc frames if your extension has access to its parent document. Code cannot be
        inserted in top-level about:-frames. By default it is <code>false</code>.
 * @param runAt The soonest that the JavaScript or CSS will be injected into the tab. Defaults to
        "document_idle".
 */
class RegisteredContentScriptOptions(
    var matches: Array<MatchPattern>,
    var excludeMatches: Array<MatchPattern>? = null,
    var includeGlobs: Array<String>? = null,
    var excludeGlobs: Array<String>? = null,
    var css: Array<ExtensionFileOrCode>? = null,
    var js: Array<ExtensionFileOrCode>? = null,
    var allFrames: Boolean? = null,
    var matchAboutBlank: Boolean? = null,
    var runAt: RunAt? = null
)

/**
 * An object that represents a content script registered programmatically */
typealias RegisteredContentScript = Any

external class ContentScriptsNamespace {
    /**
     * Register a content script programmatically
     */
    fun register(contentScriptOptions: RegisteredContentScriptOptions): Promise<Any>
}
