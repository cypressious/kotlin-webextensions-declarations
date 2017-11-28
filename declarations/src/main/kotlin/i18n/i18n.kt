package i18n

import kotlin.js.Promise

class Languages(val language: LanguageCode, /**
 * The percentage of the detected language
 */
val percentage: Int)

class DetectLanguageResult(/**
 * CLD detected language reliability
 */
val isReliable: Boolean, /**
 * array of detectedLanguage
 */
val languages: Array<Languages>)

typealias LanguageCode = String

external class I18n {
  /**
   * Gets the accept-languages of the browser. This is different from the locale used by the browser; to get the locale, use $(ref:i18n.getUILanguage).
   */
  fun getAcceptLanguages(): Promise<Array<LanguageCode>>

  /**
   * Gets the localized string for the specified message. If the message is missing, this method returns an empty string (''). If the format of the <code>getMessage()</code> call is wrong &mdash; for example, <em>messageName</em> is not a string or the <em>substitutions</em> array has more than 9 elements &mdash; this method returns <code>undefined</code>.
   */
  fun getMessage(messageName: String, substitutions: Any)

  /**
   * Gets the browser UI language of the browser. This is different from $(ref:i18n.getAcceptLanguages) which returns the preferred user languages.
   */
  fun getUILanguage()

  /**
   * Detects the language of the provided text using CLD.
   */
  fun detectLanguage(text: String): Promise<DetectLanguageResult>
}
