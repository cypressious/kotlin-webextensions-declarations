package omnibox

typealias DescriptionStyleType = String

typealias OnInputEnteredDisposition = String

/**
 * A suggest result.
 */
external class SuggestResult {
  /**
   * The text that is put into the URL bar, and that is sent to the extension when the user chooses this entry.
   */
  val content: String

  /**
   * The text that is displayed in the URL dropdown. Can contain XML-style markup for styling. The supported tags are 'url' (for a literal URL), 'match' (for highlighting text that matched what the user's query), and 'dim' (for dim helper text). The styles can be nested, eg. <dim><match>dimmed match</match></dim>. You must escape the five predefined entities to display them as text: stackoverflow.com/a/1091953/89484 
   */
  val description: String
}

/**
 * A suggest result.
 */
external class DefaultSuggestResult {
  /**
   * The text that is displayed in the URL dropdown.
   */
  val description: String
}

/**
 * The style ranges for the description, as provided by the extension.
 */
external class DescriptionStyles {
  val offset: Int

  /**
   * The style type
   */
  val type: DescriptionStyleType

  val length: Int?
}

/**
 * The style ranges for the description, as provided by ToValue().
 */
external class DescriptionStylesRaw {
  val offset: Int

  val type: Int
}

/**
 * The style ranges for the description, as provided by the extension.
 */
external class DescriptionStyles2 {
  val offset: Int

  /**
   * The style type
   */
  val type: DescriptionStyleType

  val length: Int?
}

/**
 * The style ranges for the description, as provided by ToValue().
 */
external class DescriptionStylesRaw2 {
  val offset: Int

  val type: Int
}

external class OmniboxNamespace {
  /**
   * Sets the description and styling for the default suggestion. The default suggestion is the text that is displayed in the first suggestion row underneath the URL bar.
   */
  fun setDefaultSuggestion(suggestion: DefaultSuggestResult)
}
