package menus

import browser.Event
import kotlin.Suppress
import kotlin.js.Promise
import tabs.Tab

/**
 * The different contexts a menu can appear in. Specifying 'all' is equivalent to the combination of all other contexts except for 'tab' and 'tools_menu'. */
typealias ContextType = String

/**
 * The type of menu item. */
typealias ItemType = String

/**
 * Information sent when a context menu item is clicked.
 */
class OnClickData(
        /**
         * The ID of the menu item that was clicked.
         */
        var menuItemId: MenuItemId,
        /**
         * The parent ID, if any, for the item clicked.
         */
        var parentMenuItemId: ParentMenuItemId? = null,
        /**
         * One of 'image', 'video', or 'audio' if the context menu was activated on one of these types of elements.
         */
        var mediaType: String? = null,
        /**
         * If the element is a link, the text of that link.
         */
        var linkText: String? = null,
        /**
         * If the element is a link, the URL it points to.
         */
        var linkUrl: String? = null,
        /**
         * Will be present for elements with a 'src' URL.
         */
        var srcUrl: String? = null,
        /**
         * The URL of the page where the menu item was clicked. This property is not set if the click occured in a context where there is no current page, such as in a launcher context menu.
         */
        var pageUrl: String? = null,
        /**
         *  The URL of the frame of the element where the context menu was clicked, if it was in a frame.
         */
        var frameUrl: String? = null,
        /**
         * The text for the context selection, if any.
         */
        var selectionText: String? = null,
        /**
         * A flag indicating whether the element is editable (text input, textarea, etc.).
         */
        var editable: Boolean,
        /**
         * A flag indicating the state of a checkbox or radio item before it was clicked.
         */
        var wasChecked: Boolean? = null,
        /**
         * A flag indicating the state of a checkbox or radio item after it is clicked.
         */
        var checked: Boolean? = null,
        /**
         * The id of the bookmark where the context menu was clicked, if it was on a bookmark.
         */
        var bookmarkId: String,
        /**
         * An array of keyboard modifiers that were held while the menu item was clicked.
         */
        var modifiers: Array<String>
)

/**
 * The ID of the menu item that was clicked. */
typealias MenuItemId = Any

/**
 * The parent ID, if any, for the item clicked. */
typealias ParentMenuItemId = Any

@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
class Icons() {
    inline operator fun get(key: String): String = asDynamic()[key]
    inline operator fun set(key: String, value: String) {
        asDynamic()[key] = value
    }
}

/**
 * A function that will be called back when the menu item is clicked. Event pages cannot use this; instead, they should register a listener for $(ref:contextMenus.onClicked). */
typealias Onclick = Any

/**
 * The ID of a parent menu item; this makes the item a child of a previously added item. */
typealias ParentId = Any

class CreateProperties(
        /**
         * The type of menu item. Defaults to 'normal' if not specified.
         */
        var type: ItemType? = null,
        /**
         * The unique ID to assign to this item. Mandatory for event pages. Cannot be the same as another ID for this extension.
         */
        var id: String? = null,
        var icons: Icons? = null,
        /**
         * The text to be displayed in the item; this is <em>required</em> unless <code>type</code> is 'separator'. When the context is 'selection', you can use <code>%s</code> within the string to show the selected text. For example, if this parameter's value is "Translate '%s' to Pig Latin" and the user selects the word "cool", the context menu item for the selection is "Translate 'cool' to Pig Latin".
         */
        var title: String? = null,
        /**
         * The initial state of a checkbox or radio item: true for selected and false for unselected. Only one radio item can be selected at a time in a given group of radio items.
         */
        var checked: Boolean? = null,
        /**
         * List of contexts this menu item will appear in. Defaults to ['page'] if not specified.
         */
        var contexts: Array<ContextType>? = null,
        /**
         * A function that will be called back when the menu item is clicked. Event pages cannot use this; instead, they should register a listener for $(ref:contextMenus.onClicked).
         */
        var onclick: Onclick? = null,
        /**
         * The ID of a parent menu item; this makes the item a child of a previously added item.
         */
        var parentId: ParentId? = null,
        /**
         * Lets you restrict the item to apply only to documents whose URL matches one of the given patterns. (This applies to frames as well.) For details on the format of a pattern, see $(topic:match_patterns)[Match Patterns].
         */
        var documentUrlPatterns: Array<String>? = null,
        /**
         * Similar to documentUrlPatterns, but lets you filter based on the src attribute of img/audio/video tags and the href of anchor tags.
         */
        var targetUrlPatterns: Array<String>? = null,
        /**
         * Whether this context menu item is enabled or disabled. Defaults to true.
         */
        var enabled: Boolean? = null,
        /**
         * Specifies a command to issue for the context click.  Currently supports internal commands _execute_page_action, _execute_browser_action and _execute_sidebar_action.
         */
        var command: String? = null
)

/**
 * Called when the item has been created in the browser. If there were any problems creating the item, details will be available in $(ref:runtime.lastError). */
typealias Callback = Any

/**
 * The ID of the newly created item. */
typealias CreateResult = Any

/**
 * The ID of the item to update. */
typealias Id = Any

typealias Onclick2 = Any

/**
 * Note: You cannot change an item to be a child of one of its own descendants. */
typealias ParentId2 = Any

/**
 * The properties to update. Accepts the same values as the create function.
 */
class UpdateProperties(
        var type: ItemType? = null,
        var title: String? = null,
        var checked: Boolean? = null,
        var contexts: Array<ContextType>? = null,
        var onclick: Onclick2,
        /**
         * Note: You cannot change an item to be a child of one of its own descendants.
         */
        var parentId: ParentId2? = null,
        var documentUrlPatterns: Array<String>? = null,
        var targetUrlPatterns: Array<String>? = null,
        var enabled: Boolean? = null
)

/**
 * The ID of the context menu item to remove. */
typealias MenuItemId2 = Any

external class MenusNamespace {
    val onClicked: Event<(info: OnClickData, tab: Tab?) -> Unit>

    /**
     * Creates a new context menu item. Note that if an error occurs during creation, you may not find out until the creation callback fires (the details will be in $(ref:runtime.lastError)).
     */
    fun create(createProperties: CreateProperties, callback: Callback? = definedExternally): CreateResult

    /**
     * Updates a previously created context menu item.
     */
    fun update(id: Int, updateProperties: UpdateProperties): Promise<Any>

    /**
     * Updates a previously created context menu item.
     */
    fun update(id: String, updateProperties: UpdateProperties): Promise<Any>

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
