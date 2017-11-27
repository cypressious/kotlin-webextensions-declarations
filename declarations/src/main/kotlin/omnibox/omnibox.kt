package omnibox

external class DescriptionStyleType

external class OnInputEnteredDisposition

external class SuggestResult {
  /**
   * The text that is put into the URL bar, and that is sent to the extension when the user chooses this entry.
   */
  val content: String

  /**
   * The text that is displayed in the URL dropdown. Can contain XML-style markup for styling. The supported tags are 'url' (for a literal URL), 'match' (for highlighting text that matched what the user's query), and 'dim' (for dim helper text). The styles can be nested, eg. <dim><match>dimmed match</match></dim>. You must escape the five predefined entities to display them as text: stackoverflow.com/a/1091953/89484 
   */
  val description: String

  /**
   * An array of style ranges for the description, as provided by the extension.
   */
  val descriptionStyles: Array<DescriptionStyles>

  /**
   * An array of style ranges for the description, as provided by ToValue().
   */
  val descriptionStylesRaw: Array<DescriptionStylesRaw>
}

external class DefaultSuggestResult {
  /**
   * The text that is displayed in the URL dropdown.
   */
  val description: String

  /**
   * An array of style ranges for the description, as provided by the extension.
   */
  val descriptionStyles: Array<DescriptionStyles>

  /**
   * An array of style ranges for the description, as provided by ToValue().
   */
  val descriptionStylesRaw: Array<DescriptionStylesRaw>
}

external class Omnibox {
  /**
   * Sets the description and styling for the default suggestion. The default suggestion is the text that is displayed in the first suggestion row underneath the URL bar.
   */
  fun setDefaultSuggestion(suggestion: DefaultSuggestResult) {
  }
}
