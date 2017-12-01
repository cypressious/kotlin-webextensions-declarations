package i18n

import kotlin.js.Promise

/**
 * An ISO language code such as <code>en</code> or <code>fr</code>. For a complete list of languages supported by this method, see <a href='http://src.chromium.org/viewvc/chrome/trunk/src/third_party/cld/languages/internal/languages.cc'>kLanguageInfoTable</a>. For an unknown language, <code>und</code> will be returned, which means that [percentage] of the text is unknown to CLD */
typealias LanguageCode = String

/**
 * DetectedLanguage object that holds detected ISO language code and its percentage in the input string
 */
external class Languages {
    var language: LanguageCode

    /**
     * The percentage of the detected language
     */
    var percentage: Int
}

/**
 * LanguageDetectionResult object that holds detected langugae reliability and array of DetectedLanguage
 */
external class Result {
    /**
     * CLD detected language reliability
     */
    var isReliable: Boolean

    /**
     * array of detectedLanguage
     */
    var languages: Array<Languages>
}

external class I18nNamespace {
    /**
     * Gets the accept-languages of the browser. This is different from the locale used by the browser; to get the locale, use $(ref:i18n.getUILanguage).
     */
    fun getAcceptLanguages(): Promise<Array<LanguageCode>>

    /**
     * Gets the localized string for the specified message. If the message is missing, this method returns an empty string (''). If the format of the <code>getMessage()</code> call is wrong &mdash; for example, <em>messageName</em> is not a string or the <em>substitutions</em> array has more than 9 elements &mdash; this method returns <code>undefined</code>.
     */
    fun getMessage(messageName: String, substitutions: Any?)

    /**
     * Gets the browser UI language of the browser. This is different from $(ref:i18n.getAcceptLanguages) which returns the preferred user languages.
     */
    fun getUILanguage()

    /**
     * Detects the language of the provided text using CLD.
     */
    fun detectLanguage(text: String): Promise<Result>
}
