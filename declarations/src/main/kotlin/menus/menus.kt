package menus

import kotlin.Any
import kotlin.js.Promise

typealias Icons = Any

class CreateCreateProperties(
    /**
     * The type of menu item. Defaults to 'normal' if not specified.
     */
    val type: ItemType,
    /**
     * The unique ID to assign to this item. Mandatory for event pages. Cannot be the same as another ID for this extension.
     */
    val id: String?,
    val icons: Icons?,
    /**
     * The text to be displayed in the item; this is <em>required</em> unless <code>type</code> is 'separator'. When the context is 'selection', you can use <code>%s</code> within the string to show the selected text. For example, if this parameter's value is "Translate '%s' to Pig Latin" and the user selects the word "cool", the context menu item for the selection is "Translate 'cool' to Pig Latin".
     */
    val title: String?,
    /**
     * The initial state of a checkbox or radio item: true for selected and false for unselected. Only one radio item can be selected at a time in a given group of radio items.
     */
    val checked: Boolean?,
    /**
     * List of contexts this menu item will appear in. Defaults to ['page'] if not specified.
     */
    val contexts: Array<ContextType>?,
    /**
     * A function that will be called back when the menu item is clicked. Event pages cannot use this; instead, they should register a listener for $(ref:contextMenus.onClicked).
     */
    val onclick: Any?,
    /**
     * The ID of a parent menu item; this makes the item a child of a previously added item.
     */
    val parentId: Any?,
    /**
     * Lets you restrict the item to apply only to documents whose URL matches one of the given patterns. (This applies to frames as well.) For details on the format of a pattern, see $(topic:match_patterns)[Match Patterns].
     */
    val documentUrlPatterns: Array<String>?,
    /**
     * Similar to documentUrlPatterns, but lets you filter based on the src attribute of img/audio/video tags and the href of anchor tags.
     */
    val targetUrlPatterns: Array<String>?,
    /**
     * Whether this context menu item is enabled or disabled. Defaults to true.
     */
    val enabled: Boolean?,
    /**
     * Specifies a command to issue for the context click.  Currently supports internal commands _execute_page_action, _execute_browser_action and _execute_sidebar_action.
     */
    val command: String?
)

class UpdateUpdateProperties(
    val type: ItemType,
    val title: String?,
    val checked: Boolean?,
    val contexts: Array<ContextType>?,
    val onclick: Any,
    /**
     * Note: You cannot change an item to be a child of one of its own descendants.
     */
    val parentId: Any?,
    val documentUrlPatterns: Array<String>?,
    val targetUrlPatterns: Array<String>?,
    val enabled: Boolean?
)

typealias ContextType = String

typealias ItemType = String

/**
 * Information sent when a context menu item is clicked.
 */
external class OnClickData {
  /**
   * The ID of the menu item that was clicked.
   */
  val menuItemId: Any

  /**
   * The parent ID, if any, for the item clicked.
   */
  val parentMenuItemId: Any?

  /**
   * One of 'image', 'video', or 'audio' if the context menu was activated on one of these types of elements.
   */
  val mediaType: String?

  /**
   * If the element is a link, the text of that link.
   */
  val linkText: String?

  /**
   * If the element is a link, the URL it points to.
   */
  val linkUrl: String?

  /**
   * Will be present for elements with a 'src' URL.
   */
  val srcUrl: String?

  /**
   * The URL of the page where the menu item was clicked. This property is not set if the click occured in a context where there is no current page, such as in a launcher context menu.
   */
  val pageUrl: String?

  /**
   *  The URL of the frame of the element where the context menu was clicked, if it was in a frame.
   */
  val frameUrl: String?

  /**
   * The text for the context selection, if any.
   */
  val selectionText: String?

  /**
   * A flag indicating whether the element is editable (text input, textarea, etc.).
   */
  val editable: Boolean

  /**
   * A flag indicating the state of a checkbox or radio item before it was clicked.
   */
  val wasChecked: Boolean?

  /**
   * A flag indicating the state of a checkbox or radio item after it is clicked.
   */
  val checked: Boolean?

  /**
   * The id of the bookmark where the context menu was clicked, if it was on a bookmark.
   */
  val bookmarkId: String

  /**
   * An array of keyboard modifiers that were held while the menu item was clicked.
   */
  val modifiers: Array<String>
}

external class Menus {
  /**
   * Creates a new context menu item. Note that if an error occurs during creation, you may not find out until the creation callback fires (the details will be in $(ref:runtime.lastError)).
   */
  fun create(createProperties: CreateCreateProperties, callback: Any?)

  /**
   * Updates a previously created context menu item.
   */
  fun update(id: Int, updateProperties: UpdateUpdateProperties): Promise<Any>

  /**
   * Updates a previously created context menu item.
   */
  fun update(id: String, updateProperties: UpdateUpdateProperties): Promise<Any>

  /**
   * Removes a context menu item.
   */
  fun remove(menuItemId: Int): Promise<Any>

  /**
   * Removes a context menu item.
   */
  fun remove(menuItemId: String): Promise<Any>

  /**
   * Removes all context menu items added by this extension.
   */
  fun removeAll(): Promise<Any>
}
