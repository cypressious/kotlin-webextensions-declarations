package omnibox

import webextensions.Event

/**
 * The style type. */
typealias DescriptionStyleType = String

/**
 * The window disposition for the omnibox query. This is the recommended context to display results.
        For example, if the omnibox command is to navigate to a certain URL, a disposition of
        'newForegroundTab' means the navigation should take place in a new selected tab. */
typealias OnInputEnteredDisposition = String

/**
 * A suggest result.
 * @param content The text that is put into the URL bar, and that is sent to the extension when the
        user chooses this entry.
 * @param description The text that is displayed in the URL dropdown. Can contain XML-style markup
        for styling. The supported tags are 'url' (for a literal URL), 'match' (for highlighting
        text that matched what the user's query), and 'dim' (for dim helper text). The styles can be
        nested, eg. <dim><match>dimmed match</match></dim>. You must escape the five predefined
        entities to display them as text: stackoverflow.com/a/1091953/89484 
 */
class SuggestResult(
    var content: String,
    var description: String
)

/**
 * A suggest result.
 * @param description The text that is displayed in the URL dropdown.
 */
class DefaultSuggestResult(
    var description: String
)

/**
 * The style ranges for the description, as provided by the extension.
 * @param type The style type
 */
class DescriptionStyles(
    var offset: Int,
    var type: DescriptionStyleType,
    var length: Int? = null
)

/**
 * The style ranges for the description, as provided by ToValue().
 */
class DescriptionStylesRaw(var offset: Int, var type: Int)

/**
 * The style ranges for the description, as provided by the extension.
 * @param type The style type
 */
class DescriptionStyles2(
    var offset: Int,
    var type: DescriptionStyleType,
    var length: Int? = null
)

/**
 * The style ranges for the description, as provided by ToValue().
 */
class DescriptionStylesRaw2(var offset: Int, var type: Int)

external class OmniboxNamespace {
    /**
     * User has started a keyword input session by typing the extension's keyword. This is
            guaranteed to be sent exactly once per input session, and before any onInputChanged
            events.
     */
    val onInputStarted: Event<() -> Unit>

    /**
     * User has changed what is typed into the omnibox.
     *
     * @param text null
     * @param suggest A callback passed to the onInputChanged event used for sending suggestions
            back to the browser. */
    val onInputChanged: Event<(text: String, suggest: () -> Unit) -> Unit>

    /**
     * User has accepted what is typed into the omnibox.
     *
     * @param text null
     * @param disposition null */
    val onInputEntered: Event<(text: String, disposition: OnInputEnteredDisposition) -> Unit>

    /**
     * User has ended the keyword input session without accepting the input.
     */
    val onInputCancelled: Event<() -> Unit>

    /**
     * Sets the description and styling for the default suggestion. The default suggestion is the
            text that is displayed in the first suggestion row underneath the URL bar.
     */
    fun setDefaultSuggestion(suggestion: DefaultSuggestResult)
}
