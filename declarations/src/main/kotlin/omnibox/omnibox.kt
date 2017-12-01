package omnibox

import browser.Event

/**
 * The style type. */
typealias DescriptionStyleType = String

/**
 * The window disposition for the omnibox query. This is the recommended context to display results. For example, if the omnibox command is to navigate to a certain URL, a disposition of 'newForegroundTab' means the navigation should take place in a new selected tab. */
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

/**
 * A callback passed to the onInputChanged event used for sending suggestions back to the browser. */
typealias Suggest = Any

external class OmniboxNamespace {
    val onInputStarted: Event<() -> Unit>

    val onInputChanged: Event<(text: String, suggest: Suggest) -> Unit>

    val onInputEntered: Event<(text: String, disposition: OnInputEnteredDisposition) -> Unit>

    val onInputCancelled: Event<() -> Unit>

    /**
     * Sets the description and styling for the default suggestion. The default suggestion is the text that is displayed in the first suggestion row underneath the URL bar.
     */
    fun setDefaultSuggestion(suggestion: DefaultSuggestResult)
}
